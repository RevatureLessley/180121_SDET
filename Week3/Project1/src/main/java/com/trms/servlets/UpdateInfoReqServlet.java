package com.trms.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.beans.AddedInfo;
import com.trms.services.ReimbursementService;

/**
 * Servlet implementation class UpdateInfoReqServlet
 */
public class UpdateInfoReqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReimbursementService.updateInfoReq(Integer.parseInt(request.getParameter("rid")), Integer.parseInt(request.getParameter("info_empid")));
		// TODO after emp adds the requested info the req info should be changed back to -1
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
