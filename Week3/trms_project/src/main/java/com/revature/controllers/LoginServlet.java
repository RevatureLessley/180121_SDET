package com.revature.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employees;
import com.revature.dao.EmployeeDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.setAttribute("login", false);
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		EmployeeDao dao = new EmployeeDao();
		Employees emp = new Employees(0);
		emp = dao.loginEmp(email, password);
		session.setAttribute("login",  false);
		if(emp.isBenCo() == true) {
			session.setAttribute("emp", emp);
			session.setAttribute("login", true);
			RequestDispatcher rd = request.getRequestDispatcher("BenCoView");
			rd.forward(request, response);
		}
		else if(emp.isDeptHead() == true) {
			session.setAttribute("login", true);
			session.setAttribute("emp", emp);
			RequestDispatcher rd = request.getRequestDispatcher("DeptHeadView");
			rd.forward(request, response);
		}
		else if(emp.isSup() == true) {
			session.setAttribute("login",  true);
			session.setAttribute("emp", emp);
			RequestDispatcher rd = request.getRequestDispatcher("SupervisorView");
			rd.forward(request, response);
		}
		else if(emp.getEid() > 0){
			session.setAttribute("emp", emp);
			session.setAttribute("login", true);
			RequestDispatcher rd = request.getRequestDispatcher("EmployeeView");
			rd.forward(request, response);
		} else {
			session.setAttribute("login", false);
			response.sendRedirect("login");
		}
	}
}