package dao;

import static util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import org.apache.log4j.Logger;

import bean.Employee;
import util.Connections;

public class EmployeeDaoImpl implements EmployeeDao {
	
//	final static Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

	public boolean createEmployee(String username, String password, int type) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String reportsto = "";
		try {

			EmployeeDao eDao = new EmployeeDaoImpl();
			switch (type) {
				case 0: //superior is supervisor or dept head
					if(Math.random() < 0.5) {
						reportsto = eDao.getRandomHead(1);				
					}
					else {
						reportsto = eDao.getRandomHead(2);
					}
					break;
				case 1://supervisor is dept head
					reportsto = eDao.getRandomHead(2);
					break;
				case 2://supervisor is a Benco
					reportsto = eDao.getRandomHead(3);
					break;
				case 3: //created Benco no supervisor
					reportsto = null;
					break;
				default:
					throw new NoSuchFieldException("Type must be 0,1,2,or 3");		
			
			}			
		}
		catch(NoSuchFieldException e) {
			System.out.println(e.toString());
		}		
				
		try{
			Connection conn = Connections.getConnection();
			String sql = "INSERT INTO Employee (username, password, type, reportsto) "
					+ "VALUES (?,?, ?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setInt(3,type);
			stmt.setString(4,reportsto);
			stmt.executeUpdate(); //Executing queries, brings back resultsets
			
			return true;
				
			}catch(SQLException e){
//				logger.debug(e.getStackTrace());
				e.printStackTrace();
				return false;
			}finally{
				close(stmt);
				close(rs);
			}	
	}

	@Override
	public Employee logIn(String username, String password){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Employee emp = new Employee();

		try{
			
			Connection conn = Connections.getConnection();
			String sql = "SELECT * FROM EMPLOYEE "
					+ "WHERE username = ? AND password = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs = stmt.executeQuery(); //Executing queries, brings back resultsets
			
			if (rs == null) {
				return null;
			}
			
			while(rs.next()) {
				emp.setUsername(rs.getString("username"));
				emp.setPassword(rs.getString("password"));				
			}
				
			return emp;
				
			}catch(SQLException e){
//				logger.debug(e.getStackTrace());
				e.printStackTrace();
				return null;
			}finally{
				close(stmt);
				close(rs);
			}
	}


	/**
	 * This method determines the number of individuals at a hierarchy level,
	 * then randomly picks one by index
	 * 
	 * I considered CallableStatement to return number of rows but that
	 * has higher overhead than PreparedStatement
	 * 
	 * Also considered a scroll-insensitive resultset but that is very inefficient for large results
	 * 
	 * 1 to return a supervisor
	 * 2 to return a dept head
	 * 3 to return a benco
	 */
	@Override
	public String getRandomHead(int level) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
//		Employee emp = new Employee();
		
		try{
			Connection conn = Connections.getConnection();
			
			String rowCount = "SELECT COUNT(*) FROM EMPLOYEE "
					+ "WHERE type = ?";
			stmt = conn.prepareStatement(rowCount);
			stmt.setInt(1, level);
			rs = stmt.executeQuery(); //Executing queries, brings back resultsets
			rs.next();
			int size = rs.getInt(1);
			
			String sql = "SELECT * FROM EMPLOYEE "
					+ "WHERE type = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, level);
			rs = stmt.executeQuery(); //Executing queries, brings back resultsets
			
			if (rs == null) {
				return "-1";
			}
			
			int randomIndex = (int) (Math.random() * size);
			rs.next();
			for(int i = 0; i < randomIndex;i++) {
				rs.next();
			}
			
			return rs.getString("username");
			
			}catch(SQLException e){
//				logger.debug(e.getStackTrace());
				e.printStackTrace();
				return null;
			}finally{
				close(stmt);
				close(rs);
			}
	}

	@Override
	public String getHead(String username) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try{
			Connection conn = Connections.getConnection();
			String sql = "SELECT reportsto FROM EMPLOYEE "
					+ "WHERE username = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery(); //Executing queries, brings back resultsets
			
			if (rs == null) {
				return null;
			}
			
			rs.next();

			return rs.getString("reportsto");
				
				
			}catch(SQLException e){
//				logger.debug(e.getStackTrace());
				e.printStackTrace();
				return null;
			}finally{
				close(stmt);
				close(rs);
			}
	}

	@Override
	public int getLevel(String username) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try{
			Connection conn = Connections.getConnection();
			String sql = "SELECT type FROM EMPLOYEE "
					+ "WHERE username = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery(); //Executing queries, brings back resultsets
			
			if (rs == null) {
				return -1;
			}
			
			rs.next();

			return rs.getInt("type");
				
				
			}catch(SQLException e){
//				logger.debug(e.getStackTrace());
				e.printStackTrace();
				return -1;
			}finally{
				close(stmt);
				close(rs);
			}
	}
	

}
