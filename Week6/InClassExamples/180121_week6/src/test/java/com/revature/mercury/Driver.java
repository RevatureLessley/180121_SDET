package com.revature.mercury;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

	static WebDriver driver;
	static String url = "http://newtours.demoaut.com/";
	
	public static void main(String[] args) throws InterruptedException {
		//CHROME
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();

		
		//Starts the driver and opens it at the specified url./
		driver.get(url);
		
		checkTitle("Welcome: Mercury Tours");
		
		
		//This method closes the driver and is IMPERATIVE that you include it.
		//Any time you run the application and dont invoke this method, leaves an hidden
		//chrome process in teh background that you have to close manually via the
		//task manager.
		driver.quit();
		
		/*
		 * FIREFOX
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Starts the driver and opens it at the specified url.
		driver.get(url);
		
		Thread.sleep(5000);
		
		//This method closes the driver and is IMPERATIVE that you include it.
		//Any time you run the application and dont invoke this method, leaves an hidden
		//chrome process in teh background that you have to close manually via the
		//task manager.
		driver.quit();
*/
	}
	
	public static void checkTitle(String expected){
		
		String testString = "TEST " + ": ";
		//A driver is any browser you are automating to run through your application.
		//We call getTitle FROM the driver therefore.
		String actual = driver.getTitle(); 
		
		
		if(!actual.equals(expected)){
			System.out.println(testString + "FAILED");
			System.out.println("Expected: \'" + expected + "\'");
			System.out.println("Actual:   \'" + actual + "\'");
		}else{
			System.out.println(testString + "PASSED");
		}
		
	}

}
