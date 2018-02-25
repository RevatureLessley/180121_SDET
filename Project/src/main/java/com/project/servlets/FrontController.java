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
		case "home":
			System.out.println("frontcontroller home");
			rd = request.getRequestDispatcher("Home");
			rd.forward(request, response);
			break;
		case "register":
			System.out.println("frontcontroller register");
			rd = request.getRequestDispatcher("Register");
			rd.forward(request, response);
			break;
		case "tuitionform":
			System.out.println("frontcontroller tuitionform");
			rd = request.getRequestDispatcher("TuitionForm");
			rd.forward(request, response);
			break;
		case "tuitionstatus":
			System.out.println("frontcontroller tuitionStatus");
			rd = request.getRequestDispatcher("TuitionStatus");
			rd.forward(request, response);
			break;
		case "tuitionapprove":
			System.out.println("frontcontroller tuitionapprove");
			rd = request.getRequestDispatcher("TuitionApprove");
			rd.forward(request, response);
			break;
		case "approverequest":
			System.out.println("frontcontroller approverequest");
			rd = request.getRequestDispatcher("ApproveRequest");
			rd.forward(request, response);
			break;
		case "moreinfo":
			System.out.println("frontcontroller moreinfo");
			rd = request.getRequestDispatcher("MoreInfo");
			rd.forward(request, response);
			break;
		case "reject":
			System.out.println("frontcontroller reject");
			rd = request.getRequestDispatcher("Reject");
			rd.forward(request, response);
			break;
		case "deletetuition":
			System.out.println("frontcontroller deletetuition");
			rd = request.getRequestDispatcher("DeleteTuition");
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
