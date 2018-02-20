package com.project1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project1.beans.Employee;
import com.project1.dao.EmployeeDaoImpl;
/**
 * Servlet implementation class GetEmployees
 */
public class GetPersonalInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		EmployeeDaoImpl dao = new EmployeeDaoImpl();
		List<Employee> emps = dao.getAllEmployees();
		
		if(emps!=null){
			String myXml = "<root>";
			//Manually create the xml file.
			for(Employee e : emps){
				myXml += "<employee>"
								+ "<email>" + e.getEmail() + "</email>" 
								+ "<firstname>" + e.getFirstName() + "</firstname>"
								+ "<lastname>" + e.getLastName() + "</lastname>"
								+ "<address>" + e.getAddress() + "</address>"
								+ "<jobtitle>" + e.getJobTitle() + "</jobtitle>"
								+ "<date>" + e.getDate() + "</date>"
						+ "</employee>";
			}
			myXml += "</root>";
			
			out.print(myXml); //State == 4
		}else{
			out.print("<root></root>"); //State == 4
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
