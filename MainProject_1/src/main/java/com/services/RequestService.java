package com.services;

import java.util.List;

import com.dao.RequestDao;
import com.dao.RequestDaoImp;
import com.request.RequestTR;

public class RequestService {

	public static List<RequestTR> displayRequests(Integer supervisorID) {
		RequestDao dao = new RequestDaoImp();
		System.out.println(dao.getAllSuperVisorRequests(supervisorID));
		return dao.getAllSuperVisorRequests(supervisorID);

	}
	
	public static boolean approveRequest_S(Integer requestID) {
		RequestDao dao = new RequestDaoImp();
		return dao.superApprove(requestID);	
	}

	public static void declineRequest_S(Integer requestID) {
		RequestDao dao = new RequestDaoImp();
		dao.superDecline(requestID);		
	}

	public static void docsNeeded_S(String docsNeeded, Integer requestID, Integer referenceID) {
		RequestDao dao = new RequestDaoImp();
		dao.superDocRequest(docsNeeded, requestID, referenceID);		
	}

}
