package com.revature.trms.service;

import java.sql.SQLException;

import com.revature.trms.beans.Employee;
import com.revature.trms.dao.EmployeeDaoService;

public class EmployeeService {
	private static EmployeeDaoService empDS=new EmployeeDaoService();
	public static Employee authentication(String username, String password) throws SQLException {
		
		return empDS.authenticate(username,password);
	}

}
