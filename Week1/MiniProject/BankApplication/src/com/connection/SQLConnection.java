package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private final static String USER = "SYSTEM";
    private final static String PASSWORD = "badpassword";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}
