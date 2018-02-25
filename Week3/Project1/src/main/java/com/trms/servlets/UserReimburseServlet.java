package com.trms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.trms.beans.Employee;
import com.trms.services.EmployeeService;

/**
 * Servlet implementation class UserReimburseServlet
 */
public class UserReimburseServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(UserReimburseServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Reimbursement Service and dao need to be created
		 * Mockito for testing???
		 * Then get relevant data from database to display via ajax.js
		 */
		HttpSession session = request.getSession();
		logger.info("doGet() : Emp Id # " + session.getAttribute("empid"));
		int numSub = EmployeeService.getSubordinates((Integer)session.getAttribute("empid"));
		Employee e = EmployeeService.getEmpByUserEmpId((Integer)session.getAttribute("empid"));
		logger.info("doGet() : " + e.getTitle());
		logger.info("doGet() number Subord : " + numSub);
		if(e.getTitle().equals("DEPARTMENT HEAD") || numSub > 0) {
			session.setAttribute("approver", true);
			response.sendRedirect("user/userhomeappr.html");
		} else if( e.getTitle().equals("BENEFITS COORDINATOR")) {
			session.setAttribute("approver", true);
			response.sendRedirect("user/approver/allreimburse.html");
		}	else {
			session.setAttribute("approver", false);
			response.sendRedirect("user/userhome.html");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO send information for new reimbursement to be validated and created in the database
		doGet(request, response);
	}

}
