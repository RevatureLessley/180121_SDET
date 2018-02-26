package com.trms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.beans.Employee;
import com.trms.services.EmployeeService;

/**
 * Servlet implementation class UpdateEmployee
 */
public class UpdateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		Employee newEmp = new Employee();
		
		newEmp.setFname(request.getParameter("firstname"));
		newEmp.setLname(request.getParameter("lastname"));
		newEmp.setEmail(request.getParameter("email"));
		newEmp.setAddress(request.getParameter("address"));
		newEmp.setCity(request.getParameter("city"));
		newEmp.setState(request.getParameter("state"));
		newEmp.setTel(request.getParameter("tel"));
		
		EmployeeService.updateEmpByUsername(username, newEmp);
		
	}

}
