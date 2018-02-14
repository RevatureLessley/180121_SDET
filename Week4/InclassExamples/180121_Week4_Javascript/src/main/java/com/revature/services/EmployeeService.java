package com.revature.services;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDaoImpl;

public class EmployeeService {

	public static List<Employee> displayAllEmployees() {
		EmployeeDaoImpl dao = new EmployeeDaoImpl();
		return dao.getAllEmployee();

	}
}
