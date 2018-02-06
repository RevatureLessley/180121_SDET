package com.zensar.bankingsystem.exceptions;

public class InvalidAccountTypeException extends Exception {

	public InvalidAccountTypeException() {
		this("Please enter a valid account type");
	}

	public InvalidAccountTypeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountTypeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountTypeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountTypeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}