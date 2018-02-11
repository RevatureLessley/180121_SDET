package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.services.UserRegisterService;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean success = UserRegisterService.insertUser(Integer.parseInt(request.getParameter("empid")), request.getParameter("email"),
				request.getParameter("username"), request.getParameter("password"));
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(success) {
			out.println("Registartion complete! Redirecting!");
		} else {
			out.println("Registration fail! Redirecting try again!");
			//response.sendRedirect("RegisterServlet");
			/*
			RequestDispatcher rd = request.getRequestDispatcher("RegisterServlet");
			rd.forward(request, response);
			*/
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
