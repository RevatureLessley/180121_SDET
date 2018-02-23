package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.beans.Employee;
import com.revature.util.Bridge;

public class StatusUpdateDaoImpl implements StatusUpdateDao {

	@Override
	public int updateStatus(int reiid, String afname, String alname, int status, String note) {
		
		ReimbursementDaoImpl remDao = new ReimbursementDaoImpl();
		Employee emp = remDao.getEmployeeByRid(reiid);
		
		
		if(!remDao.checkReimbursement(reiid)) {return 0;}
		
		if(remDao.getStatus(reiid) == status) {return 2;}
		
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
			
			remDao.updateStatus(reiid, status);
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}
		
		return 1;
	}

}
