package com.zensar.bankingsystem.exceptions;

public class InvalidAccountNoException extends Exception {

	public InvalidAccountNoException() {
		this("Please enter a valid account no");
	}

	public InvalidAccountNoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountNoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountNoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountNoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}