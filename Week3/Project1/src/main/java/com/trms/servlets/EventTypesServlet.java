package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.trms.beans.EventType;
import com.trms.services.EventTypeService;

/**
 * Servlet implementation class EventTypesServlet
 */
public class EventTypesServlet extends HttpServlet {
	//private static final Logger logger = Logger.getLogger(EventTypesServlet.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		List<EventType> let = EventTypeService.getEventTypes();
		
		JSONArray jarr = new JSONArray();
		
		for(EventType et : let) {
			JSONObject member = new JSONObject();
			member.put("eventid", et.getEventId());
			member.put("eventname", et.getEventName());
			member.put("eventcoverage", et.getEventCoverage());
			jarr.put(member);
			//logger.info("doGet() : " + member.toString());
		}
		
		out.print(jarr.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		doGet(request, response);
	}

}
