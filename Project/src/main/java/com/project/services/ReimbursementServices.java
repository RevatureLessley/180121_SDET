package com.project.services;

import java.util.List;

import com.project.beans.Reimbursement;
import com.project.beans.Tuition;
import com.project.dao.ReimbursementDao;
import com.project.dao.ReimbursementDaoImp;

public class ReimbursementServices {
	
	static ReimbursementDao dao = new ReimbursementDaoImp();
	
	/**
	 * this method return the percent of allowable reimbursement for given event
	 * @param event
	 * @return
	 */
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
	
	/**
	 * this method calculates the projected reimbursement form event cost and employee name
	 * @param username
	 * @param event
	 * @param cost
	 * @return
	 */
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
	
	/**
	 * this method updates the pending reimbursement column in reimbursement table
	 * @param t
	 */
	public static void updatePending(Tuition t) {
		List<Reimbursement> rs = dao.getAllReimbursement();
		for(Reimbursement r : rs) {
			if(r.getUsername().equals(t.getUsername())){
				r.setPending(r.getPending()+t.getProjected());
				dao.UpdateReimbursement(r);
			}
		}
		
	}
	
	/**
	 * this method updates the awarded reimbursement column in reimbursement table
	 * @param t
	 */
	public static void updateAwarded(Tuition t) {
		List<Reimbursement> rs = dao.getAllReimbursement();
		for(Reimbursement r : rs) {
			if(r.getUsername().equals(t.getUsername())){
				r.setPending(r.getPending()-t.getProjected());
				r.setAwarded(r.getAwarded()+t.getProjected());
				dao.UpdateReimbursement(r);
			}
		}
	}
	
	/**
	 * this method returns the reimbursement status of the given user name
	 * @param username
	 * @return
	 */
	public static Reimbursement getReimburseByUsername(String username) {
		List<Reimbursement> rs = dao.getAllReimbursement();
		for(Reimbursement r : rs) {
			if(r.getUsername().equals(username)){
				return r;
			}
		}
		return null;
	}
}
