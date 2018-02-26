package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.RequestService;

/**
 * Servlet implementation class SubmitGradeServlet
 */
public class SubmitGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text");
		PrintWriter out = response.getWriter();
		Integer requestId = Integer.parseInt(request.getParameter("req_ID"));
		
		String grade = request.getParameter("Grade");
		
		System.out.println(requestId + grade);
		
		if(RequestService.correctGrade(requestId, grade)){
				out.print("Grade Submitted");
			}else{
				out.print("Wrong Grading Format Submitted");
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
