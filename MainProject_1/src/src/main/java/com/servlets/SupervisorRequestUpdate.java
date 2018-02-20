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
 * Servlet implementation class SupervisorRequestUpdate
 */
public class SupervisorRequestUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		
		Integer RequestID =  Integer.parseInt(request.getParameter("req_ID"));
		String UpdateAction = request.getParameter("UpdateAction");
		String DocsNeeded = request.getParameter("Docs");
		
		Integer ReferenceID = (Integer) session.getAttribute("ReferenceID");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
			
			if(UpdateAction.equals("Approve"))
				if(RequestService.approveRequest_S(RequestID)) {
					out.print("<h1>REQUEST APPROVED</h1>");
					HtmlTemplates.goBackButton(out);
				}else {
					out.print("<h1>SOMETHING WENT WRONG</h1>");
					HtmlTemplates.goBackButton(out);
				}
				
			else if(UpdateAction.equals("Decline"))
				RequestService.declineRequest_S(RequestID);
			else if(UpdateAction.equals("Documentation"))
				RequestService.docsNeeded_S(DocsNeeded, RequestID, ReferenceID);
	
			
	}
			   	


}