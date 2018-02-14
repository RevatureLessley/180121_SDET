package com.revature.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

import com.revature.beans.Reimbursement;
import com.revature.beans.RejectedReimbursement;
import com.revature.beans.AprovedReimbursement;
import com.revature.beans.Employee;

import com.revature.util.Bridge;

import static com.revature.communication.CloseStreams.close;
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
	
	@Override
	/**Utility function used to check if there are currently any reimbursements in the database, if so the total number is returned.**/
	public boolean checkEmptyReg() {
		if(totalReimbursementsReg() == 0) {return true;}
		else {return false;}
	}
	@Override
	/**Utility function used to check if there are currently any approved reimbursements in the database, if so the total number is returned.**/
	public boolean checkEmptyApr() {
		if(totalReimbursementsApr() == 0) {return true;}
		else {return false;}
	}
	@Override
	/**Utility function used to check if there are currently any rejected reimbursements in the database, if so the total number is returned.**/
	public boolean checkEmptyRej() {
		if(totalReimbursementsRej() == 0) {return true;}
		else {return false;}
	}
	
	@Override
	/**Utility function used to return the total number of Reimbursements present in the database.**/
	public int totalReimbursementsReg() {
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
	/**Utility function used to return the total number of Approved Reimbursements present in the database.**/
	public int totalReimbursementsApr() {
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT COUNT(*) FROM APPROVAL_STATE";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {count = rs.getInt(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		return count;
	}
	@Override
	/**Utility function used to return the total number of Rejected Reimbursements present in the database.**/
	public int totalReimbursementsRej() {
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT COUNT(*) FROM REJECTED";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {count = rs.getInt(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		return count;
	}
	
	
	
	
	/**This function is used to add reimbursements in the database using a Reimbursement object.**/
	@Override
	public void addReimbursement(Reimbursement reim) {
		
		PreparedStatement ps = null;
		int newId = totalReimbursementsReg() + 1 ;
		
		try(Connection conn = Bridge.connect()){
			String sql = "INSERT INTO REIMBURSEMENTS VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,newId); //rei_id
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
			ps.setString(13,null); // for attachments
			ps.executeUpdate();
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}
	
	}
	
	/**This function deletes from reimbursements using the reimbursemnt's id number**/
	@Override
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

	/**This function deletes from reimbursements using the reimbursemnt's id number but using a reimbursement object as the argument**/
	@Override
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
	public void aproveReimbursement(int id, int role) {
		switch(role) {
		
		
		
		
		
		
		case 1:// Supervisor approval
			break;
		case 2:// Head of Department approval
			break;
		case 3:// Benefits Coordinator approval
			break;
		}
		
		
		PreparedStatement ps = null;
		
		try(Connection conn = Bridge.connect()){			
			String sql = "UPDATE  SET VAL = ?" 
					+ "WHERE USERNAME = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "YEP");
			ps.setString(2, user);
			ps.execute(); 
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}
		
		
		PreparedStatement ps = null;
		int newId = totalReimbursements() + 1 ;
		
		try(Connection conn = Bridge.connect()){
			String sql = "INSERT INTO REIMBURSEMENTS VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,newId); //rei_id
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
			ps.setString(13,null); // for attachments
			ps.executeUpdate();
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}
		
	}

	@Override
	public void rejectReimbursement(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reimbursement getReimbursement(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement getReimbursement(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/** This function will go into the database and return a List object that contains a list of Reimbursement objects that correspond to all the elements in the 
	 * Reimbursements table of the database.**/
	@Override
	public List<Reimbursement> getAllReimbursement() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Reimbursement> reimbursementList = new ArrayList<>();
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT * FROM REIMBURSEMENTS";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
			
			while(rs.next()){
				
				reimbursementList.add(new Reimbursement (
						rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12)
						));
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		
		return reimbursementList;
	}

	/** This function will go into the database and return a List object that contains a list of aproved Reimbursement objects that correspond to all the elements in the 
	 * Reimbursements table of the database.**/
	@Override
	public List<AprovedReimbursement> getAllAprovedReimbursement() {
		Statement stmt = null;
		ResultSet rs = null;
		List<AprovedReimbursement> reimbursementList = new ArrayList<>();
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT * FROM approval_state";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
			
			while(rs.next()){
				
				reimbursementList.add(new AprovedReimbursement (
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5)
						));
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		
		return reimbursementList;
	}

	/** This function will go into the database and return a List object that contains a list of rejected Reimbursement objects that correspond to all the elements in the 
	 * Reimbursements table of the database.**/
	@Override
	public List<RejectedReimbursement> getAllRejectedReimbursement() {
		Statement stmt = null;
		ResultSet rs = null;
		List<RejectedReimbursement> reimbursementList = new ArrayList<>();
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT * FROM rejected";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
			
			while(rs.next()){
				
				reimbursementList.add(new RejectedReimbursement (
						rs.getInt(1),
						rs.getInt(2),
						rs.getString(3)
						));
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		
		return reimbursementList;

}
}