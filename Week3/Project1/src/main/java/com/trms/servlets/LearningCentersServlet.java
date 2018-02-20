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

import com.trms.beans.LearningCenter;
import com.trms.services.LearningCenterService;

/**
 * Servlet implementation class LearningCentersServlet
 */
public class LearningCentersServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(LearningCentersServlet.class);
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		List<LearningCenter> llc = LearningCenterService.getLearningCenters();
		
		JSONArray jarr = new JSONArray();
		
		for(LearningCenter lc : llc) {
			JSONObject member = new JSONObject();
			member.put("centerid", lc.getCenterId());
			member.put("centername", lc.getCenterName());
			jarr.put(member);
			logger.info("doGet() : " + member.toString());
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
