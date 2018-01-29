package com.HKBank.code;

import java.io.IOException;

public class Driver {
	
	public static void main(String[] args) {
		
		Bank bank = new Bank();
		
		//Run Bank Application
		try {
			bank.run();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}

