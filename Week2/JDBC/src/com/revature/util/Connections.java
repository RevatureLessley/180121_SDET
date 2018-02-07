package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connections {
	private static Properties prop = null;
	private static final String FILE_NAME = "src/dbprops.properties";
	public static Connection getConnection() throws SQLException{
		try {
			prop = new Properties();
			prop.load(new FileInputStream(FILE_NAME));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		try {
			Class.forName(prop.getProperty("class"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe",
				"Animals",
				"animals" //prop.getProperty("class")
		);
	}
}
