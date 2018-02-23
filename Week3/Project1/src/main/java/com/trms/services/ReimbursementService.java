package com.trms.services;


import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.trms.beans.AddedInfo;
import com.trms.beans.Employee;
import com.trms.beans.Reimbursement;
import com.trms.daos.ReimbursementDao;
import com.trms.daos.ReimbursementDaoImpl;

public class ReimbursementService {
	private final static Logger logger = Logger.getLogger(ReimbursementService.class);
	
	public static int insertReimbursement(Reimbursement r) {
		
		ReimbursementDao dao = new ReimbursementDaoImpl();
		
		String[] s = r.getTimeStr().split(":");
		String t = r.getTimeStr();
		if(s.length == 3) {
			t = String.join(":", s[0], s[1]);
		}
		
		// Converting date from string into something that can be stored in the database
		String result = LocalTime.parse(t, DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
		LocalDateTime ldt = LocalDateTime.parse((r.getDateStr() + " " + result), dtf);
		ZonedDateTime zdt = ldt.atZone(ZoneId.of("Etc/GMT+5"));
		Date d = new Date(zdt.toInstant().toEpochMilli());
		r.setDateTime(ldt);
		r.setDate(d);

		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DATE, +14);
		java.util.Date twoWeeks = cal.getTime();
		logger.info("insertReimbursementDaoImpl() : rDate=" + d + " 2wks=" + twoWeeks); 
		
		java.util.Date rDate = new java.util.Date(d.getTime());
		if(rDate.before(twoWeeks)) {
			logger.debug("insertReimbursementDaoImpl() : is urgent=" + rDate.before(twoWeeks));
			r.setUrgent(1);
		} else {
			r.setUrgent(0);
		}
		
		return dao.insertReimbursement(r);
	}
	
	public static List<Reimbursement> getPersonalReimb(int empId) {
		ReimbursementDao dao = new ReimbursementDaoImpl();
		List<Reimbursement> lr = dao.getPersonalReimb(empId);
		
		for(Reimbursement r : lr) {
			//logger.info("getPersonalReimb() : approvedNum=" + r.getApproved());
			switch (r.getApproved()) {
			case 2:
				r.setApproveStr("PENDING");
				//logger.info("getPersonalReimb() : set Pending string");
				break;
			case 1:
				r.setApproveStr("APPROVED");
				break;
			case 0:
				r.setApproveStr("REJECTED");
				break;
			default:
				r.setApproveStr("UNKNOWN");
			}
		}
		
		return lr;
	}
	
	public static List<Reimbursement> getReimburse(int empId) {
		ReimbursementDao dao = new ReimbursementDaoImpl();
		List<Reimbursement> lr = dao.getReimburse(empId);
		
		for(Reimbursement r : lr) {
			//logger.info("getPersonalReimb() : approvedNum=" + r.getApproved());
			switch (r.getApproved()) {
			case 2:
				r.setApproveStr("PENDING");
				//logger.info("getPersonalReimb() : set Pending string");
				break;
			case 1:
				r.setApproveStr("APPROVED");
				break;
			case 0:
				r.setApproveStr("REJECTED");
				break;
			default:
				r.setApproveStr("UNKNOWN");
			}
		}
		
		return lr;
	}
	
	public static Reimbursement getReimbursement(int rId) {
		ReimbursementDao dao = new ReimbursementDaoImpl();
		Reimbursement r = dao.getReimbBy(rId);
		r.setAddedInfo(dao.getAddedInfoBy(rId));
		
		return r;
	}
	
	public static int getEmpIdByReimburse(int rId) {
		ReimbursementDao dao = new ReimbursementDaoImpl();
		
		return dao.getEmpIdByReimburse(rId);
	}
	
	public static int updateApproval(int rId, int response, int empIdApprvr) {
		ReimbursementDao dao = new ReimbursementDaoImpl();
		Employee e = EmployeeService.getDepartTitle(empIdApprvr);
		int result = -1;
		// Get reimbursements approval lvl
		// If direct supervisor then get department and find department head using select statement
		// pass that to set approveid
		
		if(e.getTitleId() == 200) {
			dao.updateApproved(rId, response);
			logger.info("For benco");
		} else {
			
			if(e.getTitleId() == 50 || e.getTitleId() == 0) {
				dao.setApproveLvl(rId, 0);
				result = dao.setApproveId(rId, 3); //3 is the current BENCO emp id
				logger.info("for department head");
			} else {
				dao.setApproveLvl(rId, 1);
				result = dao.setApproveId(rId, EmployeeService.getDepartmentHead(e.getDepartmentId()));
				logger.info("for direct supervisor");
			}
		}
		
		return result;
	}
	
	public static int updateGrade(int rId, float grade) {
		ReimbursementDao dao = new ReimbursementDaoImpl();
		
		return dao.updateGrade(rId, grade);
	}
	
	public static int insertExtraInfo(AddedInfo ai) {
		ReimbursementDao dao = new ReimbursementDaoImpl();
		
		return dao.insertAddedInfo(ai);
	}
	
	public static int updateInfoReq(int rId, int empId) {
		ReimbursementDao dao = new ReimbursementDaoImpl();
		
		return dao.updateReqInfo(rId, empId);
	}
	
	public static int updateProjReimburse(int rId, float pReimb) {
		ReimbursementDao dao = new ReimbursementDaoImpl();
		
		return dao.updateProjReimb(rId, pReimb);
	}
	
	/*
	 * Award or deny amount to user and log message if awarded amount exceeded available amount
	 */
	public static int awardReimbAmount(int rId, float pReimb, int response, float aReimb, int empId) {
		ReimbursementDao dao = new ReimbursementDaoImpl();
		
		if(response == 1) {
			EmployeeService.updateAvailReimb(rId, dao.getEmpIdByReimburse(rId)); // This returns rows updated should be one
			dao.awardReimburse(rId, response);
			if(pReimb > aReimb) {
				AddedInfo ai = new AddedInfo();
				
				ai.setInfoRid(rId);
				ai.setInfoEmpId(empId);
				ai.setInfoSubject("FUNDS EXCEEDED");
				ai.setInfoMessage("$" + pReimb + " was awarded which exceeded availble amount $" + aReimb + " of the employee");
				insertExtraInfo(ai);
			}
		} else if (response == 0) {
			dao.awardReimburse(rId, response);
			// TODO if time, make is so that BenCo can submit reason why reimbursement not awarded
		} else {
			logger.warn("awardReimbAmount() : This is not a proper response to award reimbursement");
		}
		
		return 0;
	}
}

