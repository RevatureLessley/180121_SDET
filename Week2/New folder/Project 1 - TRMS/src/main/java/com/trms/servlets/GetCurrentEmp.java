package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.beans.Employee;
import com.trms.services.EmployeeService;

/**
 * Servlet implementation class GetCurrentEmp
 */
public class GetCurrentEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Employee emp = EmployeeService.getEmpByUsername(request
				.getParameter("username"));
		// System.out.println(emp);
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		if (emp != null) {
			String xml = "<root><employee>"
					+ "<empid>" + emp.getEmpId() + "</empid>"
					+ "<username>" + emp.getUsername() + "</username>"
					+ "<firstname>" + emp.getFname()+ "</firstname>" 
					+ "<lastname>" + emp.getLname() + "</lastname>"
					+ "<email>" + emp.getEmail() + "</email>"
					+ "<title>" + emp.getTitle() + "</title>"
					+ "<supervisor>" + emp.getSupervisor() + "</supervisor>"
					+ "<address>" + (emp.getAddress() == null ? " ": emp.getAddress()) + "</address>"
					+ "<city>" + (emp.getCity() == null ? " " : emp.getCity())+ "</city>"
					+ "<state>" + (emp.getState()==null?" ":emp.getState())+ "</state>"
					+ "<tel>" + (emp.getTel()==null?" ":emp.getTel())+ "</tel>"
					+ "</employee></root>";
			out.print(xml);
		} else {
			out.print("<root></root>");
		}
	}

}
