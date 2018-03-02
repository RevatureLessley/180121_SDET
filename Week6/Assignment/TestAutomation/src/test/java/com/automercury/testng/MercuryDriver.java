package com.automercury.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MercuryDriver {
	public static WebDriver driver;
	public final String url = "http://newtours.demoaut.com/";

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

	@Test(priority=0)
	public void confirmHomepage() {
		AssertJUnit.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
	}
	
	@Test
	public void loginToMercury() {
		
	}
	
	@Test
	public void flightDetailsAndPrefs() {
		
	}
	
	@Test
	public void selectDepartAndReturn() {
		
	}
	
	@Test
	public void bookFlight() {
		
	}
	
	@Test
	public void logOut() {
		
	}
}
