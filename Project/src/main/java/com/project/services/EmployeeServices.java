package com.project.services;

import java.util.List;

import com.project.beans.Employee;
import com.project.beans.Tuition;
import com.project.dao.EmployeeDao;
import com.project.dao.EmployeeDaoImp;

public class EmployeeServices {
	
	
	static EmployeeDao dao = new EmployeeDaoImp();
	
	/**
	 * this method validate login credentials
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean validateLogin(String username, String password) {
		List<Employee> emps = dao.getAllEmployee();
		for(Employee emp : emps) {
			if(emp.getUserName().equals(username)&&emp.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * this method validates if the user name is taken or not 
	 * @param username
	 * @return
	 */
	public static boolean validateUsername(String username) {
		List<Employee> emps = dao.getAllEmployee();
		for(Employee emp : emps) {
			if(emp.getUserName().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * this method setup the initial approval state of a tuition form
	 * @param t
	 * @return
	 */
	public static int getInital_approval(Tuition t){
		List<Employee> emps = dao.getAllEmployee();
		for(Employee emp : emps) {
			if(emp.getUserName().equals(t.getUsername())) {
				if(emp.getTitle().equals("BENCO")) {
					return 0;
				}else if(emp.getTitle().equals("HEAD")||
						(t.getAttachment()!=null&&t.getFile_type().equals("msg"))) {
					return 1;
				}else if(emp.getTitle().equals("SUPERVISOR")) {
					return 2;
				}
		
			}
		}
		return 3;
	}
	
	/**
	 * This method gives email of employee by given title
	 * @param title
	 * @return
	 */
	public static String getEmailByTitle(String title) {
		List<Employee> emps = dao.getAllEmployee();
		for(Employee emp : emps) {
			if(emp.getTitle().equals(title)) {
				return emp.getEmail();
			}
		}
		return null;
	}
	
	/**
	 * this method gets title of employee by given user name
	 * @param username
	 * @return
	 */
	public static String getTitle(String username) {
		List<Employee> emps = dao.getAllEmployee();
		for(Employee emp : emps) {
			if(emp.getUserName().equals(username)) {
				return emp.getTitle();
			}
		}
		return null;
	}
}
