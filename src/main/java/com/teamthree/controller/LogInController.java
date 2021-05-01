package com.teamthree.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamthree.models.User;
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
		String username = req.getHeader("username");
		String password = req.getHeader("password");
		
		System.out.println("req.username = "+username);
		couldLogIn = helpDesk.tryLogIn(username, password);
		
		if (couldLogIn) {
			System.out.println("  :)    Creating session because username was found & password matched!");
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
			resp.setStatus(213);
		}
		
	
		/*
		 *
		 * CHANGED : No longer need to redirect. Now using AJAX instead of FORM to log in, so as to get feedback
		 * 
		if (couldLogIn) {
			resp.sendRedirect("/ERS/site/UserHome");
		}
		else {
			resp.sendRedirect("/ERS/site/Home");
		}*/
		
	}
	
	
	
	
	public void logUserOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("LogInController: logUserOut()");
	
		if (req.getSession(false) != null) {
			req.getSession().invalidate();
		}

			resp.sendRedirect("/ERS/site/Home");

	}
	
	
	
	
	
	
	public void goToForgotPasswordPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("GOTOFORGOTPASSWORDPAGE method start");
		RequestDispatcher fPWDispatcher = req.getRequestDispatcher("/TEST_forgotpassword.html");
		fPWDispatcher.forward(req, resp);
		
	}

	public void goToRecoveryEmailSendPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("GOTORECOVERYEMAILSENTPAGE method start");
		RequestDispatcher emailSentDispatcher = req.getRequestDispatcher("/TEST_recoveryemailsent.html");
		emailSentDispatcher.forward(req, resp);
		
	}
	
}
