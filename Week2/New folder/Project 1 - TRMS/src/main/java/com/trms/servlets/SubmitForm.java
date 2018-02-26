package com.trms.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.beans.Application;
import com.trms.beans.Employee;
import com.trms.services.EmployeeService;
import com.trms.services.FormServices;

/**
 * Servlet implementation class SubmitForm
 */
public class SubmitForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String tel = request.getParameter("tel");
		String eventType = request.getParameter("event_type");
		Date eventDate = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			eventDate = format.parse(request.getParameter("event_date"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(eventDate.getTime());
		String eventLocation = request.getParameter("event_location");
		double eventCost = Double.parseDouble(request
				.getParameter("event_cost"));
		String relation = request.getParameter("relation");
		String gradingFormat = request.getParameter("grading_format");
		String description = request.getParameter("description");

		Application newApp = new Application(firstname, lastname, email,
				address, city, state, tel, sqlDate, eventLocation, eventType,
				relation, eventCost, gradingFormat, description);
		Employee emp = EmployeeService.getEmpByUsername((String) request
				.getSession().getAttribute("username"));
		newApp.setEmpId(emp.getEmpId());

		FormServices.addNewForm(newApp);
		
		response.sendRedirect("applicationHistory.jsp");

	}

}
