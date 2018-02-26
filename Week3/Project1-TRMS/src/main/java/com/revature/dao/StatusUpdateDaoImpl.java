package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.util.Bridge;

public class StatusUpdateDaoImpl implements StatusUpdateDao {

	@Override
	public int updateStatus(int reiid, String afname, String alname, int status, String note) {
		
		ReimbursementDaoImpl remDao = new ReimbursementDaoImpl();
		Employee emp = remDao.getEmployeeByRid(reiid);
		
		
		if(!remDao.checkReimbursement(reiid)) {return 0;}
		
		if(getStatus(reiid,status) == 1) {return 2;}
		
		PreparedStatement ps = null;
		
		try(Connection conn = Bridge.connect()){
			String sql = "INSERT INTO R_STATUS VALUES(?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,reiid); //
			ps.setInt(2, emp.getEmp_id()); //
			ps.setString(3,emp.getFname()); // 
			ps.setString(4,emp.getLname()); // 
			ps.setString(5,afname); //
			ps.setString(6,alname); //
			ps.setInt(7,status); // Employee role
			ps.setString(8,note); //Department employee is part of 
			ps.executeUpdate();
			
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}
		
		return 1;
	}

	public int getStatus(int s, int initial) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int status = 0;
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT count(approval_status) FROM r_status WHERE REI_ID = ? AND APPROVAL_STATUS = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s);
			ps.setInt(2, initial);
			rs = ps.executeQuery();
			while(rs.next()) {status = rs.getInt(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
return status;
	}

	@Override
	public List<Status> getReimbursements(int emp_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Status> statusList = new ArrayList<Status>();
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT * FROM R_Status WHERE EMP_ID = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, emp_id);
			rs = ps.executeQuery(); 
			
			while(rs.next()){
				
				statusList.add(new Status (
						rs.getInt(1), 		// rei_id
						rs.getInt(2), 		//emp_id
						rs.getString(3), 	// First name
						rs.getString(4), 	// Last name
						rs.getString(5), 	// Date Of Event 
						rs.getString(6), 	// Time Of event
						rs.getInt(7), 	// Location of event
						rs.getString(8) 	// Description of event
						));
				
				
				
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		
		return statusList;
	}

	@Override
	public List<Status> getAwaitingReimbursements(String role) {
		
		if(role.equals("head")) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Status> statusList = new ArrayList<Status>();
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT * FROM R_Status WHERE Approval_status = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			rs = ps.executeQuery(); 
			
			while(rs.next()){
				
				statusList.add(new Status (
						rs.getInt(1), 		// rei_id
						rs.getInt(2), 		//emp_id
						rs.getString(3), 	// First name
						rs.getString(4), 	// Last name
						rs.getString(5), 	// Date Of Event 
						rs.getString(6), 	// Time Of event
						rs.getInt(7), 	// Location of event
						rs.getString(8) 	// Description of event
						));
				
				
				
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		
		return statusList;
		
		
		}
		
		else {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Status> statusList = new ArrayList<Status>();
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT * FROM R_Status WHERE Approval_status = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 2);
			rs = ps.executeQuery(); 
			
			while(rs.next()){
				
				statusList.add(new Status (
						rs.getInt(1), 		// rei_id
						rs.getInt(2), 		//emp_id
						rs.getString(3), 	// First name
						rs.getString(4), 	// Last name
						rs.getString(5), 	// Date Of Event 
						rs.getString(6), 	// Time Of event
						rs.getInt(7), 	// Location of event
						rs.getString(8) 	// Description of event
						));
				
				
				
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		
		return statusList;
		}
	}

}
