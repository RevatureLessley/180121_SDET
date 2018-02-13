package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class CreateSession
 */
public class CreateSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		//Provides the created session for a whatever user accessed the site last.
		//HOWEVER, if it is the first time this computer accessed the site, it will create
		//a new session for this computer.
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(session.isNew()){
			session.setAttribute("username", request.getParameter("username"));
			session.setAttribute("password", request.getParameter("password"));
			session.setAttribute("logged in", new Boolean(true));
			//session.setMaxInactiveInterval(5);
			
			out.println("<h3>SESSION CREATED FOR " + 
						((String)session.getAttribute("username")).toUpperCase() + "!</h3>");
		}else{
			out.print("<h4>Session already active for current user: " + 
						session.getAttribute("username") + "...</h4>");
		}
		HtmlTemplates.goBackButton(out);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
