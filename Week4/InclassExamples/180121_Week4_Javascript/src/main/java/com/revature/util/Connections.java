package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connections {
	private static Properties prop = null;
	private final static String FILE_NAME = "dbprops.properties";
	public static Connection getConnection() throws SQLException{
		try{
			prop = new Properties();
			prop.load(new FileInputStream(FILE_NAME));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		/*
		 * This class serves as a helper class to return the database connection
		 * for our project.
		 * In order to achieve a connection to a database in JDBC, you need
		 * to use the DriverManager class and have it return a connection via
		 * a specific driver to the class.
		 * getConnection(connectionURL, username, password)
		 */
		
		/*
		 * ClassLoader
		 * -The classloader is a tool that is utilized at runtime. It is what
		 * Java uses to bring in all the classes to be used by our application at
		 * runtime.
		 * -It also has the added benefit of manually bring in classes to be used
		 * for execution.
		 */
		//We forcibly bring in the OracleDriver class from our ojdbc7 jar.
		
		try {
			Class.forName(prop.getProperty("class"));
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("username"),
					prop.getProperty("password")
				);
	}
}