package com.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connections {
	public static Connection getConnection() throws SQLException{
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"MINIPROJECT",
					"MINIPROJECT"
				);
	}
}
