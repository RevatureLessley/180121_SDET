package com.revature.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	static {
		 		try {
		 			Class.forName("oracle.jdbc.OracleDriver");
				} catch (ClassNotFoundException e) {
		 			e.getStackTrace();
		 		}
		 	}
	
	public static Connection getConnection() throws SQLException, IOException{
		//File f = new File(".");
		//for(String i: f.list()) {
		//	System.out.println(i);
		//}
		//Properties prop = new Properties();
		//InputStream in = new FileInputStream("connection.properties");
		//prop.load(in);
		
//		String url = prop.getProperty("url");
//		String user = prop.getProperty("user");
//		String password = prop.getProperty("password");
		
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "$Tabla26");
	}
}