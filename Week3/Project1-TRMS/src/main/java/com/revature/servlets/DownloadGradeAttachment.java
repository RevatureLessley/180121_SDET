package com.revature.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.DataService;

public class DownloadGradeAttachment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int a_id = Integer.parseInt(request.getParameter("gbutton"));
		
        String fileName = DataService.getGradeAttachmentNameByAid(a_id);
        String fileType = DataService.getGradeAttachmentTypeByAid(a_id);

        response.setContentType(fileType);

       
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
       
        File my_file = DataService.getGradeAttachmentByAid(a_id);
        
        OutputStream out = response.getOutputStream();
        try (FileInputStream in = new FileInputStream(my_file)) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
        out.flush();
	}

}
