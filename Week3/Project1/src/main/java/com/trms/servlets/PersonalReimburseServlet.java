package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.trms.beans.Reimbursement;
import com.trms.services.ReimbursementService;



/**
 * Servlet implementation class PersonalReimburseServlet
 */
public class PersonalReimburseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// TODO add JUnit testing
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
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
			jarr.put(member);
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
