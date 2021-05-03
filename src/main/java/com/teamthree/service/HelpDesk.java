package com.teamthree.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.teamthree.dao.TicketDaoImpl;
import com.teamthree.dao.UserDaoImpl;
import com.teamthree.models.Ticket;
import com.teamthree.models.User;

public class HelpDesk implements ServiceLayer {

	String testUsername = "Bob";
	String testPassword = "password";
	
	private UserDaoImpl userDao;
	private TicketDaoImpl ticketDao;
	
	public final static Logger logger = Logger.getLogger(HelpDesk.class);
	
	
	public HelpDesk(UserDaoImpl userDao, TicketDaoImpl ticketDao) {
		this.userDao = userDao;
		this.ticketDao = ticketDao;
	}
	
	public boolean tryLogIn(String username, String password) {

		System.out.println("TRY LOG IN METHOD");
		
		
		
		User user = findUser(username);
		
		
		if (user == null) {
		
			
			return false;
			
		}
		if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
			
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
		if (username.equals("") || password.equals("") || dOB.equals("") || accessLevel.equals("") || fName == "" || lName.equals("")) {
			return false;
		}
		boolean addedUser = userDao.addUser(username, password, dOB, accessLevel, fName, lName);
		
		return addedUser;
	}
	
	public ArrayList<Ticket> getTicketsForUsername(String username) {
		
		return ticketDao.getTicketsForUsername(username);
		
	}

	public Ticket getTicketByID(int id) {
		if (id < 0) {
			return null;
		}
		Ticket t = ticketDao.getTicketFromID(id);
		return t; 
	}
	
	public boolean generateTicket(String username, double amount, String description, String type) {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		return ticketDao.addTicket("Pending", amount, timestamp, description, username, type);
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

	public boolean isUsernameAvailable(String newUsername) {
		
		User currentUserWithThisUsername = userDao.getUserFromUsername(newUsername);
		if (currentUserWithThisUsername == null) {
			return true;
		}
		return false;

	}
}
