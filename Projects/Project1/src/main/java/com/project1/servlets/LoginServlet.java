package com.project1.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project1.services.AccountServices;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login servlet reached");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		RequestDispatcher rd;
		HttpSession session = request.getSession(true);
		System.out.println(session.isNew());
		
		if(session.isNew() && AccountServices.validate(email, password)){
			if (!AccountServices.approved(email)) {
				session.invalidate();
				response.sendRedirect("notapproved.html");
			} else if (AccountServices.specialAccount(email)){
				session.setAttribute("email", email);
				rd = request.getRequestDispatcher("accountpageadmin.jsp");
				rd.forward(request, response);
			} else {
				session.setAttribute("email", email);
				rd = request.getRequestDispatcher("accountpage.jsp");
				rd.forward(request, response);
			}
		} else if(!session.isNew()){
				rd = request.getRequestDispatcher("accountpageadmin.jsp");
				rd.forward(request, response);
		} else {
			session.invalidate();
			rd = request.getRequestDispatcher("signin.html");
			rd.forward(request, response);
		}
	}
}
