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

/**
 * Servlet implementation class EditReimbursement
 */
public class EditReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int empid = (int) session.getAttribute("empid");
		String fname = (String) session.getAttribute("fname");
		String lname = (String) session.getAttribute("lname");
		String gradingFormat = "Unsure";
		
		int reiid = Integer.parseInt(request.getParameter("reiid"));
		String dateof = request.getParameter("dateof");
		String timeof = request.getParameter("timeof");
		String location = request.getParameter("location");
		String desc = request.getParameter("desc");
		int cost = Integer.parseInt(request.getParameter("cost"));
		String typeofevent = request.getParameter("typeofevent");
		String work = request.getParameter("work");
		
		if(DataService.editReimbursement(reiid, empid, fname, lname, dateof, timeof, location,
				desc, cost, gradingFormat, typeofevent, work) == 1) {
			HTMLTemplates.headers(out);
			HTMLTemplates.navbar(out);
			out.print("<h1>SUCCESS! YOUR REIMBURSEMENT WAS EDITED! PLEASE RETURN TO THE HOME PAGE.</h1>");
			
		}else {
			HTMLTemplates.headers(out);
			HTMLTemplates.navbar(out);
			out.print("<h1>REI_ID WAS NOT FOUND! PLEASE TRY AGAIN!</h1>");
			HTMLTemplates.goBackButton(out);
		}
	}

}
