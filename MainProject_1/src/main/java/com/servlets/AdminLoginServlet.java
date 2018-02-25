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

import com.adminend.BenCoAccount;
import com.adminend.DeptHeadAccount;
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
					session.setAttribute("EmployeeType", "Supervisor");
					//session.setAttribute("DeptID", visor.getDeptID());

					
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
			
		} else if(LoginChoice.equals("Department Head")) {
			DeptHeadAccount dept;
			HttpSession session = request.getSession();
			try {
				if(LoginAdmin.validateDept(username, password) != null){
					dept = LoginAdmin.validateDept(username, password);
					session.setAttribute("username", dept.getUserName());
					session.setAttribute("Password", dept.getPassword());
					session.setAttribute("AccountID", dept.getDeptHeadID());
					session.setAttribute("DeptID", dept.getDeptID());
					session.setAttribute("EmployeeType", "DeptHead");
					
					RequestDispatcher rd = request.getRequestDispatcher("admins/deptHeadHome.html");
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
			
		} else if(LoginChoice.equals("BenCo")) {
			BenCoAccount benco;
			HttpSession session = request.getSession();
			try {
				if(LoginAdmin.validateBenCo(username, password) != null){
					benco = LoginAdmin.validateBenCo(username, password);
					session.setAttribute("username", benco.getUserName());
					session.setAttribute("Password", benco.getPassword());
					session.setAttribute("AccountID", benco.getBenCoID());
					session.setAttribute("EmployeeType", "BenCo");
					
					RequestDispatcher rd = request.getRequestDispatcher("admins/benCoHome.html");
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
			
		
	}

}
