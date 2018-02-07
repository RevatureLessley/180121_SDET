package com.miniprojectbankingsystem.repoprovider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {

	private static Connection con = null;
	private static Properties properties = null;

	public static Connection getConnection() {
		
		try {
			properties= new Properties();
			properties.load(new FileReader(".\\src\\resources\\oracle.properties"));
			
			String Driver = properties.getProperty("Driver");
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			
			Class.forName(Driver);
			con = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return con;
	}
}