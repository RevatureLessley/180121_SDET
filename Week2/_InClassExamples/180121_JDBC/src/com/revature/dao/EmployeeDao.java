package com.revature.dao;

import java.util.List;

import com.revature.beans.Employee;

/*
 * Typically, you will start your dao with a skeleton to enforce
 * that all required methods are implemented for any situations that
 * involve interacting with employees in the database.
 */
public interface EmployeeDao {
	public void insertEmployee(Employee emp);
	public Employee selectEmployeeById(int id);
	public List<Employee> getAllEmployee();
	public int deleteEmployeeById();
	public int updateEmployeeById(Employee emp);
}
