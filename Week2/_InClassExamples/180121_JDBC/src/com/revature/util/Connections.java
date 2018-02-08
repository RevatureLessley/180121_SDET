package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connections {
<<<<<<< HEAD
	
	private static Properties prop = null;
	private final static String FILE_NAME = "dbprops.properties";
	
=======
	private static Properties prop = null;
	private final static String FILE_NAME = "dbprops.properties";
>>>>>>> 4333b3961e29bca8b88ed71f9acb85f09c3262d2
	public static Connection getConnection() throws SQLException{
		try{
			prop = new Properties();
			prop.load(new FileInputStream(FILE_NAME));
		}catch(IOException e){
			e.printStackTrace();
		}
<<<<<<< HEAD
=======
		
>>>>>>> 4333b3961e29bca8b88ed71f9acb85f09c3262d2
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
		String props[] = System.getenv("DBProps").split(";");

		try {
<<<<<<< HEAD
			Class.forName(prop.getProperty("class"));
=======
			//Class.forName(prop.getProperty("class"));
			Class.forName(props[0]);
>>>>>>> 4333b3961e29bca8b88ed71f9acb85f09c3262d2
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return DriverManager.getConnection(
<<<<<<< HEAD
					prop.getProperty("url"),
					prop.getProperty("username"),
					prop.getProperty("password")
					);
=======
				props[1],
				props[2],
				props[3]
					//prop.getProperty("url"),
					//prop.getProperty("username"),
					//prop.getProperty("password")
				);
>>>>>>> 4333b3961e29bca8b88ed71f9acb85f09c3262d2
	}
}
