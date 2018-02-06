package com.banksystem.util;
import org.apache.log4j.Logger;


public class BankLogger {
	
	private final static Logger logger = Logger.getRootLogger();

	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	public static void traceMsg(String msg){
		logger.trace(msg);
	}
	
	public static void debugMsg(String msg){
		logger.debug(msg);
	}
	
	public static void infoMsg(String msg){
		logger.info(msg);
	}
	
	public static void warnMsg(String msg){
		logger.warn(msg);
	}
	
	public static void errorMsg(String msg){
		logger.error(msg);
	}
	
	public static void fatalMsg(String msg){
		logger.fatal(msg);
	}

}
