package com.trms.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connections {
	private static Properties prop = null;
	private final static String FILE_NAME = "dbprops.properties";

	public static Connection getConnection() throws SQLException {
//		try {
//			prop = new Properties();
//			prop.load(new FileInputStream(FILE_NAME));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		if (prop != null) {
//			try {
//				Class.forName(prop.getProperty("class"));
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//
//			return DriverManager.getConnection(prop.getProperty("url"),
//					prop.getProperty("username"), prop.getProperty("password"));
//		}
//		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jiaqi", "jiaqi1234");
	}
}
