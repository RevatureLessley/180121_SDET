package com.dao;

import java.util.List;

import com.request.RequestTR;


public interface RequestDao {

	public boolean addRequest(RequestTR tr);
	
	public List<RequestTR> getAllSuperVisorRequests(Integer SupervisorRef);

	public boolean superApprove(Integer requestID);

	public void superDecline(Integer requestID);

	public void superDocRequest(String docsNeeded, Integer requestID, Integer referenceID);

	public Integer getBalance(Integer price, Integer employeeID);

	public Integer setReimbursment(Integer employeeID);

	public void adjustBalance(Integer amount_Requested, Integer employeeId);
}
