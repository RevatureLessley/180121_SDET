package com.project1.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project1.services.ValidateLogin;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login servlet reached");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
//		HttpSession session = request.getSession();
//		if(!session.isNew()){
//			out.println("<h1>Stop being weird.</h1>");
//			session.invalidate();
		
		if(ValidateLogin.validate(email, password)){
			//session.setAttribute("username", email);
			RequestDispatcher rd = request.getRequestDispatcher("account.html");
			rd.forward(request, response);
		}else{
			//session.invalidate();
			
			out.print("<h1>INVALID CREDENTIALS</h1>");
			//HtmlTemplates.goBackButton(out);
			
		}
		
	}

}
