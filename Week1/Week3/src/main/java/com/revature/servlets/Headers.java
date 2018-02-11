package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class Headers
 */
public class Headers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration headers = request.getHeaderNames();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String header = "";
		out.println("<table border=2px><tr><th>Header Name</th><th>Header Value</th></tr>");
		while(headers.hasMoreElements()) {
			header = (String)headers.nextElement();
			out.println("<tr><td>" + header + "</td><td>" + request.getHeader(header) + "</td></tr>");
		}
		
		out.println("</table>");
		
		HtmlTemplates.goBackButton(out);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
