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
	private RegistrationController registrationController;
	
	
	
	
	public RequestHelper() {
		
		helpDesk = new HelpDesk();
		
		homeController = new HomeController(helpDesk);
		sessionController = new SessionController(helpDesk);
		userController = new UserController(helpDesk);
		ticketController = new TicketController(helpDesk);
		logInController = new LogInController(helpDesk);
		registrationController = new RegistrationController(helpDesk);
		
	}
	

	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		StringBuffer url = req.getRequestURL();
		System.out.println("URL: "+url +",  method: "+req.getMethod());
		String uri = req.getRequestURI();
		String method = req.getMethod();

		/*
		 *
		 * URL SUMMARY:
		 * 
		 * ERS/site/*    ---->   THESE ARE THE VISIBLE HTML PAGES (ie, Home, Login, Register, etc...)
		 * 
		 * ERS/api/*   (ERS/api/user & ERS/api/ticket)  -----> THESE ARE THE URLS FOR RETRIEVING, UPDATING, OR DELETING DATABASE ITEMS
		 * 														USED WITH AJAX IN JAVASCRIPT
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
			registrationController.goToRegisterUserPage(req, resp);
			
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
			
			this.disablePageCaching(resp);
			
			registrationController.goToRegisterSuccessfulPage(req, resp);
			
			break;
			
			
		case "/ERS/site/ForgotPassword":
			
			this.disablePageCaching(resp);
			logInController.goToForgotPasswordPage(req, resp);
			break;
			
		case "/ERS/site/EmailSent":
			
			this.disablePageCaching(resp);
			logInController.goToRecoveryEmailSendPage(req,resp);
			break;
			
			
		case "/ERS/site/CreateTicket":
			
			this.disablePageCaching(resp);
			ticketController.goToCreateTicketPage(req, resp);
			break;

		
		// ADMIN ONLY
		case "/ERS/site/ModifyTicket":

			this.disablePageCaching(resp);
			ticketController.goToModifyTicketPage(req, resp);
			break;
			
			
			
		case "/ERS/site/TicketCreated":
			
			this.disablePageCaching(resp);
			ticketController.goToTicketCreatedPage(req, resp);
			break;
			
		
			
			
			
			
			

		case "/ERS/api/user":
			
			switch(method) {
			
			case "GET":
				userController.getUser(req, req);
				break;
				
			case "POST":
				userController.createNewUser(req, req);
				break;
				
			case "UPDATE":
				userController.updateUser(req, req);
				break;
				
			case "DELETE":
				userController.deleteUser(req, req);
				break;
				
			}

			break;
			
			
		case "/ERS/api/ticket":
			
			switch(method) {
			
			case "GET":
				ticketController.getTicket(req, req);
				break;
				
			case "POST":
				ticketController.createNewTicket(req, req);
				break;
				
			case "UPDATE":
				ticketController.updateTicket(req, req);
				break;
				
			case "DELETE":
				ticketController.deleteTicket(req, req);
				break;
				
			}
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
					String mode = req.getParameter("mode");
					System.out.println("mode is = "+mode);
					if (mode.equals("getAccessLevel")) {
						sessionController.getAccessLevel(req, resp);	
					}
					else if (mode.equals("getUsername")){
						sessionController.getUserForSession(req, resp);
					}
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
