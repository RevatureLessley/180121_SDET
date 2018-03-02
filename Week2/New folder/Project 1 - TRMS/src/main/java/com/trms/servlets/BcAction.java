package com.trms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.services.FormServices;

/**
 * Servlet implementation class FormAction
 */
public class BcAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int num = request.getParameterMap().size();
		for (int i = 0; i < num / 3; i++) {
			Long appId = Long.parseLong(request.getParameter("bcappid" + i));
			String bcDecision = request.getParameter("bcDecision" + i);
			String bcComment = request.getParameter("bcComment" + i);
			
			FormServices.updateBcAction(appId, bcDecision, bcComment);
		}

		response.sendRedirect("manageApplication.jsp");
	}

}
