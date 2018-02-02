package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
	public static Connection getConnection() throws SQLException{

		/*
		 * ClassLoader
		 * -The classloader is a tool that is utilized at runtime. It is what
		 * Java uses to bring in all the classes to be used by our application at
		 * runtime.
		 * -It also has the added benefit of manually bring in classes to be used
		 * for execution.
		 */
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"MiniProject2",
					"miniproject2"
				);
	}
}
