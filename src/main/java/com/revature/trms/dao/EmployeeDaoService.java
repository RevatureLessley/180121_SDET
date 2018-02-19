package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.trms.beans.Employee;
import com.revature.trms.util.Connections;
import com.revature.trms.util.CloseStreams;

public class EmployeeDaoService implements EmployeeDao{

	public Employee authenticate(String username, String password) throws SQLException {
		Statement stmt=null;
		ResultSet rs =null;
		try(Connection conn= Connections.getConnection()){ 
			String sql="SELECT EmployeeId, firstname, lastName FROM Employee WHERE "
					+ "username='"+username
					+"' AND PASSWORD ='" +password+"'";
			
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			if (rs.next()) {
           Employee emp= new Employee();
           emp.setEmpId(rs.getString(1));
           emp.setFirstName(rs.getString(2));
           emp.setLastName(rs.getString(3));
           emp.setUsername(username);
           emp.setPassword(password);
           
           
           return emp;
			}else return null;
        
       }        
       finally {
    	   CloseStreams.close(stmt);
		   CloseStreams.close(rs);
       }
		
	}

	
	@Override
	public boolean updateEmployeePI(String username, String ssn, String firtName, String lastName, String email,
			String phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmployeeUP(String u, String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean search(String username) throws SQLException {
		
		return false;
	}

	@Override
	public ArrayList<Employee> getEmployees(String username) throws SQLException {

		return null;
	}

/**
 * insert new records into (tuition table, reimbursment table ) through procedure
 */
	@Override
	public boolean ReimbursementRequest(String username, String startDate, String endDate, String location,
			String desctription, Double cost, String grading, String program) {
		
		return false;
	}
   
}
