package com.project.dao;

import java.util.List;

import com.project.beans.Employee;

public interface EmployeeDao {

	public List<Employee> getAllEmployee();
	public boolean addEmployee(Employee e);
	public boolean deleteEmployeeByUserName(String e);
	
}
