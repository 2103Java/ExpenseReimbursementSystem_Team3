package com.teamthree.service;

public class HelpDesk implements ServiceLayer {

	String testUsername = "Bob";
	String testPassword = "password";
	
	public boolean tryLogIn(String username, String password) {

		if (username.equals(testUsername) && password.equals(testPassword)) {
			return true;
		}
		return false;
		
		
	}
	

}
