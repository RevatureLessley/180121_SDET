package SAS.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class SASConnect{
	private static Properties prop = null;
	private final static String ODBP = "oracledbprops.properties";

	//file path /Users/Juan/Documents/ORCLSQLDOCS/oracledbprops.properties 
	public static Connection getConnection() throws SQLException {
		try {
			FileInputStream fs = new FileInputStream(ODBP);
			// string array of properties from database to pass into my driver-manager
			prop = new Properties();
			prop.load(fs);

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(prop.getProperty("class"));
		try {
		Class.forName(prop.getProperty("class"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
		// prop.getProperty("url"),
		// prop.getProperty("username"),
		// prop.getProperty("password")
	}

}



