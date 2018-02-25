package com.trms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.trms.services.ReimbursementService;

/**
 * Servlet implementation class AwardAmountServlet
 */
public class AwardAmountServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(AwardAmountServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ReimbursementService.awardReimbAmount(Integer.parseInt(request.getParameter("rid")), Float.parseFloat(request.getParameter("projreimb")), 
				Integer.parseInt(request.getParameter("award")), Float.parseFloat(request.getParameter("availreimb")), (int)session.getAttribute("empid"));
		logger.info("doGet() : request to servlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
