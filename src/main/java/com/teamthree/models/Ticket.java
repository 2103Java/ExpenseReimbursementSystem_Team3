package com.teamthree.models;

public class Ticket {

	private int id;  // unique primary key
	private double reimburseAmount;
	private double timeStamp;
	private String description;
	private String username;  // foreign key into the User table
	
	
	
	
	public Ticket(int id, double reimburseAmount, double timeStamp, String description, String username) {
		
		super();
		this.id = id;
		this.reimburseAmount = reimburseAmount;
		this.timeStamp = timeStamp;
		this.description = description;
		this.username = username;
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getReimburseAmount() {
		return reimburseAmount;
	}
	public void setReimburseAmount(double reimburseAmount) {
		this.reimburseAmount = reimburseAmount;
	}
	public double getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(double timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
