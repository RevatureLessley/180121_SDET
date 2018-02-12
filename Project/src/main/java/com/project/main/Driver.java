package com.project.main;

import java.util.List;

import com.project.beans.Employee;
import com.project.dao.EmployeeDao;
import com.project.dao.EmployeeDaoImp;

public class Driver {

	public static void main(String[] args) {
		
		EmployeeDao dao = new EmployeeDaoImp();
//		Employee e = new Employee("harish","chandramohan","HARISH","PASSWORD","SDET",
//				"richard", "rosario","harishkumarchandra@gmail.com");
//		dao.addEmployee(e);
		dao.deleteEmployeeByUserName("HARISH");
		List<Employee> es = dao.getAllEmployee();
		for(Employee s:es) {
			System.out.println(s);
		}
		
	}

}
