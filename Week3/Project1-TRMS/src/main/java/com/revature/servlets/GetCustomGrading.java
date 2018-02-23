package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.CustomGrade;
import com.revature.beans.Reimbursement;
import com.revature.service.DataService;

public class GetCustomGrading extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		int reid = Integer.parseInt(request.getParameter("reid_id"));
		List<CustomGrade> cust = DataService.getCustomGrading(reid);
		
		if(cust!=null){
			String myXml = "<root>";
			//Manually create the xml file.
			for(CustomGrade c : cust){
				myXml += "<customgrade>"
						+ "<c_rid>" + c.getReid() + "</c_rid>" +
						"<c_eid>" + c.getEmpid() + "</c_eid>" +
						"<c_fname>" + c.getFname() + "</c_fname>" +
						 "<c_lname>" + c.getLname() + "</c_lname>" +
						"<c_format>" + c.getCustomFormat() + "</c_format>" +
						"<r_desc>" + c.getFormatDesc() + "</r_desc>" +
						"</customgrade>";
			}
			myXml += "</root>";
			
			out.print(myXml); //State == 4
		}else{
			out.print("<root></root>"); //State == 4
		}
	}

}
