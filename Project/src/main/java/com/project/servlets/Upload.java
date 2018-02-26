package com.project.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.project.beans.Tuition;
import com.project.dao.TuitionDao;
import com.project.dao.TuitionDaoImp;
import com.project.services.TuitionServices;


@MultipartConfig
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * this servlet helps to upload the file attachment to tuition form
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Upload servlet");
		TuitionDao dao = new TuitionDaoImp();
		
		int t_id = Integer.parseInt(request.getParameter("u_id"));
		System.out.println(t_id);
		Tuition t = TuitionServices.getTuitionById(t_id);
		t.setFile_type(request.getParameter("file_type"));
		
		Part content = request.getPart("attachment");
		InputStream is = null;
		ByteArrayOutputStream os = null;

		try {
			is = content.getInputStream();
			os = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			
			while (is.read(buffer) != -1) {
				os.write(buffer);
			}
			System.out.println("file done writing");
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
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(dao.addFile(t)) {
			out.print("<h1>File Upload SUCCESSFUL</h1>");
			out.println("<hr><input type='button' value='GO BACK' onclick='goBack()'>"
					+ "<script>function goBack(){ window.history.back(); }</script>");
		}else {
			out.print("<h1>File Upload FAILED</h1>");
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
