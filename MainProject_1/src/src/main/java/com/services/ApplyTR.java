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
}
