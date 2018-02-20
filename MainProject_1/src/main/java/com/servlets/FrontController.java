package com.servlets;

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

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI(); //Stores the url in a string
		System.out.println(url);
		RequestDispatcher rd;
		
		String[] tokens = url.split("/");
		String action = (tokens[tokens.length-1]);
		action = action.substring(0, action.length()-3).toLowerCase();
		
		System.out.println(action);
		
	
		switch(action){
		case "register":
			rd = request.getRequestDispatcher("RegisterServlet");
			rd.forward(request, response);
			break;
		
		case "login":
			rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
			break;
			
		case "adminlogin":
			rd = request.getRequestDispatcher("AdminLoginServlet");
			rd.forward(request, response);
			break;
			
		case "request":
			rd = request.getRequestDispatcher("SendRequestServlet");
			rd.forward(request, response);
			break;
			
		case "superupdaterequest":
			rd = request.getRequestDispatcher("SupervisorRequestUpdate");
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