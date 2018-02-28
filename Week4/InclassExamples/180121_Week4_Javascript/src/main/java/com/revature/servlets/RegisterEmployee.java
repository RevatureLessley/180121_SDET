package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.EmployeeService;

/**
 * Servlet implementation class RegisterEmployee
 */
public class RegisterEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		Integer salary = Integer.parseInt(request.getParameter("salary"));
		String title = request.getParameter("title");
		
		if(!EmployeeService.nameTaken(name)){
			if(EmployeeService.insertEmployee(name, salary, title)){
				out.print("Employee Inserted!");
			}else{
				out.print("Employee Insertion Failed.");
			}
			
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
