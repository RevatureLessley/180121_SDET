package com.revature.servlets;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.IOException;
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

import com.revature.beans.Attachments;
import com.revature.service.DataService;
import com.revature.util.HTMLTemplates;

public class NewReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dateOf = "ERROR";	
		String time = "ERROR";	
		String location = "ERROR";	
		String desc = "ERROR";	
		int cost = -1000;
		String gradingFormat = "ERROR";	
		String typeOfEvent = "ERROR";	
		String work = "ERROR";	
		int attachment_bit = 0;
		
		String customgradeformat = "ERROR";																					
		String customgradedesc = "ERR0R";

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		List<File> attachments = new ArrayList<File>();


		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			HTMLTemplates.headers(out);
			HTMLTemplates.navbar(out);
			out.println("No Files/No Valid files Detected. Please try again!<br/>");
			return;
		}

		FileItemFactory factory = new DiskFileItemFactory(); //Create A FileItemFactory object
		ServletFileUpload upload = new ServletFileUpload(factory); //Create a ServletFileUpload object
		
		try {
			List<FileItem> fields = upload.parseRequest(request); // Parse the request into a list of FileItems
			Iterator<FileItem> it = fields.iterator();			// Create an iterator for the list that was created above
			
			while(it.hasNext()) { // iterate through the list while there are still elements within the list of FileItems.
				FileItem file = (FileItem)it.next(); //Pull the next object in line in the list that the iterator is pointing too
					
				if(!file.isFormField()) {
					if(!file.getName().equals("")) {
						attachment_bit = 1;
				
						String fileName = file.getName(); // Acquire name of the file and save it in a string.
						File attachedFile = new File(fileName); // Create a new file with the name of the attachment file from the list.
						file.write(attachedFile); // Write the file that was in the list, into the newly created file with the files name.
						attachments.add(attachedFile);
					} 
				} 
					
					else {
					if(file.getFieldName().equals("typeofevent")) {typeOfEvent = file.getString();}  					// Type Of Event  
					else if(file.getFieldName().equals("desc")) {desc = file.getString();} 								//	Description
					else if(file.getFieldName().equals("work")) {work = file.getString();} 								// Work Related Justification
					else if(file.getFieldName().equals("grade")) {gradingFormat = file.getString();} 					// Grading format
					else if(file.getFieldName().equals("location")) {location = file.getString();} 						// Location
					else if(file.getFieldName().equals("dateof")) {dateOf = file.getString();} 							// Date Of Event
					else if(file.getFieldName().equals("timeof")) {time = file.getString();}							// Time Of Event
					else if(file.getFieldName().equals("cost")) {cost =  Integer.parseInt(file.getString());}			// Cost
					else if(file.getFieldName().equals("customgradeformat")) {customgradeformat =  file.getString();}	// Custom Grade Format
					else if(file.getFieldName().equals("customgradedesc")) {customgradedesc	 =  file.getString();}		// Custom Grade Description
				}
			}
		} catch (FileUploadException e) {e.printStackTrace();}
		  catch (Exception e) {e.printStackTrace();}
		
		HttpSession session = request.getSession(false);
		
		int empid = (int) session.getAttribute("empid");
		String fname = (String) session.getAttribute("fname");
		String lname = (String) session.getAttribute("lname");
		
		int rei_id = DataService.insertNewReimbursement(empid, fname, lname, dateOf, time,  location,
				desc, cost, gradingFormat, typeOfEvent, work, attachment_bit);
		if (attachment_bit == 1) { 
			Attachments attachment = new Attachments(rei_id,attachments);
			DataService.addAttachment(attachment);
			};
			
		if(gradingFormat.equals("Other")) {	DataService.insertCustomGradingFormat(rei_id, empid, fname, lname, customgradeformat, customgradedesc);};
		
		HTMLTemplates.headers(out);
		HTMLTemplates.navbar(out);
		out.println("<div class='well' ><h1>Your Reimburse was submitted SUCCESSFULLY!</h1><div>");
		HTMLTemplates.goBackButton(out);
			
	}
}
