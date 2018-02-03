package com.bank.main;

import com.bank.page.Page;

public class Driver {

	public static void main(String[] args) {
			
		boolean session = true;
		while(session) {
			switch(Page.homePage()) {
			case 1:
				System.out.println("1");
				break;
			case 2:
				System.out.println("2");
				break;
			case 3:
				session = Page.exit();
				break;
			default:
				System.out.println("Please select again\n");
			}
		}
		
	}

}
