package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormsServlet
 */
public class FormsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String sName;
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println(config.getServletName() + " instantiated");
		sName = config.getServletName();
	}


	public void destroy() {
		System.out.println(sName + " destroyed.");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<h1>GET</h1>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<h1>POST</h1>");
	}

}
