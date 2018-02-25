package com.trms.beans;

import java.io.File;
import java.sql.Blob;

/**
 * Beans class for the table that handles reimbursement's attached files
 * @author Lynda
 *
 */
public class AttachedFile {
	private int fileId;
	private int reimburseId;
	private Blob blob;
	private File file;
	private String fileName;
	private String subject;
	
	
	public AttachedFile() {
	}


	public AttachedFile(int fileId, int reimburseId, Blob blob, String fileName, String subject) {
		this.fileId = fileId;
		this.reimburseId = reimburseId;
		this.blob = blob;
		this.fileName = fileName;
		this.subject = subject;
	}

	public AttachedFile(int fileId, int reimburseId, File file, String fileName, String subject) {
		this.fileId = fileId;
		this.reimburseId = reimburseId;
		this.file = file;
		this.fileName = fileName;
		this.subject = subject;
	}

	public int getFileId() {
		return fileId;
	}


	public void setFileId(int fileId) {
		this.fileId = fileId;
	}


	public int getReimburseId() {
		return reimburseId;
	}


	public void setReimbureId(int reimburseId) {
		this.reimburseId = reimburseId;
	}


	public Blob getBlob() {
		return blob;
	}


	public void setBlob(Blob blob) {
		this.blob = blob;
	}


	public File getFile() {
		return file;
	}


	public void setFile(File file) {
		this.file = file;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}
	

	
}
