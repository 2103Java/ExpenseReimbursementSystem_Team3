package com.teamthree.driver;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.teamthree.dao.TicketDaoImpl;
import com.teamthree.dao.UserDaoImpl;
import com.teamthree.models.Ticket;
import com.teamthree.models.User;
import com.teamthree.service.HelpDesk;

public class MainDriver {

	

	
	public static void main(String[] args) {
		
		System.out.println("Hi");
		

		
		UserDaoImpl userDao = new UserDaoImpl();
		TicketDaoImpl ticketDao = new TicketDaoImpl();
		
		HelpDesk helpDesk = new HelpDesk(userDao, ticketDao);
		
		User aUser = helpDesk.findUser("bob23");
		if (aUser == null) {
			System.out.println("Couldnt find user");
		}
		else {
			System.out.println("Found user: username = "+aUser.getUsername()+", password = "+aUser.getPassword());
		}
		
		System.out.println("testing viewing all users");
		ArrayList<User> allUsers = userDao.getAllUsers();
		for (User u: allUsers) {
			System.out.println("User: username = "+u.getUsername());
			System.out.println("User: password = "+u.getPassword());
		}
		
		System.out.println("TESTING INSERTING A USER INTO DATABASE");
		
		//userDao.addUser(new User("dadboy21","coolpassword","04/20/2011","admin","Mr.","Man"));
		
		
		System.out.println("TESTING FETCHING TICKETS FOR A USERNAME");
		
		ArrayList<Ticket> someTicketsForBob = helpDesk.getTicketsForUsername("bob23");
		
		for (Ticket t : someTicketsForBob) {
			System.out.println("ticket id = "+t.getId());
			System.out.println("amount = "+t.getReimburseAmount());
		}
		
		
		System.out.println("TEST ADDING A TICKET");
		helpDesk.generateTicket("bob23", 1000.00, "Lodging related costs in New Mexico", "lodging");
		
		helpDesk.deleteTicketFromTicketID(2);
		
		helpDesk.changeTicketStatus(3,"approved");
		
		System.out.println("SHOWING ALL TICKETS");
		ArrayList<Ticket> allTickets = helpDesk.getAllTickets();
		for (Ticket t: allTickets) {
			System.out.println("TICKET ID: "+t.getId());
			System.out.println("TICKET AMOUNT: "+t.getReimburseAmount());
			System.out.println("TICKET STATUS: "+t.getStatus());
		}
		
		Ticket someTicket = helpDesk.getTicketByID(3);
		System.out.println("someTicket = "+someTicket);
	}
}
