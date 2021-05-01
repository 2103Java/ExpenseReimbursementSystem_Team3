package com.teamthree.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	private static final String URL = System.getenv("Rev_Project1_URL");
	private static final String USERNAME = System.getenv("Rev_Project1_USERNAME");
	private static final String PASSWORD = System.getenv("Rev_Project1_PASSWORD");
	
	private static Connection conn;
	
	public static Connection getConnection() {
		
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//System.out.println("Could not connect");
		}
		
		return conn;
	}
}
