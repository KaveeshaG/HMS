package com.hcs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	 static Connection conn = null;

	    public static Connection getConnection() throws SQLException {
//	        if (conn != null) {
//	            return conn;
//	        }

	        String database = "HCS";
	        String Username = "root";
	        String password = "sithara@123";
	        return getConnection(database, Username, password);
	    }

	    private static Connection getConnection(String databaseName, String UserName, String password) throws SQLException {

	        if (conn == null || conn.isClosed()) {
	            try {
	                Class.forName("com.mysql.jdbc.Driver");
	                conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/" + databaseName + "?user=" + UserName + "&password=" + password);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	        return conn;
	    }

}
