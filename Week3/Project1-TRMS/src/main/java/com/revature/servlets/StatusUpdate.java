package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.service.DataService;
import com.revature.util.HTMLTemplates;

public class StatusUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String afname = (String) session.getAttribute("fname");
		String alname = (String) session.getAttribute("lname");
		String role = (String) session.getAttribute("role");
		System.out.println(role);
		System.out.println("========================");
		int reiid = Integer.parseInt(request.getParameter("reiid"));
		int status = interpretStatus(request.getParameter("status"), role);
		System.out.println(request.getParameter("status"));
		System.out.println("========================");
		System.out.println(status);
		
		String note = request.getParameter("note");
		int result = DataService.addStatus(reiid, afname, alname,status, note);
		
		if( result == 1) {
			HTMLTemplates.headers(out);
			HTMLTemplates.navbarEmp(out);
			out.print("<h1>SUCCESS! REIMBURSEMENT WAS UPDATED! PLEASE RETURN TO THE HOME PAGE.</h1>");
			HTMLTemplates.goBackButton(out);
			
		}else if(result == 0){
			HTMLTemplates.headers(out);
			HTMLTemplates.navbar(out);
			out.print("<h1>REI_ID WAS NOT FOUND! PLEASE TRY AGAIN!</h1>");
			HTMLTemplates.goBackButton(out);
			
		}else if (result == 2) {
			HTMLTemplates.headers(out);
			HTMLTemplates.navbar(out);
			out.print("<h1>REIMBURSEMENT ALREADY HAD THIS STATUS!</h1>");
			HTMLTemplates.goBackButton(out);
		}
		
	}

	
	private int interpretStatus (String status, String r) {
		if(status.equals("Approve") && r.equals("supervisor")) {return 1;}
		else if(status.equals("Approve")&& r.equals("head")) {return 2;}
		else if(status.equals("Approve")&& r.equals("benefits coordinator")) {return 3;}
		else if(status.equals("Reject") && r.equals("supervisor")) {return 4;}
		else if(status.equals("Reject") && r.equals("head")) {return 5;}
		else {return 6;}
	}
}
