package com.trms.services;

import java.util.List;
import com.trms.beans.Employee;
import com.trms.dao.EmployeeDaoImpl;

public class EmployeeService {

	public static List<Employee> getAllEmployees() {
		EmployeeDaoImpl dao = new EmployeeDaoImpl();
		return dao.getAllEmployee();

	}

	public static boolean addEmployee(String fname, String lname, String username, String email, String password){
		EmployeeDaoImpl dao = new EmployeeDaoImpl();
		Employee emp = new Employee();
		emp.setFname(fname);
		emp.setLname(lname);
		emp.setUsername(username);
		emp.setEmail(email);
		emp.setPassword(password);
		
		if(dao.insertEmployee(emp)!=0)
			return true;
		
		return false;
	}
	
	public static boolean insertEmployee(String name, Integer salary, String title){
//		EmployeeDaoImpl dao = new EmployeeDaoImpl();
//		Employee emp = new Employee(name,salary,title);
//		
//		if(dao.insertEmployee(emp) != 0){
//			return true;
//		}
		return false;
	}
	
	public static Employee getEmpByUsername(String username){
		EmployeeDaoImpl dao = new EmployeeDaoImpl();
		return dao.getEmployeeByUsername(username);
	}
	
	public static boolean checkUsernameAvailability(String username){
		for(Employee emp: getAllEmployees()){
			if(username.equals(emp.getUsername())){
				return true;
			}
		}
		return false;
	}
}
