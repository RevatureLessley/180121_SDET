package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		System.out.println("FRONT CONTROLLER LOADED");
			
		System.out.println("=====" + this.getServletName() + "=====");
		System.out.println("ConfigVariable: " + this.getInitParameter("configVar"));
		System.out.println("ConfigVariableForLogin: " + this.getInitParameter("loginVar"));
		System.out.println("ContextVariable: " + this.getServletContext().getInitParameter("ContextBob"));

	}
	
	@Override
	public void destroy() {
		System.out.println("FRONT CONTROLLER DESTROYED");
	}
	
	
	/*
	 * Front controller:
	 * -Is a design pattern.
	 * -The front controller design pattern is built to designate a single servlet
	 * for handling all requests and dispatching to proper servlets for handling.
	 * The goal is ensure that one servlets acts as the gatekeeper for the rest of the
	 * application.	 
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI(); //Stores the url in a string
		System.out.println(url);
		RequestDispatcher rd;

		System.out.println("=====" + this.getServletName() + "=====");
		System.out.println("ConfigVariable: " + this.getInitParameter("ConfigVar"));
		System.out.println("ConfigVariableForLogin: " + this.getInitParameter("loginVar"));
		System.out.println("ContextVariable: " + this.getServletContext().getInitParameter("ContextBob"));

		
		String[] tokens = url.split("/");
		String action = (tokens[tokens.length-1]);
		action = action.substring(0, action.length()-3).toLowerCase();
		
		System.out.println(action);
		
	
		switch(action){
		case "login":
			rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
			break;
		default:
			response.sendError(404);
		}
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
