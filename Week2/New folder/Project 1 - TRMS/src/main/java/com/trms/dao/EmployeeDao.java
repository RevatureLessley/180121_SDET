package com.trms.dao;

import java.util.List;

import com.trms.beans.Employee;

/*
 * Typically, you will start your dao with a skeleton to enforce
 * that all required methods are implemented for any situations that
 * involve interacting with employees in the database.
 */
public interface EmployeeDao {
	public int insertEmployee(Employee emp);
	public Employee getEmployeeById(String id);
	public List<Employee> getAllEmployee();
	public int updateEmployeeById(Employee emp);
	public Employee getEmployeeByUsername(String username);
	
}
