package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.trms.beans.Employee;
import com.trms.services.EmployeeService;
import com.trms.services.ReimbursementService;

/**
 * Servlet implementation class SingleReEmpInfoServlet
 */
public class SingleReEmpInfoServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(SingleReEmpInfoServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		int rid = Integer.parseInt(request.getParameter("rid"));
		
		
		Employee e = EmployeeService.getUserEmpId(ReimbursementService.getEmpIdByReimburse(rid));
		JSONObject member = new JSONObject();
		
		member.put("empid", e.getId());
		member.put("fname", e.getFname());
		member.put("lname", e.getLname());
		member.put("addr", e.getAddr());
		member.put("city", e.getCity());
		member.put("state", e.getState());
		member.put("zip", e.getZipCode());
		member.put("availreim", e.getAvailReimburse());
		member.put("department", e.getDepartment());
		member.put("title", e.getTitle());

		logger.info("doGet() : " + member.toString());
		out.print(member.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
