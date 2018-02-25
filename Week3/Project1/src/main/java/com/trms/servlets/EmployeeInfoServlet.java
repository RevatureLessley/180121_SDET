package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.trms.beans.Employee;
import com.trms.services.EmployeeService;

/**
 * Servlet implementation class EmployeeInfoServlet
 */
public class EmployeeInfoServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(EmployeeInfoServlet.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		Employee e = EmployeeService.getEmpByUserEmpId((int)session.getAttribute("empid"));
		JSONObject member = new JSONObject();
		
		member.put("empid", e.getId());
		member.put("fname", e.getFname());
		member.put("lname", e.getLname());
		member.put("addr", e.getAddr());
		member.put("city", e.getCity());
		member.put("state", e.getState());
		member.put("zip", e.getZipCode());
		member.put("availreim", e.getAvailReimburse());

		logger.info("doGet() : " + member.toString());
		out.print(member.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		doGet(request, response);
	}

}
