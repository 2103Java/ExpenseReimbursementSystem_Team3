package com.teamthree.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamthree.service.HelpDesk;

public class UserHomeController {

	private HelpDesk helpDesk;
	
	public UserHomeController(HelpDesk helpDesk) {
		this.helpDesk = helpDesk;
	}
	
	public void goToUserHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher userHomeDispatcher = req.getRequestDispatcher("/userhome.html");
		userHomeDispatcher.include(req, resp);
		
	}
}
