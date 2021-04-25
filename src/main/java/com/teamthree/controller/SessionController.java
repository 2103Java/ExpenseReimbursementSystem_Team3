package com.teamthree.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamthree.service.HelpDesk;

public class SessionController {
	
	private HelpDesk helpDesk;
	
	public SessionController(HelpDesk helpDesk) {
		this.helpDesk = helpDesk;
	}

	
	
	public void getUserForSession(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		
		System.out.println("");
		System.out.println("************* Sending user session name in response");
		// getSessionUser(req, resp) gives an HTTP response with
		// a JSON object for the current session user, if there is one,
		// otherwise null is returned
		
		
		if (req.getSession(false) != null) {
			resp.setContentType("json/application");
			ObjectMapper om = new ObjectMapper();
			resp.getWriter().write(om.writeValueAsString(req.getSession(false).getAttribute("username")));
			
		}
		
	}
	
	
	public void getAccessLevel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		// Give an HTTP Response with level = none, customer, or admin
		
		HttpSession session = req.getSession(false);
		
		if (session == null) {
			resp.getWriter().write("none");
		}
		else {
			resp.getWriter().write("customer");			
		}
		
		
	}
	
}
