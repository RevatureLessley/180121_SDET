package com.zensar.bankingsystem.exceptions;

public class InvalidAmountException extends Exception {

	public InvalidAmountException() {
		this("Please enter a valid amount");
	}

	public InvalidAmountException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidAmountException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAmountException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAmountException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}