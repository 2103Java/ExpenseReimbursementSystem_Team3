package com.teamthree.service;

import java.util.ArrayList;

import com.teamthree.models.Ticket;
import com.teamthree.models.User;

public interface ServiceLayer {

	User findUser(String username);
	ArrayList<User> getAllUsers();
	boolean createNewUser(String username, String password, String dateOfBirth, String accessLevel, String firstName, String lastName);
	ArrayList<Ticket> getTicketsForUsername(String username);
	boolean generateTicket(String username, double amount, String description, String type);
	boolean deleteTicket(Ticket ticket);
	boolean deleteTicketFromTicketID(int ticket_id);
	boolean changeTicketStatus(int ticket_id, String newStatus);
	ArrayList<Ticket> getAllTickets();
}
