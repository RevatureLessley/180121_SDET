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

	public static List<RequestTR> displayUserRequests(Integer accountID) {
		RequestDao dao = new RequestDaoImp();
		System.out.println(dao.getAllAssociateRequests(accountID));
		return dao.getAllAssociateRequests(accountID);
	}

	public static String checkStatus(Integer requestId) {
		RequestDao dao = new RequestDaoImp();
		
		return dao.checkRequestStatus(requestId);
	}

	public static String getDoc(Integer requestId) {
		
		RequestDao dao = new RequestDaoImp();
		
		if(dao.getReqDoc(requestId) != null)
			
			return "Doc Needed";
		else
			return "N/A";
	}

	public static boolean correctGrade(Integer requestId, String grade) {
		
		RequestDao dao = new RequestDaoImp();
		String letterGradeFormat = grade;
		Integer numberGradeFormat = null;
		
		if(letterGradeFormat.equals("A") || letterGradeFormat.equals("A") || letterGradeFormat.equals("B") || 
		   letterGradeFormat.equals("C") || letterGradeFormat.equals("D") || letterGradeFormat.equals("F")) {
			
				return dao.insertLetterGrade(requestId, letterGradeFormat);
				
		}else {
			
			try {
			
			numberGradeFormat = Integer.parseInt(grade);
			if(numberGradeFormat > 0 && numberGradeFormat < 100) 
					return dao.insertNumberGrade(requestId, numberGradeFormat);
			else
					return false;
			
			}catch( NumberFormatException e) 
			
			{ return false; }

		}
	}

}
