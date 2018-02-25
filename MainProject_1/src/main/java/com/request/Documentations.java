package com.request;

import java.io.File;
import java.sql.Blob;

public class Documentations {

	private Integer DocRequestId;
	private Integer RequestId;
	private Integer EmployeeId;
	private String AuthorizerTitle;
	private String DocumentRequested;
	private Blob Doc;
	private File file;
	private String FileName;
	private Integer FileAttached;
	
	public Documentations(Integer docRequestId, Integer requestId, Integer employeeId, String authorizerTitle,
			String documentRequested, Blob doc, File file, String fileName, Integer fileAttached) {
		super();
		DocRequestId = docRequestId;
		RequestId = requestId;
		EmployeeId = employeeId;
		AuthorizerTitle = authorizerTitle;
		DocumentRequested = documentRequested;
		Doc = doc;
		this.file = file;
		FileName = fileName;
		FileAttached = fileAttached;
	}

	public Documentations(Integer requestId, Integer employeeId, String authorizerTitle, String documentRequested,
			Blob doc, File file, String fileName, Integer fileAttached) {
		super();
		RequestId = requestId;
		EmployeeId = employeeId;
		AuthorizerTitle = authorizerTitle;
		DocumentRequested = documentRequested;
		Doc = doc;
		this.file = file;
		FileName = fileName;
		FileAttached = fileAttached;
	}

	public Integer getDocRequestId() {
		return DocRequestId;
	}

	public void setDocRequestId(Integer docRequestId) {
		DocRequestId = docRequestId;
	}

	public Integer getRequestId() {
		return RequestId;
	}

	public void setRequestId(Integer requestId) {
		RequestId = requestId;
	}

	public Integer getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		EmployeeId = employeeId;
	}

	public String getAuthorizerTitle() {
		return AuthorizerTitle;
	}

	public void setAuthorizerTitle(String authorizerTitle) {
		AuthorizerTitle = authorizerTitle;
	}

	public String getDocumentRequested() {
		return DocumentRequested;
	}

	public void setDocumentRequested(String documentRequested) {
		DocumentRequested = documentRequested;
	}

	public Blob getDoc() {
		return Doc;
	}

	public void setDoc(Blob doc) {
		Doc = doc;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public Integer getFileAttached() {
		return FileAttached;
	}

	public void setFileAttached(Integer fileAttached) {
		FileAttached = fileAttached;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
}
