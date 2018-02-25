package com.trms.servlets;

import static com.trms.util.CloseStreams.close;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.trms.beans.AttachedFile;
import com.trms.services.ReimbursementService;

/**
 * Servlet implementation class FileDownloadServlet
 */
public class FileDownloadServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(FileDownloadServlet.class);
	private List<AttachedFile> laf;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int rId = Integer.parseInt(request.getParameter("rid"));
		String zipName = "reimbursement#" + rId + "attachments.zip";
		
		byte[] zip = convertFiles(rId);
		ServletOutputStream sos = response.getOutputStream();
		response.setContentType("application/zip");
		response.setHeader("Content-disposition","attachment; filename=" + zipName);
		sos.write(zip);
		sos.flush();
	}

	private byte[] convertFiles(int rid) {
		laf = ReimbursementService.getAttachmentsByRid(rid);
		logger.info("convertFiles() : ");
		
		int B = 1000;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        byte[] buffer = new byte[B*25];
        InputStream is = null;
        OutputStream os = null;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		
		try {
			for(AttachedFile af : laf) {
				fis = new FileInputStream(af.getFile());
				bis = new BufferedInputStream(fis);
				
				zos.putNextEntry(new ZipEntry(af.getFileName()));
				
				int bytesRead;
				while((bytesRead = bis.read(buffer)) != -1) {
					zos.write(buffer, 0, bytesRead);
				}
				zos.closeEntry();
				bis.close();
				fis.close();
			}
			
			zos.flush();
			baos.flush();
			zos.close();
			baos.close();
			
		} catch(IOException io) {
			logger.error(io.getMessage());
		} finally {
			close(fis);
		}
		
		return baos.toByteArray();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
