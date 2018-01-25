package com.revature.day4.logging;

import org.apache.log4j.Logger;

public class Driver {
	final static Logger logger = Logger.getRootLogger();
	
	public static void main(String[] args) {
		Driver d = new Driver();
		d.logStuff("Test Log");
	}
	
	public void logStuff(String message) {
		logger.trace(message);
		logger.debug(message);
		logger.info(message);
		logger.warn(message);
		logger.error(message);
		logger.fatal(message);
		
	}
}
