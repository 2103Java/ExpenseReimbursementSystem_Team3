package com.teamthree.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamthree.service.HelpDesk;

public class RegistrationController {

	private HelpDesk helpDesk;

	public RegistrationController(HelpDesk helpDesk) {
		this.helpDesk = helpDesk;
	}
	
	
	public void goToRegisterUserPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			RequestDispatcher userHomeDispatcher = req.getRequestDispatcher("/register.html");
			userHomeDispatcher.forward(req, resp);
			
	}
	
	public void goToRegisterSuccessfulPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//RequestDispatcher userHomeDispatcher = req.getRequestDispatcher("/registersuccessful.html");
		//userHomeDispatcher.forward(req, resp);
		
}
		
}
