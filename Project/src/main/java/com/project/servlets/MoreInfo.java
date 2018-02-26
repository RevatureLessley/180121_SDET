package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.services.TuitionServices;

/**
 * Servlet implementation class MoreInfo
 */
public class MoreInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * this servlet request More info form the employee on tuition request
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In servlet Moreinfo");
		
		int t_id = Integer.parseInt(request.getParameter("t_id"));
		String reason = request.getParameter("reason");
		
		System.out.println(t_id);
		System.out.println(reason);
		
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		if(TuitionServices.moreInfo(t_id,reason)){
			out.print("<root><result id='rs'>success</result></root>");
			System.out.println("success");
		}else {
			out.print("<root><result id='rs'>failed</result></root>");
			System.out.println("failed");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
