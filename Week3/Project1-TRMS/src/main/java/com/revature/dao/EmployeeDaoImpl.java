package com.revature.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

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
public class EmployeeDaoImpl implements EmployeeDao{
	
	@Override
	/**Utility function used to check if there are currently any employees in the database, if so the total number is returned.**/
	public boolean checkEmpty() {
		if(totalEmployees() == 0) {return true;}
		else {return false;}
	}
	
	@Override
	/**Utility function used to return the total number of employees present in the database.**/
	public int totalEmployees() {
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT COUNT(*) FROM EMPLOYEES";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {count = rs.getInt(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		return count;
	}
	
	@Override
	/**This function performs a check to ensure that all employees use distinctive usernames. This
	 * function will compare a given Employee Objects username with all the usernames in the database
	 * and return true if the username has not been used yet, or return false if it has**/
	public boolean checkUniqueUsername(Employee employee) {
		Statement stmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT USERNAME FROM EMPLOYEES";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
			
			while(rs.next()){
				if(employee.getUsername().equals( rs.getString(1))) {return false;}
			}
		}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		return true;
	}
	
	@Override
	/**This function performs a check to ensure that all employees use distinctive usernames. This
	 * function will compare a given Employee Objects username with all the usernames in the database
	 * and return true if the username has not been used yet, or return false if it has**/
	public boolean checkUniqueUsername(String username) {
		Statement stmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT USERNAME FROM EMPLOYEES";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
			
			while(rs.next()){
				if(username.equals( rs.getString(1))) {return false;}
			}
		}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		return true;
	}
	
	@Override
	/**This function performs a check to ensure that all employees use distinctive ids. This
	 * function will compare a given Employee Objects emp_id with all the emp_id in the database
	 * and return true if the emp_id has not been used yet, or return false if it has**/
	public boolean checkUniqueId(Employee employee) {
		Statement stmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT EMP_ID FROM EMPLOYEES";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
			
			while(rs.next()){
				if(employee.getEmp_id() == rs.getInt(1)) {return false;}
			}
		}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		return true;
	}
	
	@Override
	/**This method is used for adding new users into the TRMS database. it will take an employee object, which contains all of an
	 * employees information, and add it into the database.**/
	public void addEmployee(Employee employee) {
		PreparedStatement ps = null;
		
		try(Connection conn = Bridge.connect()){
			String sql = "INSERT INTO EMPLOYEES VALUES(?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,employee.getEmp_id()); //emp_id
			ps.setString(2, employee.getFname()); //First name
			ps.setString(3,employee.getLname()); // Last Name
			ps.setString(4, employee.getUsername()); // Username
			ps.setString(5,employee.getPassword()); //Password
			ps.setString(6,employee.getEmail()); //Email Adress
			ps.setString(7,employee.getRole()); // Employee role
			ps.setString(8,employee.getDepartment()); //Department employee is part of 
			ps.setInt(9,employee.getSup_id()); //Direct supervisor of this employee
			ps.setInt(10,employee.getAmount()); //Reimbursement amount of this employee
			ps.executeUpdate();
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}
	}
	
	@Override
	/**This method will be used to delete a record from the Employee table in the databse. This method will use an employees
	 * username to remove the record for the user.**/
	public void deleteEmployee(Employee employee) {
		PreparedStatement ps = null;
		
		try(Connection conn = Bridge.connect()){
			String sql = "DELETE FROM EMPLOYEES WHERE USERNAME = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,employee.getUsername()); //Username
			ps.execute();
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}
	}
	
	@Override
	/**This is a utility function used for returning an employee object using a username as the identifier.**/
	public Employee getEmployee(String username) {
		List<Employee> employeeList = getAllEmployees();
		ListIterator<Employee> li = employeeList.listIterator(0);
		
		while(li.hasNext()) {
			if(li.next().getUsername().equals(username)) {return li.previous();}
		}
		return null;
	}

	@Override
	/**This is a utility function used for returning an employee object using a username as the identifier.**/
	public Employee getEmployee(int empId) {
		List<Employee> employeeList = getAllEmployees();
		ListIterator<Employee> li = employeeList.listIterator(0);
		
		while(li.hasNext()) {
			if(li.next().getEmp_id() == empId) {return li.previous();}
		}
		return null;
	}
	
	@Override
	/**This method is used to check a users username and password with what is listed in the Employees table in the database.
	 * This method will return an int; this int will correspond to a code that will indicate the status of the attempted login
	 * Below is the break down for this code:
	 * 0 ==== There are currently no users in TRMS
	 * 1 ==== Username was NOT found in TRMS
	 * 2 ==== Password is INCORRECT for provided username
	 * 3 ==== User is of the regular employee and should taken to a regualr employee page for that user.
	 * 4 ==== User is a Supervisor employee and should be taken to a supervisor login page fir that user.
	 * 5 ==== User is a department head employee and should be taken to a head employee login page for that user.
	 * 6 ==== User is a benefits coordinator employee and should be taken to a benefits coordinate login page for that user.**/
	public int checkCredentials(String u, String p) {
		if(totalEmployees() == 0){return 0;} //No users for TRMS created yet.
		
		List<Employee> employeeList = getAllEmployees();
		ListIterator<Employee> li = employeeList.listIterator(0);
		
		while(li.hasNext()) {
			if(li.next().getUsername().equals(u)) {
				if(li.previous().getPassword().equals(p)) {
					String r = li.next().getRole();
					
					switch(r){
					case "employee": return 3; // User is of the regular employee and should taken to a regular employee page for that user.
					case "supervisor": return 4; // User is a Supervisor employee and should be taken to a supervisor login page fir that user.
					case "head": return 5; // User is a department head employee and should be taken to a head employee login page for that user.
					case "benco": return 6; // User is a benefits coordinator employee and should be taken to a benefits coordinate login page for that user
					}
				} else {return 2;} // Password is INCORRECT for provided username
			} 
		}
		return 1; // Username was NOT found in TRMS
	}
	
	@Override
	/**This method goes through the database and creates an arraylist of employees and returns the list. This is used to search through
	 * the table of employees in a list form to make it easier thanks to list methods.**/
	public List<Employee> getAllEmployees() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> employeeList = new ArrayList<>();
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT * FROM EMPLOYEES";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
			
			while(rs.next()){
				
				employeeList.add(new Employee (
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10)
						));
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		
		return employeeList;
	}

	@Override
	/**This method goes through the database and creates an arraylist of employees who are supervisors and returns the list. This is used to search through
	 * the table of employees in a list form to make it easier thanks to list methods.**/
	public List<Employee> getAllSupervisors() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> supervisorList = new ArrayList<>();
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT * FROM employees WHERE role = 'supervisor'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
			
			while(rs.next()){
				
				supervisorList.add(new Employee (
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10)
						));
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		
		return supervisorList;
	}

	@Override
	/**This method goes through the database and creates an arraylist of employees who are heads of departments and returns the list. This is used to search through
	 * the table of employees in a list form to make it easier thanks to list methods.**/
	public List<Employee> getAllDepartmentHeads() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> headList = new ArrayList<>();
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT * FROM employees WHERE role = 'head'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
			
			while(rs.next()){
				
				headList.add(new Employee (
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10)
						));
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		
		return headList;
	}

	@Override
	/**This method goes through the database and creates an arraylist of employees who are benefits Coordinators and returns the list. This is used to search through
	 * the table of employees in a list form to make it easier thanks to list methods.**/
	public List<Employee> getAllBenefitsCoordinators() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> headList = new ArrayList<>();
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT * FROM employees WHERE role = 'benco'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
			
			while(rs.next()){
				
				headList.add(new Employee (
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10)
						));
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		
		return headList;
	}

	@Override
	public void refreshAmount() {
		
		PreparedStatement ps = null;
		
		try(Connection conn = Bridge.connect()){
			String sql = "UPDATE EMPLOYEES SET AMOUNT = 1000)";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}
		
		
	}
}