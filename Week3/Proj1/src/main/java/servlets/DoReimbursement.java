package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ToYear;
import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;
import dao.ToYearDao;
import dao.ToYearDaoImpl;
import util.HtmlTemplates;

/**
 * Servlet implementation class Reimbursement
 */
public class DoReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DoReimbursement() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();	
		response.setContentType("text/html");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			out.println("<h1>Nobody is logged in</h1>");	
			HtmlTemplates.goBackButton(out);
		}

		
		String username = request.getParameter("username");

		if(!session.getAttribute("username").equals(username)) {
			out.println("<h1>No match found for that username and password</h1>");
			return;
		}
		
		String submitDateForm = request.getParameter("submitDate");
		String startDateForm = request.getParameter("startDate");
		
		int cost = Integer.parseInt(request.getParameter("cost"));
		int event = Integer.parseInt(request.getParameter("event"));
		switch(event) {
			case 1: //uni course
				cost *= 0.8; //80% coverage
				break;
			case 2:
				cost *= 0.6; //60% coverage
				break;
			case 3:
				cost *= 0.75;
				break;
			case 4: 
				cost *= 1;
				break;
			case 5:
				cost *= 0.9; 
				break;
			case 6:
				cost *= 0.3;
				break;								
		}
		
		ReimbursementDao rDao = new ReimbursementDaoImpl();
		int isUrgent = rDao.isUrgent(submitDateForm,startDateForm);	
		if (isUrgent == 2) {
			out.println("<h1>Your request is less than one week to start and has been automatically rejected</h1>");
			HtmlTemplates.goBackButton(out);
			return;
		}
				
		ToYearDao tyDao = new ToYearDaoImpl();
		ToYear ty = tyDao.getRecord(username);
		int adjustedClaim = tyDao.getMaxClaim(ty, cost );
		if(adjustedClaim > 0) {
			rDao.insertR(username, request.getParameter("super") ,Date.valueOf(submitDateForm), Date.valueOf(startDateForm), isUrgent, adjustedClaim, event);	
			out.println("<h1>Reimbursement request submitted for " + session.getAttribute("username") + " in the amount of " + adjustedClaim + "</h1>");			
		}
		else {
			out.println("<h1>" + session.getAttribute("username")  + " has reached their yearly limit</h1>");			
			
		}
//		session.setAttribute("fetchedFlag", false);//needs to be in try catch(NullPointerException) block


	}

}
