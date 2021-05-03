package com.teamthree.dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.teamthree.models.Ticket;

public interface TicketDao {

	ArrayList<Ticket> getTicketsForUsername(String username);
	ArrayList<Ticket> getAllTickets();
	boolean addTicket(String status, double amount, Timestamp timestamp, String description, String username, String type);
	boolean deleteTicket(int ticket_id);
	boolean updateTicketStatusByTicketID(int ticket_id, String newStatus);
	
}
