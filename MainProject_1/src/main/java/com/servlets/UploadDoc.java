package com.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dao.RequestDao;
import com.dao.RequestDaoImp;

@MultipartConfig

public class UploadDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadDoc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Part file = request.getPart("FileAttach");
		Integer RequestDocID =  Integer.parseInt(request.getParameter("req_Doc_ID"));
		
		InputStream is = null;
		ByteArrayOutputStream os = null;

		try {
			is = file.getInputStream();
			os = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];

			while (is.read(buffer) != -1) {
				os.write(buffer);
			}
			
			byte[] file_arr = os.toByteArray();
			
		
			RequestDao doc = new RequestDaoImp();
			doc.uploadDoc(file_arr, RequestDocID);
			
			
		} catch (IOException e) {
			System.out.println("Could not upload file!");
			e.printStackTrace();
		} finally {
			if (is != null)
				is.close();
			if (os != null)
				os.close();
		}
		
	}

}
