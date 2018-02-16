package com.revature.serlvets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.DataService;

/**
 * Servlet implementation class RegisterEmp
 */
public class RegisterEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text");
		PrintWriter out = response.getWriter();
		
		int emp_id = Integer.parseInt(request.getParameter("empid"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		String department = request.getParameter("department");;
		int sup_id = Integer.parseInt(request.getParameter("supid"));
		int amount = 1000;
		
		//if(!DataService.dupName(username)){
			DataService.insertEmployee(emp_id, fname, lname, username, password, email, role, department, sup_id, amount);
				out.print("Employee Inserted!");
				//}else{//
		//		out.print("Employee Insertion Failed.");
	//		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
