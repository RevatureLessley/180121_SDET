package com.revature.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.pages.MercuryBookFlight;
import com.revature.pages.MercuryFlightFinder;
import com.revature.pages.MercuryLogin;
import com.revature.pages.MercurySelectFlight;

public class MercuryDriver {
	
	public static WebDriver driver;
	public final String url = "http://newtours.demoaut.com/";
	public MercuryLogin LoginPage;
	public MercuryFlightFinder flightPage;
	public MercurySelectFlight selectFlight;
	public MercuryBookFlight bookFlight;
	
	@Test(priority = 0)
	public void confirmHomepage() {
		AssertJUnit.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
	}
	
	@Test(dependsOnMethods = "confirmHomepage")
	public void logInToMercury() {
		LoginPage = new MercuryLogin(driver);
		LoginPage.driverLogInToMercury("harish", "harish");
		AssertJUnit.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
	}
	
	@Test(dependsOnMethods = { "confirmHomepage", "logInToMercury" })
	public void findFlights() throws InterruptedException {
		flightPage = new MercuryFlightFinder(driver);
		flightPage.selectOneWay();
		flightPage.selectFirstClass();
		Thread.sleep(1000);
		flightPage.submitFindFlight();
		AssertJUnit.assertEquals(driver.getTitle(), "Select a Flight: Mercury Tours");
	}
	
	@Test(dependsOnMethods = { "confirmHomepage", "logInToMercury", "findFlights" })
	public void selectFlight() throws InterruptedException {
		selectFlight = new MercurySelectFlight(driver);
		selectFlight.selectDepart360();
		selectFlight.selectReturn633();
		Thread.sleep(1000);
		selectFlight.submitSelectFlight();
		AssertJUnit.assertEquals(driver.getTitle(), "Book a Flight: Mercury Tours");
	}

	@Test(dependsOnMethods = { "confirmHomepage", "logInToMercury", "findFlights","selectFlight" })
	public void bookFlight() throws InterruptedException {
		bookFlight = new MercuryBookFlight(driver);
		Thread.sleep(1000);
		bookFlight.submitBookFlights();
		AssertJUnit.assertEquals(driver.getTitle(), "Flight Confirmation: Mercury Tours");
	}
	  
	@BeforeTest
	public void beforeTest() {
	  	System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}

	@AfterTest
	public void afterTest() throws InterruptedException {
		Thread.sleep(2500);
		driver.quit();
	}
	
	
}
