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
import com.revature.service.DataService;

public class GetAllCustomGrading extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		int emp_id = (int) session.getAttribute("empid");
		
		List<CustomGrade> cust = DataService.getAllCustomGrading(emp_id);
		
		if(cust!=null){
			String myXml = "<root>";
			//Manually create the xml file.
			for(CustomGrade c : cust){
				myXml += "<customgrade>"
						+ "<c_rid>" + c.getReid() + "</c_rid>" +
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
