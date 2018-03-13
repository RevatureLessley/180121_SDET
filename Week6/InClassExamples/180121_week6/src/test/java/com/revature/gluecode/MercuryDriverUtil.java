package com.revature.gluecode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class MercuryDriverUtil {
	public static WebDriver driver;
	
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
