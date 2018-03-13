package com.revature.gluecode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class MercuryDriverUtility {
	public static WebDriver driver;
	
	
	//Since thie before/after tag is included in the testrunner by the 
	//'gluecode=path' statement, these webhook tags will execute for all tests, including
	//those not within this file specifically.
	@Before
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://newtours.demoaut.com");		
	}
	
	//After runs at the end of the test.
	@After
	public void teardown(Scenario scenario){
		if(driver!=null){
			driver.quit();
		}
	}
}
