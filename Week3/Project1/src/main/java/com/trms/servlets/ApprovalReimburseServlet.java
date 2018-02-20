package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.trms.beans.Reimbursement;
import com.trms.services.ReimbursementService;



/**
 * Servlet implementation class PersonalReimburseServlet
 */
public class ApprovalReimburseServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(ApprovalReimburseServlet.class);
	private static final long serialVersionUID = 1L;

	// TODO add JUnit testing
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		// TODO change what the call to the database is the query
		List<Reimbursement> lr = ReimbursementService.getPersonalReimb((int)session.getAttribute("empid"));
		
		JSONArray jarr = new JSONArray();
		
		for(Reimbursement r : lr) {
			JSONObject member = new JSONObject();
			member.put("reimburseid", r.getReimburseId());
			member.put("date", r.getDate());
			member.put("eventtype", r.getEventStr());
			member.put("learncenter", r.getCenterStr());
			member.put("gradeformat", r.getFormatStr());
			member.put("cost", r.getCost());
			member.put("projReimb", r.getProjectedReimb());
			member.put("attachments", r.isFiles());
			member.put("status", r.getApproveStr());
			logger.info("doGet() : status=" + r.getApproveStr());
			jarr.put(member);
		}
		
		logger.info(jarr.toString());
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
