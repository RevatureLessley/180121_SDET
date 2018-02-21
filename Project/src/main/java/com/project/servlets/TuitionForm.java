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
import com.project.dao.TuitionDao;
import com.project.dao.TuitionDaoImp;

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
		
		//System.out.println(t);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		TuitionDao dao = new TuitionDaoImp();
		if(dao.addTuition(t)) {
			out.print("<h1>Tuition Reimbursement request SUCCESSFUL</h1>");
			out.println("<hr><input type='button' value='GO BACK' onclick='goBack()'>"
					+ "<script>function goBack(){ window.history.back(); }</script>");
		}else {
			out.print("<h1>Tuition Reimbursement request FAILED</h1>");
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
