package com.teamthree.controller;

import javax.servlet.http.HttpServletRequest;

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
}
