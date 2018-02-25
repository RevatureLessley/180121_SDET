package com.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RequestDao;
import com.dao.RequestDaoImp;

/**
 * Servlet implementation class DownloadDoc
 */
public class DownloadDocs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadDocs() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/pdf");
		//response.setHeader(arg0, arg1);
		
		RequestDao Doc = new RequestDaoImp();
		
		InputStream is = new ByteArrayInputStream(Doc.downloadDoc(51111));
		
		OutputStream os = response.getOutputStream();
				

		byte[] buffer = new byte[1024];
				
		while (is.read(buffer) != -1) {
			os.write(buffer);
		}
		

		os.flush();
		os.close();
		is.close();
	
	}

}
