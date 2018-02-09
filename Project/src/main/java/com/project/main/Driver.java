package com.project.main;

import java.sql.Connection;
import java.sql.SQLException;

import com.project.util.CloseStreams;
import com.project.util.Connections;

public class Driver {

	public static void main(String[] args) {
		
		try (Connection conn = Connections.getConnection()) {
			System.out.println("Success");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
