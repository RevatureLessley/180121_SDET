package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bridge {
	public static Connection connect() throws SQLException{
		try {Class.forName("oracle.jdbc.driver.OracleDriver");} 
		catch (ClassNotFoundException e) {e.printStackTrace();}
		return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"TRMS",
					"trmspassword");
	}
}