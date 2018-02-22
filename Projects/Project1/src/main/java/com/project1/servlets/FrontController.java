package com.project1.servlets;

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
	
	/*
	 * Front controller:
	 * -Is a design pattern.
	 * -The front controller design pattern is built to designate a single servlet
	 * for handling all requests and dispatching to proper servlets for handling.
	 * The goal is ensure that one servlets acts as the gatekeeper for the rest of the
	 * application.	 
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("front controller reached");
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(username + " " + password);
		String url = request.getRequestURI(); //Stores the url in a string
		RequestDispatcher rd;
		String[] tokens = url.split("/");
		String action = (tokens[tokens.length-1]);
		action = action.substring(0, action.length()-3).toLowerCase();
		
		//servlets to handle request
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
