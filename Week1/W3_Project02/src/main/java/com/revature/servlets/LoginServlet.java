package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.services.ValidateLogin;
import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		if(!session.isNew()) {
			out.println("<h1>Stop being weird.</h1>");
		} else if(session.isNew() && ValidateLogin.validate(username, password)){
			session.setAttribute("username", username);
			RequestDispatcher rd = request.getRequestDispatcher("user/usersite.html");
			rd.forward(request, response);
			out.println("<h1>Logged</h1>");
		} else {
			session.invalidate();
		}
		
		HtmlTemplates.goBackButton(out);
	}

}
