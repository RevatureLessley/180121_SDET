package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.beans.Employee;
import com.trms.services.EmployeeService;

/**
 * Servlet implementation class GetEmployees
 */
public class GetEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		List<Employee> emps = EmployeeService.getAllEmployees();

		if(emps!=null){
			String myXml = "<root>";
			for(Employee emp : emps){
				myXml += "<employee>"
						+ "<empid>" + emp.getEmpId() + "</empid>"
						+ "<username>" + emp.getUsername() + "</username>"
						+ "<firstname>" + emp.getFname()+ "</firstname>" 
						+ "<lastname>" + emp.getLname() + "</lastname>"
						+ "<title>" + emp.getTitle() + "</title>"
						+ "<supervisor>" + emp.getSupervisor() + "</supervisor>"
						+ "<email>" + emp.getEmail() + "</email>"
						+ "<address>" + (emp.getAddress() == null ? " ": emp.getAddress()) + "</address>"
						+ "<city>" + (emp.getCity() == null ? " " : emp.getCity())+ "</city>"
						+ "<state>" + (emp.getState()==null?" ":emp.getState())+ "</state>"
						+ "<tel>" + (emp.getTel()==null?" ":emp.getTel())+ "</tel>"
						+ "</employee>";
			}
			myXml += "</root>";
			
			out.print(myXml); 
		}else{
			out.print("<root></root>"); 
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
