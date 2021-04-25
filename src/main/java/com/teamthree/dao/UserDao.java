package com.teamthree.dao;

import java.util.ArrayList;

import com.teamthree.models.User;

public interface UserDao {

	ArrayList<User> getAllUsers();
	boolean addUser(User u);
	User getUserFromUsername(String username);
}
