package com.trms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trms.beans.AddedInfo;
import com.trms.services.ReimbursementService;

/**
 * Servlet implementation class InsertNewAddedInfoServlet
 */
public class InsertNewAddedInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AddedInfo ai = new AddedInfo(Integer.parseInt(request.getParameter("rid")), (int)session.getAttribute("empid"), request.getParameter("info"));
		ai.setInfoSubject(request.getParameter("subject"));
		ReimbursementService.insertExtraInfo(ai);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
