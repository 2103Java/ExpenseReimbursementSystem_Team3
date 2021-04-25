package com.teamthree.models;

public class User {

	String username; // unique primary key
	String password;
	String dateOfBirth;
	String accessLevel;  // "customer" or "employee" or "admin"
	String firstName;
	String lastName;
	
	
	public User(String username, String password, String dateOfBirth, String accessLevel, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.accessLevel = accessLevel;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getAccessLevel() {
		return accessLevel;
	}


	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
	
	
}
