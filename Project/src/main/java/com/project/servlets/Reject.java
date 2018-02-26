package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.TuitionDao;
import com.project.dao.TuitionDaoImp;

/**
 * Servlet implementation class Reject
 */
public class Reject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * this servelt changes the status of tuition to reject
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Reject servlet");
		
		TuitionDao dao = new TuitionDaoImp();
		
		int t_id = Integer.parseInt(request.getParameter("t_id"));
		String reason = request.getParameter("reason");
		
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		if(dao.reject(t_id,reason)){
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
