package com.revature.day4.logging;

import org.apache.log4j.Logger;

public class Driver {

	final static Logger logger = Logger.getRootLogger();
	
	public static void main(String[] args) {
	
		Driver d = new Driver();
		d.logStuff("Test Log");
		
		
		
	}
	public void logStuff(String msg){
		logger.trace(msg);
		logger.debug(msg);
		logger.warn(msg);
		logger.info(msg);
		logger.error(msg);
		logger.fatal(msg);
		
	}

}
