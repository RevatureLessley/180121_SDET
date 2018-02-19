package com.revature.trms.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.trms.beans.Employee;



public interface EmployeeDao {
	//public String authenticate(String username, String password) throws SQLException;
	
	public boolean ReimbursementRequest(String username, String startDate, String endDate, String location, 
			                            String desctription, Double cost, String grading, String program);
	public boolean updateEmployeePI(String username,String ssn,String firtName,String lastName,String email,String phone);
	public boolean updateEmployeeUP(String u,String username,String password);
	public Employee authenticate(String username, String password) throws SQLException;
	//public boolean balanceUpdate(String username, Double amount, String descr ,Double   balance);
	boolean search(String username) throws SQLException;
	public ArrayList<Employee> getEmployees(String username) throws SQLException;
	
 
	
}
