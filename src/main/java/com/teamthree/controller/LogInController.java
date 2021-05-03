package com.teamthree.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.teamthree.models.User;
import com.teamthree.service.HelpDesk;

public class LogInController {

	HelpDesk helpDesk;
	
	public final static Logger logger = Logger.getLogger(LogInController.class);
	
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
		System.out.println("username = "+username);
		System.out.println("password = "+password);
		
		couldLogIn = helpDesk.tryLogIn(username, password);
		logger.info("Attempting to log in as user: "+username);
		
		if (couldLogIn) {
			System.out.println("  :)    Creating session because username was found & password matched!");
			logger.info("--->  Successfully logged in as user "+username);
			
			HttpSession session = req.getSession();
			
			User userObjForLoggedInUser = helpDesk.findUser(username);
			
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			session.setAttribute("firstName", userObjForLoggedInUser.getFirstName());
			session.setAttribute("lastName", userObjForLoggedInUser.getLastName());
			session.setAttribute("firstName", userObjForLoggedInUser.getFirstName());
			session.setAttribute("dateOfBirth", userObjForLoggedInUser.getDateOfBirth());
			session.setAttribute("accessLevel", userObjForLoggedInUser.getAccessLevel());			
			
		}
		else {
			System.out.println("  :(     Could not log in. No session created.");
			logger.info("--->  Unable to log in");
			resp.setStatus(213);
		}
		
	
		
	}
	
	
	
	
	public void logUserOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("LogInController: logUserOut()");
	
		if (req.getSession(false) != null) {
			logger.info("--->  User "+req.getSession().getAttribute("username") +  " logged out." );
			req.getSession().invalidate();
		}

			resp.sendRedirect("/ERS/site/Home");

	}
	
}
