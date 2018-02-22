package com.services;

import com.dao.RequestDao;
import com.dao.RequestDaoImp;
import com.request.RequestTR;

public class ApplyTR {

	public static boolean SendRequest(RequestTR tr) {
		
		RequestDao dao = new RequestDaoImp();
		if(dao.addRequest(tr))
			return true;
		else
			return false;
}

	public static Integer checkBalance(Integer price, Integer employeeID) {
		RequestDao dao = new RequestDaoImp();
		
			return dao.getBalance(price, employeeID);
		
	}

	public static Integer adjustedBalance(Integer employeeID) {
		RequestDao dao = new RequestDaoImp();
		
		return dao.setReimbursment(employeeID);
	}

	public static void adjustAssociateBalance(Integer amount_Requested, Integer employeeId) {
		
		RequestDao dao = new RequestDaoImp();
		
		dao.adjustBalance(amount_Requested, employeeId);		
	}
}
