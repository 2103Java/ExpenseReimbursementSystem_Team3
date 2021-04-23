package com.teamthree.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamthree.controller.RequestHelper;

public class MasterServlet extends HttpServlet {

	RequestHelper rh;
	
	public void init() throws ServletException {
		super.init();
		rh = new RequestHelper();
		System.out.println("^^^^ MASTER SERVLET BORN ^^^^");
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("\n--> REQUEST RECEIVED! HI");
		//super.service(req, resp);
		
		HttpServletRequest httpRequest;
		HttpServletResponse httpResponse;
		
		if (!(req instanceof HttpServletRequest && resp instanceof HttpServletResponse)) {
			throw new ServletException("Not a HTTP request or response");
		}
		
		httpRequest = (HttpServletRequest) req;
		httpResponse = (HttpServletResponse) resp;
		
		rh.process(httpRequest, httpResponse);
		
	}
	
	
}
