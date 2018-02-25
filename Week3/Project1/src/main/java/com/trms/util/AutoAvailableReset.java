package com.trms.util;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.trms.services.EmployeeService;

public class AutoAvailableReset extends TimerTask {
	private static final Logger logger = Logger.getLogger(AutoAvailableReset.class);

	public AutoAvailableReset() {
	}
	
	@Override
	public void run() {
		EmployeeService.resetAllAvailReimb();
		logger.info("run() : EmployeeService method ran"); 
	}	

}
