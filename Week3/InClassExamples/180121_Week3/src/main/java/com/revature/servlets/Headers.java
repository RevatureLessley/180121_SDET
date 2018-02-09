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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration headers = request.getHeaderNames();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String header = "";
		out.println("<table border=2px><tr><th>Header Name</th><th>Header Value</th></tr>");
		while(headers.hasMoreElements()){
			header = (String)headers.nextElement();
			out.println("<tr><td>" + header + "</td><td>" + request.getHeader(header) + "</td></tr>");
		}
		
		out.println("</table>");
		HtmlTemplates.goBackButton(out);
		
		/*
		 * REQUEST HEADERS
		 * -Both requests and responses have key/value pairs that represent meta data about
		 * each one. These are called the request/response headers.
		 * For request, typical headers can include:
		 * -Accept: Shows which file types are allowed to be returned/received
		 * -Authorization: header used to identify itself
		 * -Connection: Header for determining how long a connection lasts
		 * -Cookie: storing cookies previously recieved by browser (client)
		 * -Host: Specifies host and port
		 * -user agent: Identify browser that is being used.
		 * 	-Can have special reactions to specific browsers.
		 * 
		 * RESPONSE HEADERS
		 * -Allow: Specifies which HTTP methods are supported by the server.
		 * -Connection: Determining whether or not to persist connections to server
		 * -expires: Dictates when page should no longer be cached
		 * -refresh: Set a timer for when page re-loads
		 * -set-cookie: Specifies a cooke with a page.
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
