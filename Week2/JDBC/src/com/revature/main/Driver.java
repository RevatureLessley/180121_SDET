package com.revature.main;

import java.sql.Connection;

import com.revature.services.EmployeeService;
import com.revature.util.Connections;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeService.displayAllEmployees();
		int ran = (int)(Math.random()*500);
		EmployeeService.addEmployee("NewEmployee" + ran, (int)(Math.random()*500000), "NEW HIRE");
		
		/*
		try(Connection conn = Connections.getConnection()){
			System.out.println("SUCCESS!");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("FAILURE");
		}
		*/
		
	}

}
