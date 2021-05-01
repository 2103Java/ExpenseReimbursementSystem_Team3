package com.teamthree.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamthree.service.HelpDesk;

public class UserController {

	
	private HelpDesk helpDesk;
	
	public UserController(HelpDesk helpDesk) {
		this.helpDesk = helpDesk;
	}
	
	
	public boolean createNewUser(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println("xXxXxXxXxXxXxXxXxXxX\nUSER CONTROLLER CLASS\nxXxXxXxXxXxXxXxXxXxX");
		System.out.println("createNewUser()");
		String newUsername = req.getParameter("username");
		String newPassword = req.getParameter("password");
		String newDateOfBirth = req.getParameter("dOB");
		String newAccessCode = req.getParameter("accessCode");
		String newFirstName = req.getParameter("firstName");
		String newLastName = req.getParameter("lastName");
		newDateOfBirth = "01/01/2000";
		
		System.out.println("newUsername = "+newUsername);
		System.out.println("newPassword = "+newPassword);
		System.out.println("newAccessCode = "+newAccessCode);
		System.out.println("newFirstName = "+newFirstName);
		System.out.println("newLastName = "+newLastName);
		
		// Find if this username is available
		boolean usernameAvailable = helpDesk.isUsernameAvailable(newUsername);
		
		if (!usernameAvailable) {
			resp.setStatus(213);
			return false;
		}
		String newAccessLevel;
		if (Integer.parseInt(newAccessCode) == RequestHelper.employeeAccessCode) {
			newAccessLevel = "customer";
		}
		else if (Integer.parseInt(newAccessCode) == RequestHelper.adminAccessCode) {
			newAccessLevel = "customer";
		}
		else {
			resp.setStatus(217);
			return false;
		}
		helpDesk.createNewUser(newUsername, newPassword, newDateOfBirth, newAccessLevel, newFirstName, newLastName);
		
		return true;
	}
	
	public void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
		
	}
	
	public void updateUser(HttpServletRequest req, HttpServletResponse resp) {
		
	}
	
	public void getUser(HttpServletRequest req, HttpServletResponse resp) {
		
	}
	
	
	public void goToUserHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("UserController --> goToUserPage()");		
		RequestDispatcher userHomeDispatcher = req.getRequestDispatcher("/userhome.html");
		userHomeDispatcher.forward(req, resp);
		
	}
	
	public void goToViewAllTicketsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("UserController --> goToViewAllTicketsPage()");
		RequestDispatcher userHomeDispatcher = req.getRequestDispatcher("/ADMINalltickets.html");
		userHomeDispatcher.forward(req, resp);
		
	}
	
}
