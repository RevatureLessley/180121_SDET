package com.revature.dao;

import java.util.List;

import com.revature.beans.Employee;

public interface EmployeeDao {

	public boolean checkEmpty ();
	public int totalEmployees();
	public boolean checkUniqueUsername(Employee employee);
	
	public void addEmployee(Employee employee);
	public void deleteEmployee(Employee employee);
	public Employee getEmployee(String username);
	public Employee getEmployee(int empId);
	public int checkCredentials (String u, String p);
	
	public List<Employee> getAllEmployees (); 
	public List<Employee> getAllSupervisors ();
	public List<Employee> getAllDepartmentHeads ();
	public List<Employee> getAllBenefitsCoordinators ();
	
	
}
