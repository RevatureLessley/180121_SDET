package com.bank.main;

import java.sql.Connection;
import java.sql.SQLException;

import com.bank.util.Connections;



public class Driver {

	public static void main(String[] args) {
		try(Connection conn = Connections.getConnection()){
			System.out.println("Success");	
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
