package com.project.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.beans.Tuition;
import com.project.services.TuitionServices;

/**
 * Servlet implementation class Download
 */
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Download Servlet");
		
		HttpSession session = request.getSession(false);
		
		int t_id = Integer.parseInt(request.getParameter("d_id"));
		
		System.out.println(t_id);
		
		Tuition t = TuitionServices.getTuitionById(t_id);
		
		if(t.getAttachment()!=null) {
			
		
		response.setContentType("application/"+t.getFile_type());
		response.setHeader("Content-Disposition", "attachment; filename=" + "Attachment."+t.getFile_type());
				
		// Create the input stream (IN to the app FROM the tuiton)
		InputStream is = new ByteArrayInputStream(t.getAttachment());
				
		// Create the output stream (OUT of the app TO the client)
		OutputStream os = response.getOutputStream();
				
		// We're going to read and write 1KB at a time
		byte[] buffer = new byte[1024];
				
		// Reading returns -1 when there's no more data left to read.
		while (is.read(buffer) != -1) {
			os.write(buffer);
		}
		
		// Always close your streams!
		os.flush();
		os.close();
		is.close();
		
		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<h1>No File Attached</h1>");
			out.println("<hr><input type='button' value='GO BACK' onclick='goBack()'>"
					+ "<script>function goBack(){ window.history.back(); }</script>");
		}
	}
		
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
