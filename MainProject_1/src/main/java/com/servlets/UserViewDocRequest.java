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
import com.services.RequestService;
import com.util.HtmlTemplates;

/**
 * Servlet implementation class UserViewDocRequest
 */
public class UserViewDocRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		//System.out.println("Im here");
		
		HttpSession session = request.getSession(false);
		Integer EmployeeID = (Integer) session.getAttribute("AccountID");
		
		
		List<Documentations> docs = RequestService.displayUserDocRequests(EmployeeID);
		
		/*out.println("<table border = 2px>");
		out.println("<th>Doc Request ID</th>");
		out.println("<th>Request ID</th><td>");	
		out.println("<th>Authorizer Title</th>");
		out.println("<th>Document Requested</th>");
		
		
		for(Documentations d : docs) {

		out.println("<tr><td>" + d.getDocRequestId() + "</td></tr>");
		out.println("<tr><td>" +  d.getRequestId() + "</td></tr>");	
		out.println("<tr><td>" +  d.getAuthorizerTitle() + "</td></tr>");
		out.println("<tr><td>" + d.getDocument() + "</td></tr>");
		
		}
		
		out.println("</table>");
		
		HtmlTemplates.goBackButton(out);
		*/
		
				
		if(docs!=null){
			String myXml = "<root>";
			//Manually create the xml file.
			for(Documentations d : docs){
				myXml += "<doc><r_d_id>" + d.getDocRequestId() + "</d_id>"
								+ "<r_id>" + d.getRequestId() + "</r_id>"
								+ "<e_id>" + d.getAuthorizerTitle() + "</e_id>"
								+ "<d_doc>" + d.getDocument() + "</d_doc> </doc>";
										
								
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
