package com.project.services;

import java.util.ArrayList;
import java.util.List;

import com.project.beans.Tuition;
import com.project.dao.TuitionDao;
import com.project.dao.TuitionDaoImp;

public class TuitionServices {
	
	/**
	 * this method the returns approval status in text when number is given
	 * @param approval
	 * @return
	 */
	public static String getStatus(int approval) {
		
		switch(approval) {
		case 0:
			return "PENDING";
		case 1:
			return "BENCO";
		case 2:
			return "HEAD";
		case 3:
			return "SUPERVISOR";
		case -1:
			return "AWARDED";
		case -2:
			return "REJECTED";
		default:
			return "MOREINFO";
		}
	}

	/**
	 * this method returns the list of all the tuition forms  info for the given user name 
	 * @param username
	 * @return
	 */
	public static List<Tuition> getTuitionByUsername(String username) {
		TuitionDao dao = new TuitionDaoImp();
		List<Tuition> tuitions= dao.getAllTuition();
		List<Tuition> tuitionsUsername = new ArrayList<>();
		for (Tuition t : tuitions) {
			if(t.getUsername().equals(username)) {
				tuitionsUsername.add(t);
			}
		}
		return tuitionsUsername;
	}
	
	/**
	 * this method returns list of tuition form info that the given approver has to approve
	 * @param username
	 * @return
	 */
	public static List<Tuition> getTuitionByApprover(String username) {
		String title = EmployeeServices.getTitle(username);
		TuitionDao dao = new TuitionDaoImp();
		List<Tuition> tuitions= dao.getAllTuition();
		List<Tuition> tuitionsUsername = new ArrayList<>();
		for (Tuition t : tuitions) {
			if((getStatus(t.getApproval()).equals(title))||
					(t.getApproval()==0 && title.equals("BENCO"))||
						(getStatus(t.getApproval()).equals("MOREINFO"))) {
				tuitionsUsername.add(t);
			}
		}
		return tuitionsUsername;
	}

	/**
	 * this method approves tuition form given the tuition id
	 * @param t_id
	 * @return
	 */
	public static boolean approveTuition(int t_id) {
		TuitionDao dao = new TuitionDaoImp();
		List<Tuition> tuitions= dao.getAllTuition();
		for (Tuition t : tuitions) {
			if(t.getT_id() == (t_id)) {
				t.setApproval(t.getApproval()-1);
				if(dao.approveTution(t)) {
					if(t.getApproval()==0) {
						ReimbursementServices.updatePending(t);
					}else if(t.getApproval()==-1) {
						ReimbursementServices.updateAwarded(t);
					}
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * this method request more MoreInfo given the tuition id and reason 
	 * @param t_id
	 * @param reason
	 * @return
	 */
	public static boolean moreInfo(int t_id, String reason) {
		TuitionDao dao = new TuitionDaoImp();
		List<Tuition> tuitions= dao.getAllTuition();
		for (Tuition t : tuitions) {
			if(t.getT_id() == (t_id)) {
				if(t.getApproval()<5) {
					t.setApproval(t.getApproval()+10);
					t.setDescription(reason);
					if(dao.MoreInfo(t)) {
						return true;
					}
				}else {
					t.setApproval(t.getApproval()-10);
					t.setDescription(reason);
					if(dao.MoreInfo(t)) {
						return true;
					}
				}
					
				
			}
		}
		return false;
	}
	
	
	/**
	 * this method return the tuition form info for given tuition id
	 * @param t_id
	 * @return
	 */
	public static Tuition getTuitionById(int t_id) {
		TuitionDao dao = new TuitionDaoImp();
		List<Tuition> tuitions= dao.getAllTuition();
		for (Tuition t : tuitions) {
			if(t.getT_id() == (t_id)) {
				return t;
			}
		}
		return null;
	}

}
