package com.project.services;

import java.util.List;

import com.project.beans.Reimbursement;
import com.project.dao.ReimbursementDao;
import com.project.dao.ReimbursementDaoImp;

public class ReimbursementServices {
	
	static ReimbursementDao dao = new ReimbursementDaoImp();
	
	public static double getEvent(String event) {
		if(event.equals("UNIVERSITY_COURSE")) 
			return 0.8;
		else if(event.equals("SEMINAR")) 
			return 0.6;
		else if(event.equals("CERTIFICATE_PREPARATION_CLASSES")) 
			return 0.75;
		else if(event.equals("CERTIFICATION")) 
			return 1.0;
		else if(event.equals("TECHNICAL_TRAINING")) 
			return 0.9;
		else 
			return 0.3;
	}
	
	public static double getProject(String username, String event, double cost) {
		List<Reimbursement> rs = dao.getAllReimbursement();
		for(Reimbursement r : rs) {
			if(r.getUsername().equals(username)) {
				double pending = r.getPending();
				double awarded = r.getAwarded();
				double amount =1000.0-(pending+awarded);
				double project = getEvent(event) * cost;
				if(project<=amount) {
					return project;
				}else {
					return amount;
				}
			}
		}
		return 0;
	}
}
