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
import com.revature.dao.ReimbursementDao;
import com.revature.dao.StatusDao;


@WebServlet("/Create")
public class CreateServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Create.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employees emp = (Employees) session.getAttribute("emp");
		int eid = emp.getEid();
		String rtype = request.getParameter("rtype");
		double cost = Double.parseDouble(request.getParameter("cost"));
		String description = request.getParameter("description");
		String location = request.getParameter("location");
		String grade = request.getParameter("grade");
		ReimbursementDao dao = new ReimbursementDao();
		dao.createReimbursement(eid, cost, rtype, description, location, grade);
		int rid = dao.getMostRecentEntry();
		StatusDao sdao = new StatusDao();
		sdao.createStatus(rid, emp.getSupervisor(), emp.getDeptHead());
		response.sendRedirect("EmployeeView");

	}
}