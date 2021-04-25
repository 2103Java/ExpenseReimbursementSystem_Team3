package com.teamthree.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.teamthree.models.Ticket;
import com.teamthree.models.User;
import com.teamthree.utility.Connector;

public class TicketDaoImpl implements TicketDao {

	
	public ArrayList<Ticket> getTicketsForUsername(String username) {
		ArrayList<Ticket> returnList = new ArrayList<Ticket>();
		
		String sqlRead = "SELECT * FROM tickets where username_fk = (?)";
		PreparedStatement psREAD;
		
		try(Connection conn = Connector.getConnection()) {
			
			psREAD = conn.prepareStatement(sqlRead);
			psREAD.setString(1, username);
			ResultSet rsREAD = psREAD.executeQuery();
			
			while (rsREAD.next()) {
				Ticket aTicket = new Ticket(
									rsREAD.getInt("ticket_id"),
									rsREAD.getString("status"),
									rsREAD.getDouble("amount"),
									rsREAD.getTimestamp("time_stamp"),
									rsREAD.getString("description"),
									rsREAD.getString("username_fk"),
									rsREAD.getString("type")
						);
						
				returnList.add(aTicket);
				
			}
			
			
		} catch (SQLException e) {
	
	//		e.printStackTrace();
		//	System.out.println("==> could not find user with username"+username);
		}
		
		return returnList;
		
	}
	
	
	
	public boolean addTicket(Ticket aTicket) {
		

		String sqlRead = "INSERT INTO tickets (status, amount, time_stamp, description, type, username_fk) values (?,?,?,?,?,?)";
		PreparedStatement pswrite;
		
		try(Connection conn = Connector.getConnection()) {
			
			pswrite = conn.prepareStatement(sqlRead);
			pswrite.setString(1,aTicket.getStatus());
			pswrite.setDouble(2,aTicket.getReimburseAmount());
			pswrite.setTimestamp(3, aTicket.getTimeStamp());
			pswrite.setString(4,aTicket.getDescription());
			pswrite.setString(5,aTicket.getType());
			pswrite.setString(6,aTicket.getUsername());
			boolean rswrite = pswrite.execute();
			
		} catch (SQLException e) {

			//e.printStackTrace();
		//	System.out.println("==> could not add user with username"+userArg.getUsername());
			return false;
		}
	
		return true;
	}
	
	
	public boolean deleteTicket(int id) {
		
		String sqlRead = "DELETE FROM tickets where ticket_id = (?)";
		PreparedStatement pswrite;
		
		try(Connection conn = Connector.getConnection()) {
			
			pswrite = conn.prepareStatement(sqlRead);
			pswrite.setInt(1,id);
			
			boolean rswrite = pswrite.execute();
			
		} catch (SQLException e) {

			//e.printStackTrace();
		//	System.out.println("==> could not add user with username"+userArg.getUsername());
			return false;
		}
	
		return true;
		
	}
	
	public boolean updateTicketStatusByTicketID(int id, String newStatus) {

		
		String sqlRead = "UPDATE tickets SET status = (?) where ticket_id = (?)";
		PreparedStatement pswrite;
		
		try(Connection conn = Connector.getConnection()) {
			
			pswrite = conn.prepareStatement(sqlRead);
			pswrite.setString(1,newStatus);
			pswrite.setInt(2,id);
			
			boolean rswrite = pswrite.execute();
			
		} catch (SQLException e) {

			//e.printStackTrace();
		//	System.out.println("==> could not add user with username"+userArg.getUsername());
			return false;
		}
	
		return true;
	
		
	}
	
	
	
	public ArrayList<Ticket> getAllTickets() {
		
		ArrayList<Ticket> allTickets = new ArrayList<Ticket>();
		
		String sqlRead = "SELECT * FROM tickets";
		PreparedStatement psREAD;
		
		try(Connection conn = Connector.getConnection()) {
			
			psREAD = conn.prepareStatement(sqlRead);
			ResultSet rsREAD = psREAD.executeQuery();
			
			
			// Ticket(Integer id, String status, double reimburseAmount, Timestamp timeStamp, String description, String username, String type)
			while (rsREAD.next()) {
				Ticket aTicket = new Ticket(rsREAD.getInt("ticket_id"),
										rsREAD.getString("status"),
										rsREAD.getDouble("amount"),
										rsREAD.getTimestamp("time_stamp"),
										rsREAD.getString("description"),
										rsREAD.getString("username_fk"),
										rsREAD.getString("type")
									);
				allTickets.add(aTicket);
				
			}
			
			
		} catch (SQLException e) {
	
	//		e.printStackTrace();
		//	System.out.println("==> could not find user with username"+username);
		}
		
		return allTickets;
	}
}
