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
 * Servlet implementation class UserViewRequest
 */
public class UserViewRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		Integer AccountID = (Integer) session.getAttribute("AccountID");
		
		
		List<RequestTR> reqs = RequestService.displayUserRequests(AccountID);
		
		System.out.println(reqs);
		
		if(reqs!=null){
			String myXml = "<root>";
			//Manually create the xml file.
			for(RequestTR r : reqs){
				myXml += "<view><r_id>" + r.getRequestId() + "</r_id>"
								+ "<e_type>" + r.getEvent_Type() + "</e_type>"
								+ "<e_price>" + r.getAmount_Requested() + "</e_price>"
								+ "<e_status>" + RequestService.checkStatus(r.getRequestId()) + "</e_status>"
								+ "<e_docs>" +  RequestService.getDoc(r.getRequestId()) + "</e_docs></view>";			
								
			}
			myXml += "</root>";
			
			System.out.println(myXml);
			
			out.print(myXml); //State == 4
			
		}else{
			
		out.print("<root></root>"); //State == 4
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
