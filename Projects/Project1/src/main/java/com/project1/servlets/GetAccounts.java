package com.project1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project1.beans.Personal;
import com.project1.dao.TRMSDaoImpl;

/**
 * Servlet implementation class GetEmployees
 */
public class GetAccounts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TRMSDaoImpl dao = new TRMSDaoImpl();
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		List<Personal> people = dao.getAllPersonal();
		
		if(people!=null){
			String myXml = "<root>";
			for(Personal e : people){
				myXml += "<person><email>" + e.getEmail() + "</email>" +
						"<fname>" + e.getFirstName() + "<fname>"
								+ "<lname>" + e.getLastName() + "<lname></person>";
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
