package com.banksystem.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connections {
	public static Connection getConnection() throws SQLException{
		
		//We forcibly bring in the driver class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//getConnection(connectionURL, username, password);
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
				"jiaqi", 
				"jiaqi1234");
	}

}
