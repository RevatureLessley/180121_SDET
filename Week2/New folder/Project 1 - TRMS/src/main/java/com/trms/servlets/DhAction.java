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
public class DhAction extends HttpServlet {
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
			if(request.getParameter("svappid" + i)==null)
				break;
			Long appId = Long.parseLong(request.getParameter("svappid" + i));
			String dhDecision = request.getParameter("svDecision" + i);
			String dhComment = request.getParameter("svComment" + i);
			FormServices.updateDhAction(appId, dhDecision, dhComment);
		}
		for (int i = 0; i < num / 3; i++) {
			Long appId = Long.parseLong(request.getParameter("dhappid" + i));
			String svDecision = request.getParameter("dhDecision" + i);
			String svComment = request.getParameter("dhComment" + i);
			FormServices.updateDhAction(appId, svDecision, svComment);
		}

		response.sendRedirect("manageApplication.jsp");
	}

}
