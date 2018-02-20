package com.project.services;

import java.util.List;

import com.project.beans.Employee;
import com.project.dao.EmployeeDao;
import com.project.dao.EmployeeDaoImp;

public class EmployeeServices {
	
	static EmployeeDao dao = new EmployeeDaoImp();
	
	public static boolean validateLogin(String username, String password) {
		List<Employee> emps = dao.getAllEmployee();
		for(Employee emp : emps) {
			if(emp.getUserName().equals(username)&&emp.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean validateUsername(String username) {
		List<Employee> emps = dao.getAllEmployee();
		for(Employee emp : emps) {
			if(emp.getUserName().equals(username)) {
				return true;
			}
		}
		return false;
	}
}
