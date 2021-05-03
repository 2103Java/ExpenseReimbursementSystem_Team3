import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.Test;
import org.mockito.Mock;

import com.teamthree.dao.TicketDaoImpl;
import com.teamthree.dao.UserDaoImpl;
import com.teamthree.models.Ticket;
import com.teamthree.models.User;
import com.teamthree.service.HelpDesk;

public class HelpDeskTests {

	@Mock
	private UserDaoImpl userDao;
	@Mock
	private TicketDaoImpl ticketDao;
	
	
	@Test
	public void testTryLogIn() {
	
		userDao = mock(UserDaoImpl.class);

		
		HelpDesk spyHelpDesk = spy(new HelpDesk(userDao, ticketDao));
		
		// Log in with valid information test
		when(spyHelpDesk.findUser("bob23")).thenReturn(new User("bob23", "password4", "05/07/1907", "employee", "Bob", "Jenkins"));
		boolean loggedIn = spyHelpDesk.tryLogIn("bob23", "password4");
		assertTrue(loggedIn);
		
		// Log in with wrong password test
		when(spyHelpDesk.findUser("bob23")).thenReturn(new User("bob23", "password4", "05/07/1907", "employee", "Bob", "Jenkins"));
		loggedIn = spyHelpDesk.tryLogIn("bob23", "password_321111");
		assertTrue( ! loggedIn);
		
		// Log in with user that does not exist in system
		when(spyHelpDesk.findUser("derrick24")).thenReturn(null);
		loggedIn = spyHelpDesk.tryLogIn("derrick24", "12345678");
		assertTrue( ! loggedIn);
		
	}
	
	@Test
	public void testCreateNewUser() {
		
		userDao = mock(UserDaoImpl.class);
		

		// Create user when username does not exist
		when(userDao.addUser("bob55", "password1", "01/01/2000", "employee", "Bob", "Jenkins")).thenReturn(true);
		HelpDesk hD = new HelpDesk(userDao, ticketDao);
		assertTrue(hD.createNewUser("bob55", "password1", "01/01/2000", "employee", "Bob", "Jenkins"));
		
		// Add user when user does exist
		when(userDao.addUser( "bob55", "password1", "01/01/2000", "employee", "Bob", "Jenkins" )).thenReturn(false);
		hD = new HelpDesk(userDao, ticketDao);
		assertFalse(hD.createNewUser("bob55", "password1", "01/01/2000", "employee", "Bob", "Jenkins"));
		
		// Attempt add user with a blank field of any type
		when(userDao.addUser( "bob55", "password1", "01/01/2000", "", "Bob", "Jenkins" )).thenReturn(false);
		hD = new HelpDesk(userDao, ticketDao);
		assertFalse(hD.createNewUser("bob55", "password1", "01/01/2000", "", "Bob", "Jenkins"));
		
		
	}
	
	@Test
	public void testGetTicketByID() {
		
		// Get a ticket by ID when it exists
		ticketDao = mock(TicketDaoImpl.class);
		when(ticketDao.getTicketFromID(10)).thenReturn(new Ticket(10,"Pending",400.22,null,"Spanish restaurant","bob23","Food"));
		HelpDesk hd = new HelpDesk(null, ticketDao);
		Ticket ticketThatExists = hd.getTicketByID(10);
		assertTrue(ticketThatExists != null);
		assertTrue(ticketThatExists.getId() == 10);
		
		// Try to get a ticket when negative id is specified
		ticketDao = mock(TicketDaoImpl.class);
		when(ticketDao.getTicketFromID(-10)).thenReturn(null);
		hd = new HelpDesk(null, ticketDao);
		Ticket ticketDoesNotExist1 = hd.getTicketByID(-10);
		assertTrue(ticketDoesNotExist1 == null);
		
		// Try to get a ticket by ID when it does not exist
		ticketDao = mock(TicketDaoImpl.class);
		when(ticketDao.getTicketFromID(20)).thenReturn(null);
		hd = new HelpDesk(null, ticketDao);
		Ticket ticketThatDoesNotExist2 = hd.getTicketByID(20);
		assertTrue(ticketThatDoesNotExist2 == null);
		
	}
	
	@Test
	public void testGenerateTicket() {
		
		ticketDao = mock(TicketDaoImpl.class);
		
		// Successful ticket generation
		when(ticketDao.addTicket(eq("Pending"), eq(800.00), any(Timestamp.class), eq("Fuel"), eq("bob23"), eq("Travel"))).thenReturn(true);
		HelpDesk hd = new HelpDesk(userDao, ticketDao);
		assertTrue(hd.generateTicket("bob23", 800.0,"Fuel", "Travel"));
	}
	
	@Test
	public void testIsUsernameAvailable() {
		
		userDao = mock(UserDaoImpl.class);
		
		when(userDao.getUserFromUsername("bob23")).thenReturn(new User("bob23","password","01/01/1900","employee","Bob","Jenkins"));
		HelpDesk hD = new HelpDesk(userDao, null);
		assertFalse(hD.isUsernameAvailable("bob23"));
		
		when(userDao.getUserFromUsername("bob6000")).thenReturn(null);
		hD = new HelpDesk(userDao, null);
		assertTrue(hD.isUsernameAvailable("bob6000"));
		
	}
	
	
	
	
	
	
	
}
