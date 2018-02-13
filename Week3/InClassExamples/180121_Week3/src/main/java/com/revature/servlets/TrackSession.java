package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;

import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class TrackSession
 */
public class TrackSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		//If parameter is false, do NOT create a session if one does not exist
		/*
		 * If a user gets a session for the first time, you are given
		 * a brand new session. If a session already exists, you are given
		 * the already created session.
		 */
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(session==null){
			out.println("<h1>WHO ARE YOU, GET OUR OF HERE!</h1>");
			HtmlTemplates.goBackButton(out);
			
			//session.invalidate(); //Deletes created session
		}
		
		else{
			out.print("<h3>WELCOME BACK, " + session.getAttribute("username") + "!</h3>");
		
			out.println(
						"<table border = 2px>"
					);
			out.println("<tr><th>USERNAME</th><td>" + session.getAttribute("username") + "</td></tr>");
			out.println("<tr><th>PASSWORD</th><td>" + session.getAttribute("password") + "</td></tr>");			
			out.println("<tr><th>CREATION TIME</th><td>" + session.getCreationTime() + "</td></tr>");
			out.println("<tr><th>LAST ACCESS</th><td>" + session.getLastAccessedTime() + "</td></tr>");
			out.println("<tr><th>SESSION ID</th><td>" + session.getId() + "</td></tr>");
			out.println("</table>");
			
			HtmlTemplates.goBackButton(out);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
