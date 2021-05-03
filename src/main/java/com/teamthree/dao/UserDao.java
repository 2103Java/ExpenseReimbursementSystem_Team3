package com.teamthree.dao;

import java.util.ArrayList;

import com.teamthree.models.User;

public interface UserDao {

	ArrayList<User> getAllUsers();
	boolean addUser(String username, String password, String dOB, String accessLevel, String fName, String lName);
	User getUserFromUsername(String username);
}
