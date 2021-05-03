package com.teamthree.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.teamthree.models.User;
import com.teamthree.utility.Connector;



public class UserDaoImpl implements UserDao {

	
	public User getUserFromUsername(String username) {

		User returnUser = null;
		String sqlRead = "SELECT * FROM users WHERE username = (?)";
		PreparedStatement psREAD;
		
		try(Connection conn = Connector.getConnection()) {
			
			psREAD = conn.prepareStatement(sqlRead);
			psREAD.setString(1,username);
			ResultSet rsREAD = psREAD.executeQuery();
			
			while (rsREAD.next()) {
				returnUser = new User(rsREAD.getString("username"),
										rsREAD.getString("password"),
										rsREAD.getString("date_of_birth"),
										rsREAD.getString("access_level"),
										rsREAD.getString("first_name"),
										rsREAD.getString("last_name")
									);
				
			}
			
			
		} catch (SQLException e) {
	
	//		e.printStackTrace();
		//	System.out.println("==> could not find user with username"+username);
		}
		
		return returnUser;
		
	}
	
	public ArrayList<User> getAllUsers() {
		
		ArrayList<User> allTheUsers = new ArrayList<User>();
		
		User returnUser = null;
		String sqlRead = "SELECT * FROM users";
		PreparedStatement psREAD;
		
		try(Connection conn = Connector.getConnection()) {
			
			psREAD = conn.prepareStatement(sqlRead);
			ResultSet rsREAD = psREAD.executeQuery();
			
			while (rsREAD.next()) {
				User aUser = new User(rsREAD.getString("username"),
										rsREAD.getString("password"),
										rsREAD.getString("date_of_birth"),
										rsREAD.getString("access_level"),
										rsREAD.getString("first_name"),
										rsREAD.getString("last_name")
									);
				allTheUsers.add(aUser);
				
			}
			
			
		} catch (SQLException e) {
	
	//		e.printStackTrace();
		//	System.out.println("==> could not find user with username"+username);
		}
		
		return allTheUsers;
		
	}
	
	public boolean addUser(String username, String password, String dOB, String accessLevel, String fName, String lName) {
		
		User u = new User(username, password, dOB, accessLevel, fName, lName);
		
		System.out.println("Dob to insert is "+u.getDateOfBirth());
		String sqlRead = "INSERT INTO users values (?,?,?,?,?,?)";
		PreparedStatement pswrite;
		
		try(Connection conn = Connector.getConnection()) {
			
			pswrite = conn.prepareStatement(sqlRead);
			pswrite.setString(1,u.getUsername());
			pswrite.setString(2,u.getPassword());
			pswrite.setString(3,u.getDateOfBirth());
			pswrite.setString(4,u.getAccessLevel());
			pswrite.setString(5,u.getFirstName());
			pswrite.setString(6,u.getLastName());
			boolean rswrite = pswrite.execute();
			
		} catch (SQLException e) {

			//e.printStackTrace();
		//	System.out.println("==> could not add user with username"+userArg.getUsername());
			return false;
		}
	
		return true;
	}
	
	
}
