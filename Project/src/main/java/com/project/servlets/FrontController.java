package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		System.out.println("FRONT CONTROLLER LOADED");
		
	}
	
	@Override
	public void destroy() {
		System.out.println("FRONT CONTROLLER DESTROYED");
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getRequestURI(); 
		System.out.println(url);
		RequestDispatcher rd;
		
		String[] tokens = url.split("/");
		String action = (tokens[tokens.length-1]);
		action = action.substring(0, action.length()-3).toLowerCase();
		
		System.out.println(action);
		switch(action){
		case "login":
			System.out.println("frontcontroller login");
			rd = request.getRequestDispatcher("login");
			rd.forward(request, response);
			break;
		case "register":
			System.out.println("frontcontroller register");
			rd = request.getRequestDispatcher("Register");
			rd.forward(request, response);
			break;
		case "tuitionform":
			System.out.println("frontcontroller tuition");
			rd = request.getRequestDispatcher("TuitionForm");
			System.out.println("request dispather");
			rd.forward(request, response);
			break;
		case "logout":
			System.out.println("frontcontroller logout");
			HttpSession session = request.getSession(false);
			if(session!=null){
				System.out.println(session.getAttribute("username"));
				session.invalidate();
				System.out.println("Session deleted");
			}
			response.sendRedirect("../index.html");
			break;
		default:
			System.out.println("error");
			response.sendError(404);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
