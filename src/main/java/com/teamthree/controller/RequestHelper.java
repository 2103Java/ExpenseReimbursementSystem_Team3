package com.teamthree.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamthree.service.HelpDesk;

public class RequestHelper {
	
	// Service layer
	private HelpDesk helpDesk;
	
	// Controllers
	private HomeController homeController;
	private UserController userController;
	private TicketController ticketController;
	private SessionController sessionController;
	private LogInController logInController;
	
	
	
	
	
	public RequestHelper() {
		
		helpDesk = new HelpDesk();
		
		homeController = new HomeController(helpDesk);
		sessionController = new SessionController(helpDesk);
		userController = new UserController(helpDesk);
		logInController = new LogInController(helpDesk);
		
	}
	

	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("---> RequestHelper process(): request type = "+req.getMethod());
		
		StringBuffer url = req.getRequestURL();
		System.out.println("Process --> URL is "+url);
		String uri = req.getRequestURI();
		String method = req.getMethod();

		/*
		 *
		 * URL SUMMARY:
		 * 
		 * ERS/site/*    ---->   THESE ARE THE VISIBLE HTML PAGES (ie, Home, Login, Register, etc...)
		 * 
		 * ERS/api/*   (ERS/api/user & ERS/api/ticket)  -----> THESE ARE THE URLS FOR RETRIEVING, UPDATING, OR DELETING DATABASE ITEMS
		 * 
		 * ERS/util/*  -----> THESE ARE THE URLS FOR UTILITY TYPE FUNCTIONS SUCH AS LOGGING IN AND OUT/ CREATING OR INVALIDATING A SESSION.
		 *                     MOST/ALL OF THESE WILL REDIRECT TO A VISIBLE ERS/site/* URL AFTER THE FUNCTION IS COMPLETE
		 * 
		 */
		
		switch(uri) {
	
		
		
		// TRY TO LOG IN from HOME/LOG IN PAGE
		// NAVIGATE TO HOME/LOG IN PAGE
			
		case "/ERS/site/Home":

			System.out.println("HOME/LOG-IN PAGE: time: "+System.currentTimeMillis());
			
			// Really important: Fixes issues with browser caching where the same page is reused rather than sending a new request for it (Really nice while creating server)			 
			this.disablePageCaching(resp);

			switch (method) {
			
				case "GET":
				case "POST":
					homeController.goToLogInPage(req,resp);
					break;
					
				default:
				
			}

			break;
			
			
		case "/ERS/site/Register":
			System.out.println("REGISTRATION PAGE");
			
			this.disablePageCaching(resp);
			userController.goToRegisterUserPage(req, resp);
			
			break;
			
			
			
		// LOG OUT FROM UserHome PAGE
			
		case "/ERS/site/UserHome":
			System.out.println("USER HOME SCREEN");
			
			this.disablePageCaching(resp);
			
			switch (method) {
			
				case "GET":	
				case "POST":
				
					userController.goToUserHomePage(req,resp);
					break;
				
			}

			break;
			
		case "/ERS/site/RegistrationComplete":
			
			
			break;
			
			
		case "/ERS/site/ForgotPassword":
			
			break;
			
		case "/ERS/site/ForgotPassword/EmailSent":
			
			break;
			
			
		case "/ERS/site/CreateTicket":
			
			break;

		
		// ADMIN ONLY
		case "/ERS/site/ModifyTicket":

			
			break;
			
			
			
		case "/ERS/site/TicketCreated":
			
			break;
			
		

		case "/ERS/api/users":
			
			break;
			
			
			
			
			
		case "/ERS/api/tickets":
			
			break;
			
			
			
		case "/ERS/util/changeloginstatus":
			
			this.disablePageCaching(resp);
			
			switch (method) {
			
				case "GET":
					
					logInController.logUserOut(req, resp);
					break;
					
				case "POST":
					
					logInController.logUserIn(req, resp);
					break;
				
			}
			
			break;
			
			
			
		case "/ERS/util/session":
			this.disablePageCaching(resp);
			System.out.println("Session API");
			switch (method) {
				
				case "GET":
					System.out.println("Session API: Get");
					sessionController.putUserForSessionInResponse(req, resp);
					break;
					
			}
			
			break;
				
		
			
			
			
	
			
		default:
			System.out.println("DEFAULT PAGE");
			resp.sendRedirect("http://localhost:9500/ERS/home.html");
		}
	}
	
	public void disablePageCaching(HttpServletResponse resp) {
		resp.setHeader("Cache-Control", "max-age=0, must-revalidate, no-cache, no-store, private, post-check=0, pre-check=0");  // HTTP 1.1
		resp.setDateHeader ("Expires", 0);          // prevents caching at the proxy server
	}
}
