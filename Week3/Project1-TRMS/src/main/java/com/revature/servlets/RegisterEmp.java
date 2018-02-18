package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.DataService;

/**
 * Servlet implementation class RegisterEmp
 * This servlet is used to be the middle man between the Registration page and the class DataService.
 */
public class RegisterEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**doGet() will call doPost() as it will be more secure for handling such sensitive information.**/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { doPost(request, response);}

	/**doPost() will take in all the values from the form that is in the request, and pass them to DataService's method
	 * insertEmployee() as arguments. The amount will be preset to 1000 as it is a new registration.**/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int emp_id = Integer.parseInt(request.getParameter("empid"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		String department = request.getParameter("departments");
		int sup_id = Integer.parseInt(request.getParameter("supid"));
		int amount = 1000;
		
		DataService.insertEmployee(emp_id, fname, lname, username, password, email, role, department, sup_id, amount);
	}

}
