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
 * Servlet implementation class UserAddAttachServlet
 */
public class UserAddAttachServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(UserAddAttachServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Reimbursement r = new Reimbursement();
		r.setEmpId((int)session.getAttribute("empid"));

		//logger.info("doGet() : " + request.getParameter("rid")); 
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart) {
			logger.info("multipart request");
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			String name = "";
			
			// TODO finish setting up user attaching additional files
			// will probably just need to do some things with ReimbursementService
			try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iterator = items.iterator();
				while(iterator.hasNext()) {
					FileItem item = (FileItem)iterator.next();
					if(!item.isFormField()) { // If item is a file field
						String fileName = item.getName();
						if(fileName != "") {
							File uploadedFile = new File(fileName);
							item.write(uploadedFile);
							r.addFile(uploadedFile);
							logger.info("doGet() : filename="+fileName);
						}	
					} else {
						name = item.getFieldName();
						String value = item.getString();
						logger.info("doGet() : name=" + name + " value=" + value);
						switch(name) {
						case "rid":
							r.setReimburseId(Integer.parseInt(value));
							break;
						case "format":
							r.setAttachmentType(value);
							break;
						default:
							logger.warn("doGet() : unexpected field name");
						}
					}
				}
				
				ReimbursementService.insertAttachments(r);
			} catch(FileUploadException e) {
				logger.error(e.getMessage());
			} catch(Exception e) {
				logger.error("on last field " + name);
				e.printStackTrace(out);
			}
		} else {
			logger.info("not multipart request");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
