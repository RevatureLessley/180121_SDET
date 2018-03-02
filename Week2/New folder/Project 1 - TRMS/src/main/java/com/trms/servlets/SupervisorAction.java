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
public class SupervisorAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		for (Object o : request.getParameterMap().keySet()) {
			System.out.println(o);
		}
		int num = request.getParameterMap().size();
		for (int i = 0; i < num / 3; i++) {
			Long appId = Long.parseLong(request.getParameter("svappid" + i));
			String svDecision = request.getParameter("svDecision" + i);
			String svComment = request.getParameter("svComment" + i);
//			System.out.println("id=" + appId);
//			System.out.println("d=" + svDecision);
//			System.out.println("c=" + svComment);
			FormServices.updateSvAction(appId, svDecision, svComment);
		}

		response.sendRedirect("manageApplication.jsp");
	}

}
