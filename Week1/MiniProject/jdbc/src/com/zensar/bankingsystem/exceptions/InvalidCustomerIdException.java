package com.zensar.bankingsystem.exceptions;

public class InvalidCustomerIdException extends Exception {

	public InvalidCustomerIdException() {
		// TODO Auto-generated constructor stub
		this("Invalid customer Id,please enter a valid customer Id");
	}

	public InvalidCustomerIdException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidCustomerIdException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidCustomerIdException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidCustomerIdException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}