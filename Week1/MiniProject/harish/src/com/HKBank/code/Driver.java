package com.HKBank.code;

import java.io.IOException;

public class Driver {
	
	public static void main(String[] args) {
		
		Bank bank = new Bank();
		try {
			bank.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
