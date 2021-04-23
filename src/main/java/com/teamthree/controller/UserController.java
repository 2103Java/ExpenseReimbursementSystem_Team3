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
	
	
	
	
	public void createNewUser(HttpServletRequest req, HttpServletRequest resp) {
		
	}
	
	public void deleteUser(HttpServletRequest req, HttpServletRequest resp) {
		
	}
	
	public void updateUser(HttpServletRequest req, HttpServletRequest resp) {
		
	}
	
	public void getUser(HttpServletRequest req, HttpServletRequest resp) {
		
	}
	
	
	public void goToUserHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("UserController --> goToUserPage()");
		System.out.println("\nARG");
		RequestDispatcher userHomeDispatcher = req.getRequestDispatcher("/OLD_userhome.html");
		userHomeDispatcher.forward(req, resp);
		
	}
	
	public void goToRegisterUserPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher userHomeDispatcher = req.getRequestDispatcher("/register.html");
		userHomeDispatcher.forward(req, resp);
		
	}
	
}
