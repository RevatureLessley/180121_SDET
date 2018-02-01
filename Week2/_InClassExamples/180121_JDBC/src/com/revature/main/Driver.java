package com.revature.main;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.services.EmployeeService;

public class Driver {

	public static void main(String[] args) {
		EmployeeService.displayAllEmployees();
		int ran = (int)(Math.random()*500);
		EmployeeService.addEmployee("NewEmployee" + ran, 
				(int)(Math.random()*500000), "NEW HIRE");
		
		EmployeeDaoImpl dao = new EmployeeDaoImpl();
		dao.insertEmployeeViaSP(new Employee("procBob", 111000, "Example bob"));
		
		
	}

}
