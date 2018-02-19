package com.project1.beans;

import java.util.List;

import com.project1.dao.EmployeeDaoImpl;

public class TestMain {
	public static void main(String args[]) {
		EmployeeDaoImpl emp = new EmployeeDaoImpl();
		List<Employee> employees = emp.getAllEmployees();
		for (Employee e : employees) System.out.println(e);
	}
}
