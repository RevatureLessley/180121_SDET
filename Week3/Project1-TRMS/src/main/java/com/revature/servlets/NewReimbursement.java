package com.revature.servlets;
import java.io.PrintWriter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.service.DataService;
import com.revature.util.HTMLTemplates;

/**
 * Servlet implementation class NewReimbursement
 */
public class NewReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**doGet() will call doPost() as it will be more secure for handling such sensitive information.**/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { doPost(request, response);}

	/**doPost() will take in all the values from the form that is in the request, and pass them to DataService's method
	 * insertEmployee() as arguments. The amount will be preset to 1000 as it is a new registration.**/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		int empid = (int) session.getAttribute("empid");
		String fname = (String) session.getAttribute("fname");
		String lname = (String) session.getAttribute("lname");
		String gradingFormat = "Unsure";
		
		String dateof = request.getParameter("dateof");
		String timeof = request.getParameter("timeof");
		String location = request.getParameter("location");
		String desc = request.getParameter("desc");
		int cost = Integer.parseInt(request.getParameter("cost"));
		String typeofevent = request.getParameter("typeofevent");
		String work = request.getParameter("work");
			
		DataService.insertNewReimbursement(empid, fname, lname, dateof, timeof, location,
				desc, cost, gradingFormat, typeofevent, work);
	}

}
