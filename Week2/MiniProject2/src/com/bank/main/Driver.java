package com.bank.main;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.bank.page.Page;

public class Driver {

	final static Logger logger = Logger.getLogger(Driver.class);

	public static void main(String[] args) {
		// Run Bank Application
		try {
			run();
			logger.info("Application Started");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static void run() throws IOException {
		// Display content on Console
		boolean session = true;
		while(session) {
			switch(Page.homePage()) {
			case 1:
				Page.loginPage();
				break;
			case 2:
				//New User Page
				Page.newUserPage();
				break;
			case 3:
				//Exit application
				session = Page.exit();
				break;
			default:
				//Selection out of scope
				Page.outOfScope();
			}
		}
	}
}
