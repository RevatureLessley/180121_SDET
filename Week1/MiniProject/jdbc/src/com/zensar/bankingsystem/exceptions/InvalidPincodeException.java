package com.zensar.bankingsystem.exceptions;

public class InvalidPincodeException extends Exception {

	public InvalidPincodeException() {
		// TODO Auto-generated constructor stub
		this("Invalid Pin");
	}

	public InvalidPincodeException(String message) {
		super(message);
		
		// TODO Auto-generated constructor stub
	}

	public InvalidPincodeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidPincodeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidPincodeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}