package dao;

import static util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Reimbursement;
import util.Connections;

public class ReimbursementDaoImpl implements ReimbursementDao {
	
	private static final Logger logger = LogManager.getLogger(ReimbursementDaoImpl.class);
	
	@Override
	public void insertR(String username, String supername, Date submitDate, Date startDate, int isUrgent, int adjustedAmount, int event) {
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
			String sql = "INSERT INTO Reimbursement (username, submitDate, startDate, urgent, amount, supervisor, dept, benco, rid, event_id) "
					+ "VALUES (?,?,?,?,?, ?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			ReimbursementDao rDao = new ReimbursementDaoImpl();
			stmt.setString(1, username);
			stmt.setDate(2, submitDate);
			stmt.setDate(3, startDate);
			stmt.setInt(4, isUrgent);
			stmt.setInt(5, adjustedAmount);
			stmt.setString(6, supervisor);
			stmt.setString(7, dept);
			stmt.setString(8, benco);
			stmt.setInt(9, rDao.getUniqueId());
			stmt.setInt(10, event);
			stmt.executeUpdate(); 
			rDao = null; //makes the object available for garbage collection
		
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
				r.setEmpcomment(rs.getString("empcomment"));
				r.setSupcomment(rs.getString("supcomment"));
				r.setDeptcomment(rs.getString("deptcomment"));
				r.setBencocomment(rs.getString("bencocomment"));
				r.setSuperapp( Integer.parseInt( rs.getString("superapp")));
				r.setDeptapp( Integer.parseInt( rs.getString("deptapp")));
				r.setBencoapp( Integer.parseInt( rs.getString("bencoapp")));
				r.setEvent(rs.getInt("event_id"));
				r.setFinalapp(rs.getInt("finalapp"));
				r.setRejected(rs.getInt("rejected"));
				
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
		Set<Integer> rids = new HashSet<>();//Set has O(1) performance for contains() method

		try{
			Connection conn = Connections.getConnection();
			String sql = "SELECT rid FROM REIMBURSEMENT";

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(); //Executing queries, brings back resultsets
			
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
			stmt.setInt(1, id);

			rs = stmt.executeQuery(); //Executing queries, brings back resultsets

			if(rs.next()) {
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
				r.setEmpcomment(rs.getString("empcomment"));
				r.setSupcomment(rs.getString("supcomment"));
				r.setDeptcomment(rs.getString("deptcomment"));
				r.setBencocomment(rs.getString("bencocomment"));
				r.setSuperapp( Integer.parseInt( rs.getString("superapp")));
				r.setDeptapp( Integer.parseInt( rs.getString("deptapp")));
				r.setBencoapp( Integer.parseInt( rs.getString("bencoapp")));
				r.setEvent(rs.getInt("event_id"));
				r.setFinalapp(rs.getInt("finalapp"));
				r.setRejected(rs.getInt("rejected"));

			}

				
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
	
	@Override
	public void approveR(int id, int level, String reason) {
		CallableStatement stmt = null;
		ResultSet rs = null;

		try{
			Connection conn = Connections.getConnection();
			String sql = "call appR(?,?, ?)";
			stmt = conn.prepareCall(sql);
			stmt.setInt(1, id);
			stmt.setInt(2, level);
			stmt.setString(3, reason);
			stmt.execute(); //Executing queries, brings back resultsets
			
			}catch(SQLException e){
//				logger.debug(e.getStackTrace());
				e.printStackTrace();
			}finally{
				close(stmt);
				close(rs);
			}
	}

	//good place to use a stored procedure instead of making two calls
	@Override
	public void rejectR(int id, int level, String reason) {
		CallableStatement stmt = null;
		ResultSet rs = null;

		try{
			Connection conn = Connections.getConnection();
			String sql = "call rejectR(?,?,?)";
			stmt = conn.prepareCall(sql);
			
/*			String level2 = "";
			switch(level) {
				case 1: 
					level2 = "superapp";
					break;
				case 2:
					level2 = "deptapp";
					break;
				case 3:
					level2 = "bencoapp";
					break;		
			}*/
			
			stmt.setInt(1, id);
			stmt.setInt(2, level);
			stmt.setString(3, reason);
			stmt.execute(); //Executing queries, brings back resultsets			
			
			}catch(SQLException e){
				logger.debug(e.getStackTrace());
				e.printStackTrace();
			}finally{
				close(stmt);
				close(rs);
			}		
	}

	@Override
	public void uploadFile(byte[] file, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try{
			Connection conn = Connections.getConnection();
			String sql = "UPDATE REIMBURSEMENT SET upload = ? WHERE rid = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setBytes(1, file);
			stmt.setInt(2, id);
			stmt.executeQuery(); //Executing queries, brings back resultsets	

			}catch(SQLException e){
				logger.debug(e.getStackTrace());
				e.printStackTrace();
			}finally{
				close(stmt);
				close(rs);
			}
	}

	@Override
	public byte[] downloadFile(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try{
			Connection conn = Connections.getConnection();
			String sql = "SELECT upload FROM REIMBURSEMENT WHERE rid = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery(); //Executing queries, brings back resultsets	

			if(rs != null) {
				rs.next();
				return rs.getBytes("upload");
			}
			
			}catch(SQLException e){
				logger.debug(e.getStackTrace());
				e.printStackTrace();
			}finally{
				close(stmt);
				close(rs);
			}
		
			return null;
	}

	@Override
	public void addComment(String comment, int level, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String stringLevel = "";
		switch(level) {
			case 0:
				stringLevel = "empcomment";
				break;
			case 1:
				stringLevel = "supcomment";
				break;
			case 2:
				stringLevel = "deptcomment";
				break;
			case 3:
				stringLevel = "bencocomment";
				break;
		}
		
		try{
			Connection conn = Connections.getConnection();
			String sql = "UPDATE REIMBURSEMENT SET " + stringLevel + " = ? WHERE rid = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comment);
			stmt.setInt(2, id);
			rs = stmt.executeQuery(); //Executing queries, brings back resultsets	

			
			}catch(SQLException e){
				logger.debug(e.getStackTrace());
				e.printStackTrace();
			}finally{
				close(stmt);
				close(rs);
			}
		
	}

	@Override
	public String getApprovalString(int num) {		
		switch (num){
			case 0:
				return "Not Yet Approved";
			case 1:
				return "Approved";
			default:
				return null;
		}
	}
	
	

}
