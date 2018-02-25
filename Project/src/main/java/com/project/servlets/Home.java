package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.beans.Reimbursement;
import com.project.beans.Tuition;
import com.project.services.EmployeeServices;
import com.project.services.ReimbursementServices;
import com.project.services.TuitionServices;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Home servlet");
		
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		Reimbursement r = ReimbursementServices.getReimburseByUsername(username);
		String title = EmployeeServices.getTitle(username);
		System.out.println(r);
		
		if(r!=null){
			String myXml = "<root><name id='user'>" + username +" ( "+title+" ) " + "</name>";
			
				myXml += "<reimbursement id='reimburse'>"+
						"<r_available>" + (1000-r.getPending()-r.getAwarded()) + "</r_available>" +
						"<r_pending>" + r.getPending() + "</r_pending>" +
						"<r_awarded>" + r.getAwarded() + "</r_awarded>" +
						 "</reimbursement>";
			
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
