package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorld extends HttpServlet{
	public void init(){
		System.out.println("INIT()");
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		System.out.println("SERVICE()");
		//We need to designate what kind of file we are sending back.
		//(Rather, what kind of file we are creating dynamically)
		response.setContentType("text/html");
		//Here we assign a reference "out" to point at this specific servlets
		//response writer.
		PrintWriter out = response.getWriter();
		
		out.println(
				"<h3>HELLO WORLD</h3>"
				);
		
		out.println(
				"<hr><input type='button' value='GO BACK' onclick='goBack()'>"
				+ "<script>function goBack(){ window.history.back(); }</script>"
				);
	}
	
	public void destroy(){
		System.out.println("DESTROY()");
	}
}
