package com.teamthree.driver;

import java.sql.Connection;

import com.teamthree.utility.Connector;

public class MainDriver {

	
	public static void main(String[] args) {
		
		System.out.println("Hi");
		
		Connection aConnection = Connector.getConnection();
	}
}
