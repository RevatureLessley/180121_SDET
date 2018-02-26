package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.request.Documentations;
import com.request.RequestTR;
import com.services.RequestService;

/**
 * Servlet implementation class GetDocRequests
 */
public class GetDocRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		//System.out.println("Im here");
		
		HttpSession session = request.getSession(false);
		//System.out.println(session.getAttribute("AccountID"));
		Integer EmployeeID = (Integer) session.getAttribute("AccountID");
		
		
		List<Documentations> docs = RequestService.displayDocRequests(EmployeeID);
				
		if(docs!=null){
			String myXml = "<root>";
			//Manually create the xml file.
			for(Documentations d : docs){
				myXml += "<view><r_d_id>" + d.getDocRequestId() + "</d_id>"
								+ "<r_id>" + d.getRequestId() + "</r_id>"
								+ "<e_id>" + d.getEmployeeId() + "</e_id>"
								+ "<d_doc>" + d.getDocument() + "</d_doc>"
								+ "<d_type>" + d.getDocumentType() + "</d_type></view>";
												
			}
			
			myXml += "</root>";
			
			//System.out.println(myXml);
			
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
