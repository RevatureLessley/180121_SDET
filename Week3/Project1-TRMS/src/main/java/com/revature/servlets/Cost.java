package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.service.DataService;
/**
 * Servlet implementation class Cost
 */
public class Cost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		int emp_id = (int) session.getAttribute("empid");
		
			String myXml = "<root>";
				myXml += "<amount><cost>" + DataService.getCostAmount(emp_id) + "</cost></amount>";
			
			myXml += "</root>";
			out.print(myXml); //State == 4
		
	}

}
