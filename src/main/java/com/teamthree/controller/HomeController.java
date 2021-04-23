package com.teamthree.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamthree.service.HelpDesk;

public class HomeController {

	
	HelpDesk helpDesk;
	
	public HomeController(HelpDesk helpDesk) {
		this.helpDesk = helpDesk;
	}
	
	
	public void goToLogInPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("HomeController: goToLogInPage()!");
		System.out.println("HI");

		RequestDispatcher loginDispatcher = req.getRequestDispatcher("/home.html");
		loginDispatcher.forward(req, resp);
		
	}

	

}
