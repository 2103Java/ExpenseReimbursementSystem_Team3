package com.teamthree.dao;

import java.util.ArrayList;

import com.teamthree.models.Ticket;

public interface TicketDao {

	ArrayList<Ticket> getTicketsForUsername(String username);
	ArrayList<Ticket> getAllTickets();
	boolean addTicket(Ticket t);
	boolean deleteTicket(int ticket_id);
	boolean updateTicketStatusByTicketID(int ticket_id, String newStatus);
	
}
