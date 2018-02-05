package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
	
//	private static Properties prop = null;
//	private final static String FILE_NAME = "dbprops.properties";

	
	public static Connection getConnection() throws SQLException{

/*		try{
			prop = new Properties();
			prop.load(new FileInputStream(FILE_NAME));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
*/	
		
		String props[] = System.getenv("DBProps").split(";");
		
		
		try {
			Class.forName(props[0]);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
				
		return DriverManager.getConnection(props[1], props[2],props[3]);
	}
	

}
