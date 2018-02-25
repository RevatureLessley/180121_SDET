package com.dao;

import java.util.List;

import com.request.RequestTR;


public interface RequestDao {

	public boolean addRequest(RequestTR tr, Integer DeptID);
	
	public List<RequestTR> getAllSuperVisorRequests(Integer SupervisorRef);

	public boolean superApprove(Integer requestID);

	public void superDecline(Integer requestID);

	public void superDocRequest(String docsNeeded, Integer requestID, Integer authorizerID);

	public Integer getBalance(Integer price, Integer employeeID);

	public Integer setReimbursment(Integer employeeID);

	public void adjustBalance(Integer amount_Requested, Integer employeeId);

	public List<RequestTR> getAllAssociateRequests(Integer accountID);

	public String checkRequestStatus(Integer requestId);

	public String getReqDoc(Integer requestId);

	public boolean insertLetterGrade(Integer requestId, String letterGradeFormat);

	public boolean insertNumberGrade(Integer requestId, Integer numberGradeFormat);

	public List<RequestTR> getAllDeptHeadRequests(Integer accountID);

	public List<RequestTR> getAllBenCoRequests(Integer accountID);

	public boolean deptApprove(Integer requestID);

	public boolean bencoApprove(Integer requestID);

	public void deptDecline(Integer requestID);

	public void bencoDecline(Integer requestID);

	public void deptDocRequest(String docsNeeded, Integer requestID, Integer authorizerID);

	public void bencoDocRequest(String docsNeeded, Integer requestID, Integer authorizerID);

	public Integer getDeptID(Integer supervisorRef);
	
	public Integer getEmployeeID(Integer RequestID);
	
	public void uploadDoc(byte[] file, Integer requestDocID);

	public byte[] downloadDoc(Integer requestDocID);
}
