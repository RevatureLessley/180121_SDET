package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.beans.Tuition;
import com.project.services.TuitionServices;

/**
 * Servlet implementation class TuitionApprove
 */
public class TuitionApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet TuitionApprove");
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		List<Tuition> tuitions = TuitionServices.getTuitionByApprover(username);
		
		if(tuitions!=null){
			String myXml = "<root>";
			for(Tuition t : tuitions){
				myXml += "<tuition>"+
						"<t_name>" + t.getUsername()+ "</t_name>" +
						"<t_id>" + t.getT_id() + "</t_id>" +
						"<t_Start_date>" + t.getStart_date() + "</t_Start_date>" +
						"<t_End_date>" + t.getEnd_date() + "</t_End_date>" +
						"<t_Location>" + t.getLocation() + "</t_Location>" +
						"<t_Description>" + t.getDescription() + "</t_Description>" +
						"<t_Grading_formate>" + t.getGrading_formate() + "</t_Grading_formate>" +
						"<t_Event_type>" + t.getEvent_type() + "</t_Event_type>" +
						"<t_Cost>" + t.getCost() + "</t_Cost>" +
						"<t_Projected>" + t.getProjected() + "</t_Projected>" +
						"<t_Status>" + TuitionServices.getStatus(t.getApproval()) + "</t_Status>" +
						 "</tuition>";
			}
			myXml += "</root>";
			
			out.print(myXml); 
		}else{
			out.print("<root></root>"); 
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
