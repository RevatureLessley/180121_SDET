package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class FormsServlet
 */
public class FormsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String sName;
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println(config.getServletName() + " instantiated");
		sName = config.getServletName();
	}


	public void destroy() {
		System.out.println(sName + " destroyed.");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<h1>GET</h1>");
		doWork(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<h1>POST</h1>");
		doWork(request, response);
		
	}
	
	protected void doWork(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		out.println(
					"<table border=1px>"	+
							"<tr><th>ParamName</th><th>ParamValue</th></tr>"
				);
		
		Enumeration e = request.getParameterNames();
		while(e.hasMoreElements()){
			String paramName = (String)e.nextElement();
			String paramValue = "";
			if(request.getParameterValues(paramName).length==1){
				paramValue = request.getParameter(paramName);
			}else{
				String vals[] = request.getParameterValues(paramName);
				for(String s : vals){
					paramValue += (s + " ");
				}
			}
			
			out.println("<tr><td>" + paramName + "</td><td>" + paramValue + "</td></tr>");
			System.out.println(request.getParameterValues(paramName).length);
		}
		
		out.println("</table>");
		
		HtmlTemplates.goBackButton(out);
		
	}

}
