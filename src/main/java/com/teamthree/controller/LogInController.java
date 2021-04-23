package com.teamthree.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamthree.service.HelpDesk;

public class LogInController {

	HelpDesk helpDesk;
	
	// Reminder to self: Specifying a constructor w/ args overrides the no-args constructor
	public LogInController(HelpDesk helpDesk) {
		this.helpDesk = helpDesk;
	}
	
	public void logUserIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("LogInController: logUserIn()");
		
		boolean couldLogIn = false;
		
		if (req.getSession(false) != null) {
			req.getSession().invalidate();
		}

		// Get user and pw from http request
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		couldLogIn = helpDesk.tryLogIn(username, password);
		
		if (couldLogIn) {
			System.out.println("  :)    Creating session because username was found & password matched!");
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", password);
		}
		else {
			System.out.println("  :(     Could not log in. No session created.");
		}
		
	
		if (couldLogIn) {
			resp.sendRedirect("/ERS/site/UserHome");
		}
		else {
			resp.sendRedirect("/ERS/site/Home");
		}
		
	}
	
	
	
	
	public void logUserOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("LogInController: logUserOut()");
	
		if (req.getSession(false) != null) {
			req.getSession().invalidate();
		}

			resp.sendRedirect("/ERS/site/Home");

	}
	
	
	
	
	public void goToLogInPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.sendRedirect("/ERS/site/LogIn");
		
	}
	
}
