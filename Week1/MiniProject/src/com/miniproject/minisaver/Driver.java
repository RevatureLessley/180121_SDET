package com.miniproject.minisaver;

import java.sql.Connection;

import com.miniproject.util.Connections;

public class Driver {
	
	public static void main(String[] args) {
		MiniSaver miniSaver = new MiniSaver();
		miniSaver.runProgram();
		
		try(Connection conn = Connections.getConnection()){
			System.out.println("SUCCESS!");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("FAILURE");
		}
	}
}