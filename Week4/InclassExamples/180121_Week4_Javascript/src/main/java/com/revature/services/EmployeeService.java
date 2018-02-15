package com.revature.services;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDaoImpl;

public class EmployeeService {

	public static List<Employee> displayAllEmployees() {
		EmployeeDaoImpl dao = new EmployeeDaoImpl();
		return dao.getAllEmployee();

	}
	public static boolean nameTaken(String name){
		return false;
	}
	
	public static boolean insertEmployee(String name, Integer salary, String title){
		EmployeeDaoImpl dao = new EmployeeDaoImpl();
		Employee emp = new Employee(name,salary,title);
		
		if(dao.insertEmployee(emp) != 0){
			return true;
		}
		return false;
	}
}
