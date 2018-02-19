package dao;

import static util.CloseStreams.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

import bean.Reimbursement;
import util.Connections;

public class ReimbursementDaoImpl implements ReimbursementDao {

	@Override
	public void insertR(String username, String supername, Date submitDate, Date startDate, int isUrgent, int adjustedAmount) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String supervisor = null;
		String dept = null;
		String benco = null;
		
		String cursor = username;
		EmployeeDao eDao = new EmployeeDaoImpl();
		try {
			
			String head = eDao.getHead(cursor);
			while(head != null) {
				int level = eDao.getLevel(head);
				switch(level) {
					case 1:
						supervisor = head;
						break;
					case 2:
						dept = head;
						break;
					case 3:
						benco = head;
						break;
					default:
						throw new NoSuchFieldException("error getting level");
				}
				
				cursor = head;
				head = eDao.getHead(cursor);
			}
	
		}catch(NoSuchFieldException e) {
			System.out.println(e.toString());
		}

		
		try{
			Connection conn = Connections.getConnection();
			String sql = "INSERT INTO Reimbursement (username, submitDate, startDate, urgent, amount, supervisor, dept, benco, rid) "
					+ "VALUES (?,?,?,?, ?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setDate(2, submitDate);
			stmt.setDate(3, startDate);
			stmt.setInt(4, isUrgent);
			stmt.setInt(5, adjustedAmount);
			stmt.setString(6, supervisor);
			stmt.setString(7, dept);
			stmt.setString(8, benco);
			ReimbursementDao rDao = new ReimbursementDaoImpl();
			stmt.setInt(9, rDao.getUniqueId());
			rDao = null; //makes the object available for garbage collection
			stmt.executeUpdate(); 
		
			}catch(SQLException e){
//				logger.debug(e.getStackTrace());
				e.printStackTrace();
			}finally{
				close(stmt);
				close(rs);
			}
	}

	/**
	 * Returns
	 * 0 if submitDate 2 or more weeks earlier than startDate
	 * 1 if 1 week <= submitDate < 2 weeks compared to startDate
	 * 2 if submitDate less than 1 week to startDate (automatic rejection), 
	 * @param submitDate
	 * @param startDate
	 * @return
	 */
	@Override
	public int isUrgent(String submitDate, String startDate) {
		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
		TemporalAccessor ta = dtf.parse(submitDate);
		LocalDate submit = LocalDate.from(ta);
		ta = dtf.parse(startDate);
		LocalDate start = LocalDate.from(ta);
		
		LocalDate lessThanOneWeek = start.minusWeeks(1);
		
		//less than 7 days until course starts
		if(submit.compareTo(lessThanOneWeek) > 0) {
			return 2;
		}
		
		if(submit.compareTo(lessThanOneWeek.minusWeeks(1)) >= 0) {
			return 1;
		}
		else {
			return 0;
		}	
	}

	@Override
	public List<Reimbursement> getR(String username, int level) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Reimbursement> reims = new ArrayList<>();

		try{
			Connection conn = Connections.getConnection();
			String sql = "";
			switch(level) {
				case 0: 
					sql = "SELECT * FROM REIMBURSEMENT WHERE username = ?";
					break;
				case 1:
					sql = "SELECT * FROM REIMBURSEMENT WHERE supervisor = ?";
					break;
				case 2:
					sql = "SELECT * FROM REIMBURSEMENT WHERE dept = ?";
					break;
				case 3:
					sql = "SELECT * FROM REIMBURSEMENT WHERE benco = ?";
					break;				
			}

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery(); //Executing queries, brings back resultsets
		
			if (rs == null) {
				return null;
			}
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement();
				
				r.setAmount(rs.getInt("amount"));
				r.setUrgent(rs.getInt("urgent"));
				r.setStartDate(rs.getDate("startDate"));
				r.setSubmitDate(rs.getDate("submitDate"));
				r.setUsername(rs.getString("username"));
				if(rs.getString("supervisor") == null) {
					r.setSupervisor("Supervisor is Dept Head");
				}else {
					r.setSupervisor(rs.getString("supervisor"));					
				}
				r.setDept(rs.getString("dept"));
				r.setBenco(rs.getString("benco"));
				r.setId(rs.getInt("rid"));
				reims.add(r);
			}
				
			return reims;
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
	public int getUniqueId() {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Integer> rids = new ArrayList<>();

		try{
			Connection conn = Connections.getConnection();
			String sql = "SELECT rid FROM REIMBURSEMENT";

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(); //Executing queries, brings back resultsets
		
			if (rs == null) {
				return -1;
			}
			
			while(rs.next()) {
				rids.add(rs.getInt("rid"));
			}
			
			int random = (int)(Math.random() * 1000);
			while(rids.contains(random)) {
				random = (int)(Math.random() * 1000);
			}
				
			return random;
			}catch(SQLException e){
//				logger.debug(e.getStackTrace());
				e.printStackTrace();
				return -1;
			}finally{
				close(stmt);
				close(rs);
			}
	}

	@Override
	public Reimbursement getR(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Reimbursement r = new Reimbursement();

		try{
			Connection conn = Connections.getConnection();
			String sql = "SELECT * FROM REIMBURSEMENT WHERE rid = ?";

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(); //Executing queries, brings back resultsets
		
			if (rs == null) {
				return null;
			}
			
			rs.next();
			
			r.setAmount(rs.getInt("amount"));
			r.setUrgent(rs.getInt("urgent"));
			r.setStartDate(rs.getDate("startDate"));
			r.setSubmitDate(rs.getDate("submitDate"));
			r.setUsername(rs.getString("username"));
			if(rs.getString("supervisor") == null) {
				r.setSupervisor("Supervisor is Dept Head");
			}else {
				r.setSupervisor(rs.getString("supervisor"));					
			}
			r.setDept(rs.getString("dept"));
			r.setBenco(rs.getString("benco"));
			r.setId(rs.getInt("rid"));

				
			return r;
			}catch(SQLException e){
//				logger.debug(e.getStackTrace());
				e.printStackTrace();
				return null;
			}finally{
				close(stmt);
				close(rs);
			}
	}

}
