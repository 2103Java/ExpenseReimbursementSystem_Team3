package com.teamthree.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamthree.models.Ticket;
import com.teamthree.service.HelpDesk;
import com.teamthree.utility.Connector;

public class TicketController {

	HelpDesk helpDesk;
	
	public TicketController(HelpDesk helpDesk) {
		this.helpDesk = helpDesk;
	}
	
	

	public void createOrUpdateTicket(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String mode = req.getParameter("mode");
		
		System.out.println("CreateOrUpdateTicket.mode = "+mode);
		Enumeration<String> paramNames = req.getParameterNames();
		System.out.println("paramNames = "+paramNames);
		while (paramNames.hasMoreElements()) {
			System.out.println("parameter: "+paramNames.nextElement());
		}
		
		if (mode == null) {
			resp.setStatus(210);
			return;
		}
		

		if (mode.equals("update")) {
			String ticketIDString = req.getParameter("ticketid");
			String newTicketStatus = req.getParameter("status");
			System.out.println("ticketid = "+ticketIDString);
			if (ticketIDString == null) {
				resp.setStatus(210);
				return;
			}
			int ticketID = Integer.parseInt(ticketIDString);
			helpDesk.changeTicketStatus(ticketID,newTicketStatus);
			
			// Only admins can update a ticket so redirect them back to the admin-only AllTickets URL after updating a ticket
			// Also I had to use a form for the dynamically generated HTML buttons that trigger updateTicket, so have to redirect
			//RequestDispatcher disp = req.getRequestDispatcher("/site/AllTickets");
			//disp.forward(req, resp);
			
			//resp.sendRedirect("/ERS/site/AllTickets");
		}
		
		if (mode.equals("create")) {
			System.out.println("~-~-~-~-~-~-~-~-~-~-~-~-~");
			System.out.println("   TICKET CREATE MODE");
			System.out.println("~-~-~-~-~-~-~-~-~-~-~-~-~");
			
			String ticketType = req.getParameter("type");
			String ticketAmountString = req.getParameter("amount");
			String ticketDescription = req.getParameter("description");
			
			System.out.println("ticketType = "+ticketType);
			System.out.println("ticketAmountString = "+ticketAmountString);
			System.out.println("ticketDescription = "+ticketDescription);
			
			helpDesk.generateTicket((String)req.getSession(false).getAttribute("username"), Double.parseDouble(ticketAmountString), ticketDescription, ticketType);
		}
		
	}
	
	public void deleteTicket(HttpServletRequest req, HttpServletResponse resp) {
		
	}
	
	public void updateTicket(HttpServletRequest req, HttpServletResponse resp) {
		
	}
	
	public void getTicket(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
	
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		
		System.out.println("TicketController.class: getTicket()");
		
		
		String mode = req.getHeader("mode");
		System.out.println("mode = "+mode);
		
		if (mode == null) {
			System.out.println("Must pass in 'mode' = 'ticketid', 'username', or 'all'");
			setStatusInvalidArgs(resp);
			return;
		}
		if (mode.equals("ticketid")) {
			String ticketID = req.getHeader("ticketid");
			System.out.println("ticketid = "+ticketID);
			if (ticketID == null) {
				setStatusInvalidArgs(resp);
				return;
			}

			Ticket t = helpDesk.getTicketByID(Integer.parseInt(ticketID));
			System.out.println("t = "+t);
			System.out.println("t.id = "+t.getId());
			ObjectMapper om = new ObjectMapper();
			resp.getWriter().write(om.writeValueAsString(t));
			
			return;
			
		}
		else if (mode.equals("username")) {
			
			String username = req.getHeader("username");
			if (username == null) {
				setStatusInvalidArgs(resp);
				return;
			}
			ArrayList<Ticket> ticketsForUser = helpDesk.getTicketsForUsername(username);

			ObjectMapper om = new ObjectMapper();
			resp.getWriter().write(om.writeValueAsString(ticketsForUser));
			
		}
		
		else if (mode.equals("all")) {
			
			ArrayList<Ticket> allTickets= helpDesk.getAllTickets();

			ObjectMapper om = new ObjectMapper();
			resp.getWriter().write(om.writeValueAsString(allTickets));
						
		}
		
	
		
		
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
	
	public void setStatusInvalidArgs(HttpServletResponse resp) {
		resp.setStatus(210);
	}
}
