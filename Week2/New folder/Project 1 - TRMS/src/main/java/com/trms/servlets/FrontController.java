package com.trms.servlets;

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
		RequestDispatcher rd;
		
		String[] tokens = url.split("/");
		String action = (tokens[tokens.length-1]);
		action = action.substring(0, action.length()-3).toLowerCase();
		
		switch(action){
		case "login":
			rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
			break;
		case "register":
			rd = request.getRequestDispatcher("RegisterServlet");
			rd.forward(request, response);
			break;
		case "submitform":
			rd = request.getRequestDispatcher("../SubmitForm");
			rd.forward(request, response);
			break;
		case "svaction":
			rd = request.getRequestDispatcher("/SupervisorAction");
			rd.forward(request, response);
			break;
		case "dhaction":
			rd = request.getRequestDispatcher("/DhAction");
			rd.forward(request, response);
			break;
		case "bcaction":
			rd = request.getRequestDispatcher("/BcAction");
			rd.forward(request, response);
			break;
		default:
			response.sendError(404);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
