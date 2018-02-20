package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.beans.Employee;

/**
 * Servlet implementation class Tuition
 */
public class Tuition extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In tuition servlet");
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<h1>hi</h1>");
		
//		System.out.println("In Tuition servlet");
//		Tuition t = new Tuition();
//		e.setFirstName(request.getParameter("firstname"));
//		e.setLastName(request.getParameter("lastname"));
//		e.setTitle(request.getParameter("title"));
//		e.setUserName(request.getParameter("username"));
//		e.setPassword(request.getParameter("password"));
//		e.setEmail(request.getParameter("email"));
//		e.setSuper_firstName(request.getParameter("s_firstname"));
//		e.setSuper_lastName(request.getParameter("s_lastname"));
//		System.out.println(e);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
