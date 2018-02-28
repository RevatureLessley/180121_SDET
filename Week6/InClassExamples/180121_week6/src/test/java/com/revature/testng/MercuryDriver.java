package com.revature.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class MercuryDriver {
	public static WebDriver driver;
	public final String url = "http://newtours.demoaut.com/";
	
	@BeforeTest
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}
	
	@AfterTest
	public void teardown() throws InterruptedException{
		Thread.sleep(2500);
		driver.quit();
	}
	
	@Test
	public void logInToMercury() {
		
	}
	
	@Test
	public void confirmHomepage(){
		Assert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
	}
}
