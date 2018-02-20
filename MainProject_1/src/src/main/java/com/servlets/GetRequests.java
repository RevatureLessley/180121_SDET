package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.request.RequestTR;
import com.services.RequestService;

/**
 * Servlet implementation class GetRequests
 */
public class GetRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		//System.out.println("Im here");
		
		HttpSession session = request.getSession(false);
		Integer supervisorRef = (Integer) session.getAttribute("ReferenceID");
		Integer supervisorRef1 = 22222;
		
		
		List<RequestTR> reqs = RequestService.displayRequests(supervisorRef);
		
		System.out.println(reqs);
		
		if(reqs!=null){
			String myXml = "<root>";
			//Manually create the xml file.
			for(RequestTR r : reqs){
				myXml += "<req><r_id>" + r.getRequestId() + "</r_id>"
								+ "<e_id>" + r.getEmployeeId() + "</e_id>"
								+ "<e_fname>" + r.getFirstName() + "</e_fname>"
								+ "<e_lname>" + r.getLastName() + "</e_lname>"
								+ "<e_email>" + r.getEmail() + "</e_email>"
								+ "<e_phone>" + r.getPhone() + "</e_phone>"
								+ "<e_type>" + r.getEvent_Type() + "</e_type>"
								+ "<e_price>" + r.getAmount_Requested() + "</e_price>"
								+ "<e_format>" + r.getGrading_Format() + "</e_format>"
								+ "<e_just>" + r.getEvent_Justification() + "</e_just>"
								+ "<e_balance>" + r.getBalance_Available() + "</e_balance></req>";			
								
			}
			myXml += "</root>";
			
			System.out.println(myXml);
			
			out.print(myXml); //State == 4
			//out.print("hey there"); //State == 4
			
		}else{
			/*
			String myXml = "<root>";
			
				myXml += "<request><r_id>" + supervisorRef + "</r_id>"
								+ "<e_id>" + supervisorRef + "</e_id>"
								+ "<e_fname>" + supervisorRef + "</e_fname>"
								+ "<e_lname>" + supervisorRef + "</e_lname>"
								+ "<e_email>" + supervisorRef + "</e_email>"
								+ "<e_phone>" + supervisorRef + "</e_phone>"
								+ "<e_type>" + supervisorRef + "</e_type>"
								+ "<e_price>" + supervisorRef + "</e_price>"
								+ "<e_format>" + supervisorRef + "</e_format>"
								+ "<e_just>" + supervisorRef + "</e_just>"
								+ "<e_balance>" + supervisorRef + "</e_balance></request>";			
								
			
			myXml += "</root>";
			
			out.print(myXml); //State == 4
			out.print("hey there"); //State == 4
*/			out.print("<root></root>"); //State == 4
			}
		}
			
			
			
			
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

