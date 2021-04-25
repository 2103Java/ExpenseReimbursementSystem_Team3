package com.teamthree.models;

import java.sql.Date;
import java.sql.Timestamp;

public class Ticket {

	private Integer id;  // unique primary key
	private String status;
	private double reimburseAmount;
	private Timestamp timeStamp;
	private String description;
	private String username;  // foreign key into the User table
	private String type;
	
	
	
	
	public Ticket(Integer id, String status, double reimburseAmount, Timestamp timeStamp, String description, String username, String type) {
		
		super();
		this.id = id;
		this.status = status;
		this.reimburseAmount = reimburseAmount;
		this.timeStamp = timeStamp;
		this.description = description;
		this.username = username;
		this.type = type;
		
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getReimburseAmount() {
		return reimburseAmount;
	}
	public void setReimburseAmount(double reimburseAmount) {
		this.reimburseAmount = reimburseAmount;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
