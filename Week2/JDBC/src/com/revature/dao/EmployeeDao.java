package com.revature.dao;

import java.util.List;

import com.revature.beans.Employee;

public interface EmployeeDao {
	public void insertEmployee(Employee emp);
	public Employee selectEmployeeById(int id);
	public List<Employee> getAllEmployee();
	public int deleteEmployeeById();
	public int updateEmployeeById(Employee emp);
}
