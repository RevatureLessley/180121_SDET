package com.trms.servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.trms.util.AutoApprove;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	final static Logger logger = Logger.getLogger(FrontController.class);
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		logger.info("FRONT CONTOLLER|INIT(): LOADED");
		int day = Integer.parseInt(this.getInitParameter("daysToApprove"));
		int min = Integer.parseInt(this.getInitParameter("minsToApprove"));
		int calMin = Integer.parseInt(this.getInitParameter("calendarMinute"));
		
		Timer timer = new Timer();
		Calendar date = Calendar.getInstance();
		//date.set(Calendar.HOUR_OF_DAY, 17);
		date.set(Calendar.MINUTE, calMin);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		timer.schedule(new AutoApprove(day, min), date.getTime(), 1000*60*60);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		RequestDispatcher rd;
		
		String[] tokens = url.split("/");
		String action = (tokens[tokens.length - 1]);
		action = action.substring(0, action.length() - 3).toLowerCase();
		
		logger.debug("doGet(): " + action);
		
		switch(action) {
		case "login":
			rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
			break;
		case "register":
			rd = request.getRequestDispatcher("RegisterServlet");
			rd.forward(request, response);
			break;
		case "logout":
			rd = request.getRequestDispatcher("../LogoutServlet");
			rd.forward(request, response);
			break;
		case "newreimburse":
			rd = request.getRequestDispatcher("../NewReimburseServlet");
			rd.forward(request, response);
		default:
			response.sendError(404);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
