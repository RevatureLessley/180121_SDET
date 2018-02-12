package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.trms.beans.Employee;
import com.trms.services.EmployeeService;
import com.trms.services.UsersEmpService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	final static Logger logger = Logger.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = UsersEmpService.getUserEmpId(request.getParameter("username"), request.getParameter("password"));
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(id != -1) {
			out.println("<h3>Logged In</h3>");
			Employee e = EmployeeService.getUserEmpId(id);
			response.sendRedirect("user/userhome.html");
			// TODO figure out where to send the Employee object to use the information in it
		} else {
			out.println("<head> <meta http-equiv='Refresh' content='2;url=index.html'>"
					+ "</head>");
			out.println("<h3>Incorrect Login. Try Again.</h3>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
