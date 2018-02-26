package com.trms.util;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.trms.beans.Reimbursement;
import com.trms.services.EmployeeService;
import com.trms.services.ReimbursementService;
import com.trms.services.UsersEmpService;

public class AutoApprove extends TimerTask {
	private static final Logger logger = Logger.getLogger(AutoApprove.class);
	private int days;
	private int mins;

	public AutoApprove(int days, int mins) {
		this.days = days;
		this.mins = mins;
	}
	
	@Override
	public void run() {
		List<Reimbursement> lr = ReimbursementService.getAllReimburseForUpdate();
		Calendar todayDate = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
		
		for(Reimbursement r : lr) {
			Calendar actionCompleted = Calendar.getInstance();
			actionCompleted.setTime(r.getSqlTimestamp());
			if(this.days == 0) {
				actionCompleted.add(Calendar.MINUTE, +mins);
			} else {
				actionCompleted.add(Calendar.DATE, +days);
			}
			
			
			if(todayDate.after(actionCompleted)) {
				logger.info("Today's date " + todayDate.getTime() + " is after " + actionCompleted.getTime() + " so auto approve for super/head");
				if(r.getApprLvl() == 2 || r.getApprLvl() == 1) {
					ReimbursementService.updateApproval(r.getReimburseId(), 1, r.getNextApprovalId());
				} else if(r.getApprLvl() == 0) {
					int bencoSuper = EmployeeService.getDepartmentHead(2); //Magic number department of benco is 2
					String email =  UsersEmpService.getUserEmail(bencoSuper);
					String subject = "Reimbursement Approval Escalation";
					String message = "Hello,\nThe benefits coordinator has not taken action on reimbursment number " + r.getReimburseId() +
							" please take action.\n\n-TRMS NO REPLY\n\nDo not reply to this email.  This is an automatic message sent from "
							+ "an unmonitored email.  You will not get a reply.";
					SendEmail.sendEmail(email, subject, message);
					logger.info("EMAIL BENCO DIRECT SUPERVISOR");
					
				}
			} else {
				logger.info("There is still time");
			}
			
			
		}
	}

}
