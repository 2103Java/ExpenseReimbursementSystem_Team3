package com.teamthree.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.teamthree.dao.TicketDaoImpl;
import com.teamthree.dao.UserDaoImpl;
import com.teamthree.models.Ticket;
import com.teamthree.models.User;

public class HelpDesk implements ServiceLayer {

	String testUsername = "Bob";
	String testPassword = "password";
	
	private UserDaoImpl userDao;
	private TicketDaoImpl ticketDao;
	
	public HelpDesk(UserDaoImpl userDao, TicketDaoImpl ticketDao) {
		this.userDao = userDao;
		this.ticketDao = ticketDao;
	}
	
	public boolean tryLogIn(String username, String password) {

		if (username.equals(testUsername) && password.equals(testPassword)) {
			return true;
		}
		return false;
		
		
	}
	
	
	public User findUser(String username) {
		
		return userDao.getUserFromUsername(username);
		
	}
	
	public ArrayList<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	public boolean createNewUser(String username, String password, String dOB, String accessLevel, String fName, String lName) {
		User newUser = new User(username, password, dOB, accessLevel, fName, lName);
		boolean addedUser = userDao.addUser(newUser);
		return addedUser;
	}
	
	public ArrayList<Ticket> getTicketsForUsername(String username) {
		
		return ticketDao.getTicketsForUsername(username);
		
	}
	
	public boolean generateTicket(String username, double amount, String description, String type) {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		Ticket newTicket = new Ticket(-1, "pending", amount, timestamp, description, username, type);
		
		return ticketDao.addTicket(newTicket);
	}
	
	public boolean deleteTicket(Ticket t) {
		
		return ticketDao.deleteTicket(t.getId());
		
	}
	
	public boolean deleteTicketFromTicketID(int ticket_id) {
		
		return ticketDao.deleteTicket(ticket_id);
		
	}
	
	public boolean changeTicketStatus(int id, String newStatus) {
		return ticketDao.updateTicketStatusByTicketID(id, newStatus);
	}

	public ArrayList<Ticket> getAllTickets() {
		return ticketDao.getAllTickets();
	}
}
