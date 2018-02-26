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
		Integer supervisorRef = (Integer) session.getAttribute("SupervisorRef");
		Integer deptID = ApplyTR.getDeptID(supervisorRef);
		
		Integer ReimbursmentAmt = 0;
		System.out.print(supervisorRef);
		
		//Integer SV_Ref = Integer.parseInt(SupervisorRef);
		RequestTR tr = null; 
		
		if(ApplyTR.checkBalance(Price, EmployeeID) >= 0 ) {
		
			
			if(EventType.equals("University Course")) {
				
				ReimbursmentAmt = (int)(Price*(80.0f/100.0f));
				 tr = new RequestTR(EmployeeID, supervisorRef, deptID, LastName, FirstName, ContactNumber, Email, ReimbursmentAmt, Balance_Available,
										EventType, WRJcomment, GradingFormat, 0, 0, 0, 0);
			
			}else if(EventType.equals("Seminar")) {
				
				ReimbursmentAmt = (int)(Price*(60.0f/100.0f));
				 tr = new RequestTR(EmployeeID, supervisorRef, deptID, LastName, FirstName, ContactNumber, Email, ReimbursmentAmt, Balance_Available,
										EventType, WRJcomment, GradingFormat, 0, 0, 0, 0);
				
			}else if(EventType.equals("Certification Preparation Class")) {
				
				ReimbursmentAmt = (int)(Price*(75.0f/100.0f));
				 tr = new RequestTR(EmployeeID, supervisorRef, deptID, LastName, FirstName, ContactNumber, Email, ReimbursmentAmt, Balance_Available,
										EventType, WRJcomment, GradingFormat, 0, 0, 0, 0);
			
			}else if(EventType.equals("Certification")) {
				
				 tr = new RequestTR(EmployeeID, supervisorRef, deptID, LastName, FirstName, ContactNumber, Email, Price, Balance_Available,
										EventType, WRJcomment, GradingFormat, 0, 0, 0, 0);
			
			}else if(EventType.equals("Technical Training")) {
				
				ReimbursmentAmt = (int)(Price*(90.0f/100.0f));
				 tr = new RequestTR(EmployeeID, supervisorRef, deptID, LastName, FirstName, ContactNumber, Email, ReimbursmentAmt, Balance_Available,
										EventType, WRJcomment, GradingFormat, 0, 0, 0, 0);
				
			}else {
				
				ReimbursmentAmt = (int)(Price*(30.0f/100.0f));
				 tr = new RequestTR(EmployeeID, supervisorRef, deptID, LastName, FirstName, ContactNumber, Email, ReimbursmentAmt, Balance_Available,
										EventType, WRJcomment, GradingFormat, 0, 0, 0, 0);
			}
			
		} else if(ApplyTR.checkBalance(Price, EmployeeID) <= 0 ) {
			 ReimbursmentAmt = ApplyTR.adjustedBalance(EmployeeID);
			 tr = new RequestTR(EmployeeID, supervisorRef, deptID, LastName, FirstName, ContactNumber, Email, ReimbursmentAmt, Balance_Available,
						EventType, WRJcomment, GradingFormat, 0, 0, 0, 0);
		}
		
		System.out.println(tr.getAmount_Requested());
		
		if(ApplyTR.SendRequest(tr)) {
			
			ApplyTR.adjustAssociateBalance(tr.getAmount_Requested(), tr.getEmployeeId());
			
			out.print("<h1>REQUEST SENT</h1>");
			HtmlTemplates.goBackButton(out);
		}else{
			out.print("<h1>Something Went Wrong</h1>");
			HtmlTemplates.goBackButton(out);
		}
			   	
				
	}	
	}
