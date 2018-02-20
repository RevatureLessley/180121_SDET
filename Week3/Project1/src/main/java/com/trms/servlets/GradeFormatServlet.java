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

import com.trms.beans.GradeFormat;
import com.trms.services.GradeFormatService;

/**
 * Servlet implementation class GradeFormatServlet
 */
public class GradeFormatServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(GradeFormatServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		List<GradeFormat> lgf = GradeFormatService.getGradeFormats();
		
		JSONArray jarr = new JSONArray();
		
		for(GradeFormat gf : lgf) {
			JSONObject member = new JSONObject();
			member.put("formatid", gf.getFormatId());
			member.put("formatname", gf.getFormatName());
			member.put("formatreq", gf.getFormatReq());
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
