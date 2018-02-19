package com.trms.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.trms.beans.Reimbursement;
import com.trms.services.ReimbursementService;

/**
 * Servlet implementation class NewReimburseServlet
 */
public class NewReimburseServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(NewReimburseServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Reimbursement r = new Reimbursement();
		r.setEmpId((int)session.getAttribute("empid"));
		//get approvalLvl to add to reimbursement
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart) {
			logger.info("multipart request");
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iterator = items.iterator();
				while(iterator.hasNext()) {
					FileItem item = (FileItem)iterator.next();
					if(!item.isFormField()) { // If item is a file field
						String fileName = item.getName();
						File uploadedFile = new File(fileName);
						item.write(uploadedFile);
						r.addFile(uploadedFile);
						logger.info("doGet() : filename="+fileName);
					} else { // When item is a simple form field
						String name = item.getFieldName();
						String value = item.getString();
						logger.info("doGet() : name=" + name + " value=" + value);
					}
				}
				
				ReimbursementService.insertReimbursement(r);
			} catch(FileUploadException e) {
				logger.error(e.getMessage());
			} catch(Exception e) {
				logger.error(e.getMessage());
			}
		} else {
			logger.info("not multipart request");
		}
		
		if((Boolean)session.getAttribute("approver")) {
			out.println("<head> <meta http-equiv='Refresh' content='2;url=userhomeappr.html'>" + "</head>");
		} else {
			out.println("<head> <meta http-equiv='Refresh' content='2;url=userhome.html'>" + "</head>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Validate request and send to database 
		doGet(request, response);
	}

}
