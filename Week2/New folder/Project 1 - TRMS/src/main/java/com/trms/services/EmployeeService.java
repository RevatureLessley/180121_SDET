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
		emp.setTitle("Employee");
		
		if(dao.insertEmployee(emp)!=0)
			return true;
		
		return false;
	}
	
	public static Employee getEmpByUsername(String username){
		EmployeeDaoImpl dao = new EmployeeDaoImpl();
		return dao.getEmployeeByUsername(username);
	}
	
	public static Employee getEmpById(long id){
		EmployeeDaoImpl dao = new EmployeeDaoImpl();
		return dao.getEmployeeById(id);
	}
	
	public static boolean checkUsernameAvailability(String username){
		List<Employee> emps = getAllEmployees();
		for(Employee e: emps){
			System.out.println(e);
		}
		for(Employee emp: emps){
			if(username.equals(emp.getUsername())){
				return true;
			}
		}
		return false;
	}
	
	public static boolean updateEmpByUsername(String username, Employee newEmp){
		Employee emp = getEmpByUsername(username);
		
		emp.setFname(newEmp.getFname());
		emp.setLname(newEmp.getLname());
		emp.setEmail(newEmp.getEmail());
		emp.setAddress(newEmp.getAddress());
		emp.setCity(newEmp.getCity());
		emp.setState(newEmp.getState());
		emp.setTel(newEmp.getTel());

		EmployeeDaoImpl dao = new EmployeeDaoImpl();
		
		return dao.updateEmployeeByUsername(username, emp);
	}
	
	public static boolean updateTitleById(long id, String title){
		EmployeeDaoImpl dao = new EmployeeDaoImpl();
		return dao.updateTitleById(id, title);
	}
	
	public static boolean updateSupervisorById(long id, String supervisorId){
		EmployeeDaoImpl dao = new EmployeeDaoImpl();
		return dao.updateSupervisorById(id, supervisorId);
	}
}
