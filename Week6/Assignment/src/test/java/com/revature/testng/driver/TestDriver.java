package com.revature.testng.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.testng.pages.BookFlight;
import com.revature.testng.pages.FindAFlight;
import com.revature.testng.pages.SelectFlight;
import com.revature.testng.pages.Welcome;


public class TestDriver {
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
		Thread.sleep(2000);
		driver.quit();
	}
	
	@Test
	public void confirmHomepage(){
		Assert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
	}
	
	@Test(dependsOnMethods="confirmHomepage")
	public void login(){
		Welcome welcome = new Welcome(driver);
		welcome.driverLogInToMercury("bobbert", "bobbert");
		Assert.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
	}
	
	@Test(dependsOnMethods="login")
	public void findFlight(){
		FindAFlight findFlightPage = new FindAFlight(driver);
		findFlightPage.selectTripType(1);
		findFlightPage.selectPassCount(2);
		findFlightPage.selectDepartingPort(5);
		findFlightPage.submitFindFlight();
		Assert.assertEquals(driver.getTitle(), "Select a Flight: Mercury Tours");
	}
	
	@Test(dependsOnMethods="findFlight")
	public void selectFlight(){
		SelectFlight selectFlightPage = new SelectFlight(driver);
		selectFlightPage.selectInFlight(2);
		selectFlightPage.selectOutFlight(3);
		selectFlightPage.submit();
		Assert.assertEquals(driver.getTitle(), "Book a Flight: Mercury Tours");
	}
	
	@Test(dependsOnMethods="selectFlight")
	public void BookFlight(){
		BookFlight bookFlightPage = new BookFlight(driver);
		bookFlightPage.fillFirstName("bobbert");
		bookFlightPage.fillLasttName("bobbert");
		bookFlightPage.selectMeal(5);
		bookFlightPage.submit();
		Assert.assertEquals(driver.getTitle(), "Flight Confirmation: Mercury Tours");
	}
	
	
	

}
