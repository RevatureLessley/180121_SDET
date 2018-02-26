package com.services;

import java.util.List;

import com.dao.RequestDao;
import com.dao.RequestDaoImp;
import com.request.Documentations;
import com.request.RequestTR;

public class RequestService {

	public static List<RequestTR> displayRequests(Integer AccountID, String usertype) {
		RequestDao dao = new RequestDaoImp();
		
		if (usertype.equals("Supervisor"))
			return dao.getAllSuperVisorRequests(AccountID);
		else if (usertype.equals("DeptHead"))
			return dao.getAllDeptHeadRequests(AccountID);
		else if (usertype.equals("BenCo"))
			return dao.getAllBenCoRequests(AccountID);
		else
			return null;
	}
	
	public static boolean approveRequest(Integer requestID, String usertype) {
		RequestDao dao = new RequestDaoImp();
		
		if (usertype.equals("Supervisor"))
			return dao.superApprove(requestID);	
		else if (usertype.equals("DeptHead"))
			return dao.deptApprove(requestID);	
		else if (usertype.equals("BenCo"))
			return dao.bencoApprove(requestID);
		else
			return false;
	}

	public static void declineRequest(Integer requestID, String usertype) {
		RequestDao dao = new RequestDaoImp();
		if (usertype.equals("Supervisor"))
			dao.superDecline(requestID);
		else if (usertype.equals("DeptHead"))
			dao.deptDecline(requestID);	
		else if (usertype.equals("BenCo"))
			dao.bencoDecline(requestID);	
	}

	public static void docsNeeded(String docsNeeded, Integer requestID, String usertype, Integer AuthorizerID) {
		RequestDao dao = new RequestDaoImp();
		if (usertype.equals("Supervisor"))
			dao.superDocRequest(docsNeeded, requestID, AuthorizerID);
		else if (usertype.equals("DeptHead"))
			dao.deptDocRequest(docsNeeded, requestID, AuthorizerID);
		else if (usertype.equals("BenCo"))
			dao.bencoDocRequest(docsNeeded, requestID, AuthorizerID);
		
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

	public static List<Documentations> displayDocRequests(Integer AuthorizerId) {
		RequestDao dao = new RequestDaoImp();
		return dao.getDocRequestsAdmin(AuthorizerId);
	}

	public static List<Documentations> displayUserDocRequests(Integer employeeID) {
		RequestDao dao = new RequestDaoImp();
		return dao.getDocRequestsUser(employeeID);
	}

}
