package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adminend.SupervisorAccount;
import com.services.LoginAdmin;
import com.util.HtmlTemplates;

/**
 * Servlet implementation class LoginServlet
 */
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String LoginChoice = request.getParameter("AdminType");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(LoginChoice);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(LoginChoice.equals("Supervisor")) {
			SupervisorAccount visor;
			HttpSession session = request.getSession();
			try {
				if(LoginAdmin.validateSuper(username, password) != null){
					visor = LoginAdmin.validateSuper(username, password);
					session.setAttribute("username", visor.getUserName());
					session.setAttribute("Password", visor.getPassword());
					session.setAttribute("AccountID", visor.getSupervisorID());
					session.setAttribute("ReferenceID", visor.getReferenceID());
					
					RequestDispatcher rd = request.getRequestDispatcher("admins/supervisorHome.html");
					rd.forward(request, response);
				}else{
					session.invalidate();
					
					out.print("<h1>INVALID CREDENTIALS</h1>");
					HtmlTemplates.goBackButton(out);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		//HttpSession session = request.getSession();
		/*if(!session.isNew()){
			out.println("<h1>Stop being weird.</h1>");
		} else*/
			
		
	}

}
