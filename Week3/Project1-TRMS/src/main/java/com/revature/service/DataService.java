package com.revature.service;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

import com.revature.beans.Reimbursement;
import com.revature.beans.RejectedReimbursement;
import com.revature.beans.AprovedReimbursement;
import com.revature.beans.Employee;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.ReimbursementDaoImpl;

import com.revature.util.Bridge;
import static com.revature.util.CloseStreams.close;

public class DataService {

	public static void insertEmployee(int emp_id, String fname, String lname, String username, String password, String email, String role,
			String department, int sup_id, int amount) {
		
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		
		Employee emp = new Employee (emp_id, fname, lname, username, password, email, role, department, sup_id, amount);
		empDao.addEmployee(emp);
		
	}
	
	public static boolean dupName(String name) {
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		return empDao.checkUniqueUsername(name);
	}
}
