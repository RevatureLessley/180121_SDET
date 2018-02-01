package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
	public static Connection getConnection() throws SQLException{
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
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"animals",
					"animals"
				);
	}
}
