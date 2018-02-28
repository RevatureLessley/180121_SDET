package com.revature.mercury;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

	static WebDriver driver;
	static String url = "http://newtours.demoaut.com/";
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();

		
		//Starts the driver and opens it at the specified url.
		driver.get(url);
		
		Thread.sleep(5000);
		
		//This method closes the driver and is IMPERATIVE that you include it.
		//Any time you run the application and dont invoke this method, leaves an hidden
		//chrome process in teh background that you have to close manually via the
		//task manager.
		driver.quit();
		
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

	}

}
