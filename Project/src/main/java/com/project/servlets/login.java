package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.services.EmployeeServices;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		System.out.println("sesion created");
		if(EmployeeServices.validateLogin(username, password)){
			System.out.println("login success");
			session.setAttribute("username", username);
			System.out.println(session.getAttribute("username"));
			response.sendRedirect("user/tuitionForm.html");
		}else{
			session.invalidate();
			System.out.println("session deleted");
			out.print("<h1>INVALID CREDENTIALS</h1>");
			out.println("<hr><input type='button' value='GO BACK' onclick='goBack()'>"
					+ "<script>function goBack(){ window.history.back(); }</script>");
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
