package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.beans.Employee;
import com.project.dao.EmployeeDao;
import com.project.dao.EmployeeDaoImp;
import com.project.services.EmployeeServices;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In Register servlet");
		Employee e = new Employee();
		e.setFirstName(request.getParameter("firstname"));
		e.setLastName(request.getParameter("lastname"));
		e.setTitle(request.getParameter("title"));
		e.setUserName(request.getParameter("username"));
		e.setPassword(request.getParameter("password"));
		e.setEmail(request.getParameter("email"));
		e.setSuper_firstName(request.getParameter("s_firstname"));
		e.setSuper_lastName(request.getParameter("s_lastname"));
		System.out.println(e);
	
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		if(!EmployeeServices.validateUsername(e.getUserName())){
			EmployeeDao dao = new EmployeeDaoImp();
			if(dao.addEmployee(e)){
				out.print("<root><result id='rs'>success</result></root>");
				System.out.println("success");
			}else {
				out.print("<root><result id='rs'>failed</result></root>");
				System.out.println("failed");
			}
		}else {
			out.print("<root><result id='rs'>UserNameTaken</result></root>");
			System.out.println("UserNameTaken");
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
