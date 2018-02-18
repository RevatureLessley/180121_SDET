package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.request.RequestTR;
import com.services.ApplyTR;
import com.services.RegisterAssociate;
import com.util.HtmlTemplates;

/**
 * Servlet implementation class SendRequestServlet
 */
public class SendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		
		//out.print("<h3>Tuition Reimbursment Application for " + session.getAttribute("username") + "!</h3>");
		
		
		Integer EmployeeID = (Integer) session.getAttribute("AccountID");
		Integer Balance_Available = (Integer) session.getAttribute("Balance_Available");

		String FirstName = request.getParameter("FirstName");
		String LastName = request.getParameter("LastName");
		String Email = request.getParameter("Email");
		String ContactNumber = request.getParameter("ContactNumber");
		String EventDate = request.getParameter("EventDate");
		String EventTime = request.getParameter("EventTime");
		Integer Price = Integer.parseInt(request.getParameter("Price"));
		String GradingFormat = request.getParameter("GradingFormat");
		String EventType = request.getParameter("EventType");
		String WRJcomment = request.getParameter("WRJcomment");
		String TimeMissed = request.getParameter("TimeMissed");
		
		
		//Integer SV_Ref = Integer.parseInt(SupervisorRef);
		
		RequestTR tr = new RequestTR(EmployeeID, LastName, FirstName, ContactNumber, Email, Price, Balance_Available,
										EventType, WRJcomment, GradingFormat, 0, 0, 0, 0);
		
		
		if(ApplyTR.SendRequest(tr))
			out.print("<h1>REQUEST SENT</h1>");
		else
			out.print("<h1>Something Went Wrong</h1>");	
				
			   	
				
	}	
	}
