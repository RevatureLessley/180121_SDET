package com.revature.day2.exceptions;

public class BridgeBlocked extends Exception{
	/*
	 * By extending Exception, I am creating a custom checked exception.
	 * By extending RuntimeException or any children of it, I can create a custom
	 * unchecked exception.
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Bridge blocked by idiot truck driver.";
	}
}
