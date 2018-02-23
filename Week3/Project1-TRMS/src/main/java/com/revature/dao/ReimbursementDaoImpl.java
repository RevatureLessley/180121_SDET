package com.revature.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

import com.revature.beans.Reimbursement;
import com.revature.beans.Employee;

import com.revature.util.Bridge;


import static com.revature.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author Christian Diaz
 *
 */

public class ReimbursementDaoImpl implements ReimbursementDao {
	
	
	/**Utility function used to check if there are currently any reimbursements in the database, if so the total number is returned.**/
	public boolean checkEmpty() {
		if(totalReimbursements() == 0) {return true;}
		else {return false;}
	}
	

	/**Utility function used to return the total number of Reimbursements present in the database.**/
	public int totalReimbursements() {
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT COUNT(*) FROM reimbursements";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {count = rs.getInt(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		return count;
	}	
	
	@Override
	public boolean checkReimbursement(int reid) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT COUNT(*) FROM reimbursements WHERE REI_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reid);
			rs = ps.executeQuery();
			while(rs.next()) {count = rs.getInt(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		
		if(count == 0) {
			return false;
		}else {
			return true;
		}
		
	}
	
	
	
	@Override
	public int getCost(int emp_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cost = 0;
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT AMOUNT FROM employees WHERE EMP_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, emp_id);
			rs = ps.executeQuery();
			while(rs.next()) {cost = rs.getInt(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		return cost;
	}
	
	
	/**This function is used to add reimbursements in the database using a Reimbursement object.**/
	
	public void addReimbursement(Reimbursement reim) {
		
		PreparedStatement ps = null;
		
		try(Connection conn = Bridge.connect()){
			String sql = "INSERT INTO REIMBURSEMENTS VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,reim.getRei_id()); //rei_id
			ps.setInt(2,reim.getEmp_id()); //emp_id
			ps.setString(3,reim.getFname()); // First Name
			ps.setString(4, reim.getLname()); // Last Name
			ps.setString(5,reim.getDateOf()); // Date of Reimbursement
			ps.setString(6,reim.getTime()); // time of reimbursement
			ps.setString(7,reim.getLocation()); // Location of reimbursement
			ps.setString(8,reim.getDesc()); // Description of event
			ps.setInt(9,reim.getCost()); // Cost
			ps.setString(10,reim.getGradingFormat()); // Grading Format
			ps.setString(11,reim.getTypeOfEvent()); // Type of Event
			ps.setString(12,reim.getWork_related_justification()); // Work related Justification
			ps.setInt(13, reim.getAprroval_state());
			ps.setString(14,null); // for attachments
			ps.setString(15, ""); // empty note
			ps.executeUpdate();
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}
	
	}
	
	@Override
	public int editReimbursement(Reimbursement reim) {
		
		
		if(!checkReimbursement(reim.getRei_id())) {return 0;}
		
		PreparedStatement ps = null;
	
		try(Connection conn = Bridge.connect()){
			String sql = "UPDATE REIMBURSEMENTS SET DATEOF = ? , TIME = ?, LOCATION = ?, DESCRIPTION = ? , COST = ?, GRADING_FORMAT = ?, TYPE_OF_EVENT = ?, WORK_RELATED_JUSTIFICATION = ? WHERE REI_ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1,reim.getDateOf()); //Date Of
			ps.setString(2,reim.getTime()); // Time of
			ps.setString(3,reim.getLocation()); // Location
			ps.setString(4, reim.getDesc()); // Description
			ps.setInt(5,reim.getCost()); // Cost
			ps.setString(6,reim.getGradingFormat()); // Grading Format
			ps.setString(7,reim.getTypeOfEvent()); // Type Of Event
			ps.setString(8,reim.getWork_related_justification()); // Work Related Justification
			ps.setInt(9,reim.getRei_id()); // reim_id
			ps.execute();
		
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps); }
		
		return 1;
	}
	
	
	/**This function deletes from reimbursements using the reimbursemnt's id number**/
	
	public void deleteReimbursement(int id) {
		PreparedStatement ps = null;
		
		try(Connection conn = Bridge.connect()){
			String sql = "DELETE FROM REIMBURSEMENTS WHERE REI_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,id); //rei_id
			ps.execute();
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}
		
	}

	@Override
	public int getStatus(int rei_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int status = 0;
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT aprroval_state FROM REIMBURSEMENTS WHERE REI_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rei_id);
			rs = ps.executeQuery();
			while(rs.next()) {status = rs.getInt(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		return status;
	}
	
	
	/**This function deletes from reimbursements using the reimbursemnt's id number but using a reimbursement object as the argument**/
	
	public void deleteReimbursement(Reimbursement reim) {
		PreparedStatement ps = null;
		
		try(Connection conn = Bridge.connect()){
			String sql = "DELETE FROM REIMBURSEMENTS WHERE REI_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,reim.getRei_id()); //rei_id
			ps.execute();
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}
		
	}
	
	@Override
	public void updateStatus(int id,int s) {
		PreparedStatement ps = null;
	
		try(Connection conn = Bridge.connect()){
			String sql = "UPDATE REIMBURSEMENTS SET aprroval_state = ? WHERE REI_ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1,s); 
			ps.setInt(2,id);
		
			ps.execute();
		
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps); }
			
	}
	
	
	public Reimbursement getReimbursement(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Reimbursement getReimbursement(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public Employee getEmployeeByRid(int reid) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee emp = null;
		try(Connection conn = Bridge.connect()){
			
			String sql = " SELECT EMP_ID, FNAME, LNAME FROM REIMBURSEMENTS WHERE REI_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reid);
			rs = ps.executeQuery(); 
			
			while(rs.next()){
				
				emp = new Employee (
						rs.getInt(1), // 
						rs.getString(2), //
						rs.getString(3), // 
						"n/a",
						"n/a",
						"n/a",
						"n/a",
						"n/a",
						0,
						0);
				
				
				
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		
		return emp;
	}
	
	public List<Reimbursement> getAllReimbursement() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();
		
		try(Connection conn = Bridge.connect()){
			
			//String sql = "SELECT * FROM REIMBURSEMENTS";
			String sql = "SELECT REI_ID, EMP_ID, FNAME, LNAME, DATEOF, TIME, LOCATION, DESCRIPTION, COST, GRADING_FORMAT, TYPE_OF_EVENT , WORK_RELATED_JUSTIFICATION, APRROVAL_STATE, NOTE FROM REIMBURSEMENTS";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
			
			while(rs.next()){
				
				reimbursementList.add(new Reimbursement (
						rs.getInt(1), // rei_id
						rs.getInt(2), //emp_id
						rs.getString(3), // First name
						rs.getString(4), // Last name
						rs.getString(5), // Date Of Event 
						rs.getString(6), // Time Of event
						rs.getString(7), // Location of event
						rs.getString(8), // Description of event
						rs.getInt(9), 	 // Cost of event
						rs.getString(10), // Grading Format
						rs.getString(11), // type of event
						rs.getString(12),  // Work related Justification
						rs.getInt(13),
						rs.getString(14)
						//rs.getString(15)
						));
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		
		return reimbursementList;
	}
	
	
	public List<Reimbursement> getAllReimbursementUser(int emp_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT REI_ID, EMP_ID, FNAME, LNAME, DATEOF, TIME, LOCATION, DESCRIPTION, COST, GRADING_FORMAT, TYPE_OF_EVENT ,WORK_RELATED_JUSTIFICATION, APRROVAL_STATE,NOTE FROM REIMBURSEMENTS WHERE EMP_ID = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, emp_id);
			rs = ps.executeQuery(); 
			
			while(rs.next()){
				
				reimbursementList.add(new Reimbursement (
						rs.getInt(1), // rei_id
						rs.getInt(2), //emp_id
						rs.getString(3), // First name
						rs.getString(4), // Last name
						rs.getString(5), // Date Of Event 
						rs.getString(6), // Time Of event
						rs.getString(7), // Location of event
						rs.getString(8), // Description of event
						rs.getInt(9), 	 // Cost of event
						rs.getString(10), // Grading Format
						rs.getString(11), // type of event
						rs.getString(12),  // Work related Justification
						rs.getInt(13), // Approval status
						rs.getString(14) // note
						));
				
				
				
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		
		return reimbursementList;
	}


	@Override
	public List<Reimbursement> getSuperReimbursementUser(int emp_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();
		
		try(Connection conn = Bridge.connect()){
			
			String sql = " SELECT REIMBURSEMENTS.REI_ID, REIMBURSEMENTS.EMP_ID, REIMBURSEMENTS.FNAME, REIMBURSEMENTS.LNAME, REIMBURSEMENTS.DATEOF, REIMBURSEMENTS.TIME, REIMBURSEMENTS.LOCATION, REIMBURSEMENTS.DESCRIPTION, REIMBURSEMENTS.COST, REIMBURSEMENTS.GRADING_FORMAT, REIMBURSEMENTS.TYPE_OF_EVENT , REIMBURSEMENTS.WORK_RELATED_JUSTIFICATION, REIMBURSEMENTS.APRROVAL_STATE, REIMBURSEMENTS.NOTE\r\n" + 
					"FROM REIMBURSEMENTS\r\n" + 
					"INNER JOIN EMPLOYEES ON REIMBURSEMENTS.EMP_ID = EMPLOYEES.EMP_ID\r\n" + 
					"WHERE EMPLOYEES.SUP_ID = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, emp_id);
			rs = ps.executeQuery(); 
			
			while(rs.next()){
				
				reimbursementList.add(new Reimbursement (
						rs.getInt(1), // rei_id
						rs.getInt(2), //emp_id
						rs.getString(3), // First name
						rs.getString(4), // Last name
						rs.getString(5), // Date Of Event 
						rs.getString(6), // Time Of event
						rs.getString(7), // Location of event
						rs.getString(8), // Description of event
						rs.getInt(9), 	 // Cost of event
						rs.getString(10), // Grading Format
						rs.getString(11), // type of event
						rs.getString(12),  // Work related Justification
						rs.getInt(13), // Approval status
						rs.getString(14) // note
						));
				
				
				
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		
		return reimbursementList;
	}


	@Override
	public List<Reimbursement> getDepartmentReimbursementUser(String department) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();
		
		try(Connection conn = Bridge.connect()){
			
			String sql = " SELECT REIMBURSEMENTS.REI_ID, REIMBURSEMENTS.EMP_ID, REIMBURSEMENTS.FNAME, REIMBURSEMENTS.LNAME, REIMBURSEMENTS.DATEOF, REIMBURSEMENTS.TIME, REIMBURSEMENTS.LOCATION, REIMBURSEMENTS.DESCRIPTION, REIMBURSEMENTS.COST, REIMBURSEMENTS.GRADING_FORMAT, REIMBURSEMENTS.TYPE_OF_EVENT , REIMBURSEMENTS.WORK_RELATED_JUSTIFICATION, REIMBURSEMENTS.APRROVAL_STATE, REIMBURSEMENTS.NOTE\r\n" + 
					"FROM REIMBURSEMENTS\r\n" + 
					"INNER JOIN EMPLOYEES ON REIMBURSEMENTS.EMP_ID = EMPLOYEES.EMP_ID\r\n" + 
					"WHERE EMPLOYEES.DEPARTMENT = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, department);
			rs = ps.executeQuery(); 
			
			while(rs.next()){
				
				reimbursementList.add(new Reimbursement (
						rs.getInt(1), // rei_id
						rs.getInt(2), //emp_id
						rs.getString(3), // First name
						rs.getString(4), // Last name
						rs.getString(5), // Date Of Event 
						rs.getString(6), // Time Of event
						rs.getString(7), // Location of event
						rs.getString(8), // Description of event
						rs.getInt(9), 	 // Cost of event
						rs.getString(10), // Grading Format
						rs.getString(11), // type of event
						rs.getString(12),  // Work related Justification
						rs.getInt(13), // Approval status
						rs.getString(14) // note
						));
				
				
				
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		
		return reimbursementList;
	}


	


	












}