package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.services.TuitionServices;


public class ApproveRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * this servlet approves the approves the Tuition form with tuition id input
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In approverequest servlet");
		
		
		int t_id = Integer.parseInt(request.getParameter("t_id"));
		
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		if(TuitionServices.approveTuition(t_id)){
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
