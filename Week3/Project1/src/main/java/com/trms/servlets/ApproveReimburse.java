package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.trms.services.ReimbursementService;

/**
 * Servlet implementation class ApproveReimburse
 */
public class ApproveReimburse extends HttpServlet {
	private static final Logger logger = Logger.getLogger(ApproveReimburse.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		logger.info("empid=" + (Integer)session.getAttribute("empid"));
		ReimbursementService.updateApproval(Integer.parseInt(request.getParameter("rid")), Integer.parseInt(request.getParameter("response")), 
				(Integer)session.getAttribute("empid"));
		
		logger.info("approve reimbursement");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		doGet(request, response);
	}

}
