package Connections;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;

public class SQLConnection {
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private final static String USER = "system";
    private final static String PASSWORD = "badpassword123";
    public static Connection getConnection() throws SQLException {
    	DriverManager.registerDriver(new OracleDriver());
			return DriverManager.getConnection(
	                URL,
	                USER,
	                PASSWORD
	        );
    }
}
