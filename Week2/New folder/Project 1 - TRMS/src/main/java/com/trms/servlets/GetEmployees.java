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
		//State == 3
		
		/*
		 * In the case of a default AJAX request, we will return back to it,
		 * an xml file, so we need to set the response type to reflect that.
		 */
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		List<Employee> emps = EmployeeService.getAllEmployees();
		
		
		if(emps!=null){
			String myXml = "<root>";
			//Manually create the xml file.
//			for(Employee e : emps){
//				myXml += "<employee><e_id>" + e.getEmpId() + "</e_id>" +
//						"<e_name>" + e.getEmpName() + "</e_name>"
//								+ "<e_sal>" + e.getEmpSalary() + "</e_sal>"
//								+ "<e_title>" + e.getTitle() + "</e_title></employee>";
//			}
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
