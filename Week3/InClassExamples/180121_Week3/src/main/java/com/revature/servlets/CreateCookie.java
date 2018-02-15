package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class CreateCookie
 */
public class CreateCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * A cookie is simply a file saved client side that holds data about the client
	 * and specific site.
	 * It is typically a key/value pair of some kind.
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = new Cookie(request.getParameter("cookiename"), request.getParameter("password"));
		//First we create a cookie instance that holds some kind of key/value information.
		Cookie[] cookies = request.getCookies();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//If cookie already exist, dont create one.
		if(cookies!=null){
			for(Cookie c : cookies){
				if((c.getName()).equals("Password")){
					out.println("<h3>Cookie already exists!</h3>");
					HtmlTemplates.goBackButton(out);
					return;
				}
			}
		
		}
		
		//Otherwise we create a cookie with a lifespan of 60 seconds.
		cookie.setMaxAge(60);
		cookie.setComment("Just a cookie... ya know... existin");
		response.addCookie(cookie);
		out.println("<h3>Cookie created!</h3>");
		HtmlTemplates.goBackButton(out);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
