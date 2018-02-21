package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.service.DataService;
import com.revature.util.HTMLTemplates;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		if(!session.isNew()){
			HTMLTemplates.headers(out);
			HTMLTemplates.navbar(out);
			out.println("<h1>UNAUTHOTORIZED ACCESS! FBI HAS BEEN ALERTED!</h1>");
		}
		// If there are no users in TRMS employee database
		else if(DataService.valLogin(username, password) == 0){
			session.invalidate();
			HTMLTemplates.headers(out);
			HTMLTemplates.navbar(out);
			out.print("<h1>NO EMPLOYEE DATA! PLEASE CONTACT SITE ADMINISTRATOR!</h1>");
			HTMLTemplates.goBackButton(out);
		}
		// If user is NOT in TRMS databse
		else if(DataService.valLogin(username, password) == 1){
			session.invalidate();
			HTMLTemplates.headers(out);
			HTMLTemplates.navbar(out);
			out.print("<h1>USER NOT FOUND! PLEASE CHECK YOUR USERNAME AND TRY AGAIN!</h1>");
			HTMLTemplates.goBackButton(out);
		}
		// If wrong password
		else if(DataService.valLogin(username, password) == 2){
			session.invalidate();
			HTMLTemplates.headers(out);
			HTMLTemplates.navbar(out);
			out.print("<h1>WRONG USERNAME/PASSWORD! PLEASE TRY AGAIN!</h1>");
			HTMLTemplates.goBackButton(out);
		}
		// If user is a regular employee with correct credentials
		else if(DataService.valLogin(username, password) == 3){
			
			Employee emp = DataService.returnEmployee(username); //Retrieve users information and store in an employee object
			
			session.setAttribute("empid", emp.getEmp_id()); // Stores emp_id in session cookie 
			session.setAttribute("fname", emp.getFname()); // Stores fname in session cookie 
			session.setAttribute("lname", emp.getLname()); // Stores lname in session cookie 
			session.setAttribute("username", username); // Stores username in session cookie 
			session.setAttribute("password", emp.getPassword()); // Stores password in session cookie 
			session.setAttribute("email", emp.getEmail()); // Stores email in session cookie 
			session.setAttribute("role", emp.getRole()); // Stores role in session cookie 
			session.setAttribute("department", emp.getDepartment()); // Stores Department in session cookie
			session.setAttribute("supid", emp.getSup_id()); // Stores sup_id in session cookie 
			session.setAttribute("amount", emp.getAmount()); // Stores amount in session cookie 
			
			
			RequestDispatcher rd = request.getRequestDispatcher("user/employeehome.html");
			rd.forward(request, response);
		}
		// if user is a supervisor with correct credentials
		else if(DataService.valLogin(username, password) == 4){
			
			Employee emp = DataService.returnEmployee(username); //Retrieve users information and store in an employee object
			
			session.setAttribute("empid", emp.getEmp_id()); // Stores emp_id in session cookie 
			session.setAttribute("fname", emp.getFname()); // Stores fname in session cookie 
			session.setAttribute("lname", emp.getLname()); // Stores lname in session cookie 
			session.setAttribute("username", username); // Stores username in session cookie 
			session.setAttribute("password", emp.getPassword()); // Stores password in session cookie 
			session.setAttribute("email", emp.getEmail()); // Stores email in session cookie 
			session.setAttribute("role", emp.getRole()); // Stores role in session cookie 
			session.setAttribute("department", emp.getDepartment()); // Stores Department in session cookie
			session.setAttribute("supid", emp.getSup_id()); // Stores sup_id in session cookie 
			session.setAttribute("amount", emp.getAmount()); // Stores amount in session cookie 
			
			RequestDispatcher rd = request.getRequestDispatcher("user/supervisorhome.html");
			rd.forward(request, response);
		}
		// if user is a department head with correct credentials
		else if(DataService.valLogin(username, password) == 5){
			
			Employee emp = DataService.returnEmployee(username); //Retrieve users information and store in an employee object
			
			session.setAttribute("empid", emp.getEmp_id()); // Stores emp_id in session cookie 
			session.setAttribute("fname", emp.getFname()); // Stores fname in session cookie 
			session.setAttribute("lname", emp.getLname()); // Stores lname in session cookie 
			session.setAttribute("username", username); // Stores username in session cookie 
			session.setAttribute("password", emp.getPassword()); // Stores password in session cookie 
			session.setAttribute("email", emp.getEmail()); // Stores email in session cookie 
			session.setAttribute("role", emp.getRole()); // Stores role in session cookie 
			session.setAttribute("department", emp.getDepartment()); // Stores Department in session cookie
			session.setAttribute("supid", emp.getSup_id()); // Stores sup_id in session cookie 
			session.setAttribute("amount", emp.getAmount()); // Stores amount in session cookie 
			
			RequestDispatcher rd = request.getRequestDispatcher("user/departmentheadhome.html");
			rd.forward(request, response);
		}
		// If user is a benefits coordinator with correct credentials
		else if(DataService.valLogin(username, password) == 6){
			
			Employee emp = DataService.returnEmployee(username); //Retrieve users information and store in an employee object
			
			session.setAttribute("empid", emp.getEmp_id()); // Stores emp_id in session cookie 
			session.setAttribute("fname", emp.getFname()); // Stores fname in session cookie 
			session.setAttribute("lname", emp.getLname()); // Stores lname in session cookie 
			session.setAttribute("username", username); // Stores username in session cookie 
			session.setAttribute("password", emp.getPassword()); // Stores password in session cookie 
			session.setAttribute("email", emp.getEmail()); // Stores email in session cookie 
			session.setAttribute("role", emp.getRole()); // Stores role in session cookie 
			session.setAttribute("department", emp.getDepartment()); // Stores Department in session cookie
			session.setAttribute("supid", emp.getSup_id()); // Stores sup_id in session cookie 
			session.setAttribute("amount", emp.getAmount()); // Stores amount in session cookie 
			
			RequestDispatcher rd = request.getRequestDispatcher("user/benefitshome.html");
			rd.forward(request, response);
		}
		// Otherwise invalidate session anyways.
		else{
			session.invalidate();
			HTMLTemplates.headers(out);
			HTMLTemplates.navbar(out);
			out.print("<h1>INVALID CREDENTIALS</h1>");
			HTMLTemplates.goBackButton(out);
			
		}
	}

}
