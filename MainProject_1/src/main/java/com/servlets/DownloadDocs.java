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
       

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String RCI = request.getParameter("req_ID");
		
		Integer RequestDocID = Integer.parseInt(RCI);
		RequestDao Doc = new RequestDaoImp();
		
		System.out.println(RequestDocID);
		
		if (Doc.getDocType(RequestDocID).equals("TEXT"))
		response.setContentType( "text/plain" );
		
		else if (Doc.getDocType(RequestDocID).equals("DOC"))
		response.setContentType( "application/msword" );
		
		else if (Doc.getDocType(RequestDocID).equals("PDF"))
		response.setContentType( "application/pdf" );
		
		else if (Doc.getDocType(RequestDocID).equals("POWERPOINT"))
		response.setContentType( "application/ppt" );
		
		else if (Doc.getDocType(RequestDocID).equals("OTHER"))
		response.setContentType( "application/octet-stream" );
	
	
		
		
		InputStream is = new ByteArrayInputStream(Doc.downloadDoc(RequestDocID));
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
