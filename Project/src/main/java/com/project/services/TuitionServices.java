package com.project.services;

import java.util.ArrayList;
import java.util.List;

import com.project.beans.Tuition;
import com.project.dao.TuitionDao;
import com.project.dao.TuitionDaoImp;

public class TuitionServices {

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
