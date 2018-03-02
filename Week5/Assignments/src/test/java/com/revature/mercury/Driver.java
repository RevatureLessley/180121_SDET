package com.revature.mercury;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
	
	static WebDriver driver;
	static String url = "http://newtours.demoaut.com/";

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.get(url);
		
		checktitle("Welcome: Mercury Tours");
		
		driver.quit();
	}

	public static void checktitle(String expectedTitle) {
		
		String actualTitle = driver.getTitle();
		
		if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }
		
	}

}
