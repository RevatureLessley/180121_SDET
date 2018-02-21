package com.project.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.project.beans.Tuition;

/**
 * Servlet implementation class Tuition
 */
@MultipartConfig
public class TuitionForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In tuition servlet");
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<h1>hi</h1>");
		
		Tuition t = new Tuition ();
		t.setUsername(username); 
		System.out.println(username);
		t.setStart_date(LocalDate.parse(request.getParameter("start_date")));
		t.setEnd_date(LocalDate.parse(request.getParameter("end_date")));
		t.setCost((Double.parseDouble(request.getParameter("cost"))));
		t.setDescription(request.getParameter("description"));
		t.setLocation((request.getParameter("location")));
		System.out.println((request.getParameter("location")));
		t.setGrading_formate(request.getParameter("grading_formate"));
		t.setEvent_type((request.getParameter("event_type")));
		
		System.out.println(t);
		
		Part content = request.getPart("attachment");
		
		System.out.println("got part");
		
		InputStream is = null;
		ByteArrayOutputStream os = null;

		try {
			is = content.getInputStream();
			os = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			
			System.out.println("going to write");
			while (is.read(buffer) != -1) {
				os.write(buffer);
			}
			System.out.println("done write");
			t.setAttachment((os.toByteArray()));

		} catch (IOException e) {
			System.out.println("Could not upload file!");
			e.printStackTrace();
		} finally {
			if (is != null)
				is.close();
			if (os != null)
				os.close();
		}
		
		System.out.println(t);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
