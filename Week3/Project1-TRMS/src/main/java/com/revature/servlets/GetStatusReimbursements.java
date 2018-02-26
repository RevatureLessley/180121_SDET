package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.service.DataService;

public class GetStatusReimbursements extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		int emp_id = (int) session.getAttribute("empid");
		
		List<Status> status = DataService.statusReimbursements(emp_id);
		
		if(status!=null){
			String myXml = "<root>";
			//Manually create the xml file.
			for(Status s : status){
				myXml += "<status>"
						+ "<r_rid>" + s.getRei_id() + "</r_rid>" +
						"<r_afname>" + s.getAfname() + "</r_afname>" +
						"<r_alname>" + s.getAlname()+ "</r_alname>" +
						 "<r_status>" + interpretStatus(s.getArstatus()) + "</r_status>" +
						"<r_note>" + s.getNote() + "</r_note>" +
						"</status>";
			}
			myXml += "</root>";
			
			out.print(myXml); //State == 4
		}else{
			out.print("<root></root>"); //State == 4
		}
	}
	
	public String interpretStatus( int status) {
		if(status == 1) {return "Approved By Supervisor";}
		else if(status == 2) {return "Approved By Head Of Department";}
		else if(status == 3) {return "FULLY APPROVED!";}
		else if(status == 4) {return "Rejected By Supervisor";}
		else if(status == 5) {return "Rejected By Head Of Department";}
		else {return "Rejected By Benefits Coordinator";}
	}

}
