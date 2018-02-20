package com.dao;

import java.util.List;

import com.request.RequestTR;


public interface RequestDao {

	public boolean addRequest(RequestTR tr);
	
	public List<RequestTR> getAllSuperVisorRequests(Integer SupervisorRef);

	public boolean superApprove(Integer requestID);

	public void superDecline(Integer requestID);

	public void superDocRequest(String docsNeeded, Integer requestID, Integer referenceID);
}
