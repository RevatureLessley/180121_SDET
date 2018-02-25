package com.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.services.RegisterAssociate;
import com.util.HtmlTemplates;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String FirstName = request.getParameter("FirstName");
		String LastName = request.getParameter("LastName");
		String UserName = request.getParameter("UserName");
		String Password = request.getParameter("Password");
		String Email = request.getParameter("Email");
		String ContactNumber = request.getParameter("ContactNumber");
		String SupervisorRef = request.getParameter("SupervisorRef");
		String AssociateID = request.getParameter("AssociateID");
		
		Integer SV_Ref = Integer.parseInt(SupervisorRef);
		Integer A_ID = Integer.parseInt(AssociateID);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {
			
			//HttpSession session = request.getSession();
			
	
			
			if (RegisterAssociate.addAccount(A_ID, FirstName, LastName, UserName, Password, ContactNumber, Email, SV_Ref)){
				//session.invalidate();
				out.print("<h1>ACCOUNT CREATED</h1>");
				HtmlTemplates.goBackButton(out);
			}else {
				//session.invalidate();
				out.print("<h1>Employee ID, First Name or Last Name is Incorrect</h1>");
				HtmlTemplates.goBackButton(out);
			}
			   	
				
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
