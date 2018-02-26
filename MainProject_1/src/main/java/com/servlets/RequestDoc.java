package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.services.RequestService;
import com.util.HtmlTemplates;

/**
 * Servlet implementation class RequestDoc
 */
public class RequestDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		
		Integer RequestID =  Integer.parseInt(request.getParameter("req_ID"));
		String DocsNeeded = request.getParameter("Docs");
		Integer AuthorizerID = (Integer) session.getAttribute("AccountID");
		String EmployeeType = (String) session.getAttribute("EmployeeType");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
			
		RequestService.docsNeeded(DocsNeeded, RequestID, EmployeeType, AuthorizerID);
		out.print("<h1>REQUEST SENT</h1>");
		HtmlTemplates.goBackButton(out);
			
	}

}
