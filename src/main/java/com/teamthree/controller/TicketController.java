package com.teamthree.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamthree.service.HelpDesk;

public class TicketController {

	HelpDesk helpDesk;
	
	public TicketController(HelpDesk helpDesk) {
		this.helpDesk = helpDesk;
	}
	
	

	public void createNewTicket(HttpServletRequest req, HttpServletRequest resp) {
		
	}
	
	public void deleteTicket(HttpServletRequest req, HttpServletRequest resp) {
		
	}
	
	public void updateTicket(HttpServletRequest req, HttpServletRequest resp) {
		
	}
	
	public void getTicket(HttpServletRequest req, HttpServletRequest resp) {
	
		// Will return a JSON single ticket OR a list of JSON tickets (in response body) depending on parameter passed in from request
		
		
	}
	
	
	public void goToCreateTicketPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("GOTOCREATETICKETPAGE method start");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/createticket.html");
		dispatcher.forward(req, resp);
		
	}



	public void goToModifyTicketPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("GOTOCREATETICKETPAGE method start");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/TEST_modifyticket.html");
		dispatcher.forward(req, resp);
	
		
		
	}



	public void goToTicketCreatedPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("GOTOCREATETICKETPAGE method start");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/TEST_ticketcreated.html");
		dispatcher.forward(req, resp);
		
	}
}
