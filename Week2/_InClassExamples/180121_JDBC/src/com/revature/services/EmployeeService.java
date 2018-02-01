package com.revature.services;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;

public class EmployeeService {
	public static void displayAllEmployees(){
		EmployeeDao dao = new EmployeeDaoImpl();
		List<Employee> emps = dao.getAllEmployee();
		
		System.out.println("====LIST OF EMPLOYEES====");
		for(Employee e: emps){
			System.out.println(e.getEmpName());
		}
	}
}
