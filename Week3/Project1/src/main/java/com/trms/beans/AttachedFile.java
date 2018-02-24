package com.trms.beans;

import java.sql.Blob;

public class AttachedFile {
	private int fileId;
	private int reimburseId;
	private Blob file;
	private String fileName;
	private String subject;
	
	
	public AttachedFile() {
	}


	public AttachedFile(int fileId, int reimburseId, Blob file, String fileName, String subject) {
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


	public Blob getFile() {
		return file;
	}


	public void setFile(Blob file) {
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
