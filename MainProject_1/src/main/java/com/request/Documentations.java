package com.request;

import java.io.File;
import java.sql.Blob;

public class Documentations {

	private Integer DocRequestId;
	private Integer RequestId;
	private Integer EmployeeId;
	private Integer AuthorizerId;
	private String AuthorizerTitle;
	private String Document;
	private String DocumentType;
	private Blob Doc;
	private Integer FileAttached;
	
	public Documentations(Integer docRequestId, Integer requestId, Integer employeeId, Integer authorizerId,
			String authorizerTitle, String document, String documentType, Blob doc, Integer fileAttached) {
		super();
		DocRequestId = docRequestId;
		RequestId = requestId;
		EmployeeId = employeeId;
		AuthorizerId = authorizerId;
		AuthorizerTitle = authorizerTitle;
		Document = document;
		DocumentType = documentType;
		Doc = doc;
		FileAttached = fileAttached;
	}

	public Documentations(Integer docRequestId, Integer requestId, Integer employeeId,
			String document, String documentType) {
		super();
		DocRequestId = docRequestId;
		RequestId = requestId;
		EmployeeId = employeeId;
		Document = document;
		DocumentType = documentType;
	}
	

	public Documentations(Integer docRequestId, Integer requestId, String authorizerTitle, String document) {
		super();
		DocRequestId = docRequestId;
		RequestId = requestId;
		AuthorizerTitle = authorizerTitle;
		Document = document;
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

	public Integer getAuthorizerId() {
		return AuthorizerId;
	}

	public void setAuthorizerId(Integer authorizerId) {
		AuthorizerId = authorizerId;
	}

	public String getAuthorizerTitle() {
		return AuthorizerTitle;
	}

	public void setAuthorizerTitle(String authorizerTitle) {
		AuthorizerTitle = authorizerTitle;
	}

	public String getDocument() {
		return Document;
	}

	public void setDocument(String document) {
		Document = document;
	}

	public String getDocumentType() {
		return DocumentType;
	}

	public void setDocumentType(String documentType) {
		DocumentType = documentType;
	}

	public Blob getDoc() {
		return Doc;
	}

	public void setDoc(Blob doc) {
		Doc = doc;
	}

	public Integer getFileAttached() {
		return FileAttached;
	}

	public void setFileAttached(Integer fileAttached) {
		FileAttached = fileAttached;
	}
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
}
