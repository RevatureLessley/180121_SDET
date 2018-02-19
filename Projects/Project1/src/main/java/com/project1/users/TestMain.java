package com.project1.users;

import com.project1.dao.EmployeeDaoImpl;

public class TestMain {
	public static void main(String args[]) {
		EmployeeDaoImpl emp = new EmployeeDaoImpl();
		emp.getAllAccounts();
	}
}
