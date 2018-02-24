package com.trms.util;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.trms.beans.Reimbursement;
import com.trms.services.ReimbursementService;

public class AutoApprove extends TimerTask {
	private static final Logger logger = Logger.getLogger(AutoApprove.class);

	@Override
	public void run() {
		List<Reimbursement> lr = ReimbursementService.getAllReimburseForUpdate();
		Calendar todayDate = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
		
		for(Reimbursement r : lr) {
			Calendar actionCompleted = Calendar.getInstance();
			actionCompleted.setTime(r.getSqlTimestamp());
			actionCompleted.add(Calendar.DATE, +3);
			
			if(todayDate.after(actionCompleted)) {
				logger.info("Today's date " + todayDate.getTime() + " is after " + actionCompleted.getTime() + " so auto approve for super/head");
				if(r.getApprLvl() == 2 || r.getApprLvl() == 1) {
					ReimbursementService.updateApproval(r.getReimburseId(), 1, r.getNextApprovalId());
				} else if(r.getApprLvl() == 0) {
					logger.info("EMAIL BENCO DIRECT SUPERVISOR");
				}
			} else {
				logger.info("There is still time");
			}
			
			
		}
	}

}
