package com.testng;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.MercuryFlightBooker;
import com.pages.MercuryFlightFinder;
import com.pages.MercuryFlightSelector;
import com.pages.MercuryLogin;



public class MercuryDriver {
	public static WebDriver driver;
	public final String url = "http://newtours.demoaut.com/";
	public MercuryLogin LoginPage;
	public MercuryFlightFinder flightPage;
	public MercuryFlightSelector reservePage;
	public MercuryFlightBooker bookingPage;


	@BeforeTest(groups={"setup"})
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}

	@AfterTest(groups={"teardown"})
	public void teardown() throws InterruptedException {
		Thread.sleep(2500);
		driver.quit();
	}
	
	@Test(priority = 0, groups={"something","everything"})
	public void confirmHomepage() {
		AssertJUnit.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
	}

	@Test(dependsOnMethods = "confirmHomepage",groups={"something","everything"})
	public void logInToMercury() {
		LoginPage = new MercuryLogin(driver);
		LoginPage.driverLogInToMercury("bobbert", "bobbert");
		AssertJUnit.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
	}

	@Test(dependsOnMethods = { "confirmHomepage", "logInToMercury" },groups={"something","everything"})
	public void findFlights() throws InterruptedException {
		flightPage = new MercuryFlightFinder(driver);
		flightPage.selectOneWay();
		flightPage.selectFirstClass();
		flightPage.selectFromPort("Frankfurt");
		flightPage.selectFromMonth("May");
		flightPage.selectFromDay("9");
		flightPage.selectToMonth("December");
		flightPage.selectToDay("2");
		flightPage.selectAirLine("Unified Airlines");
		Thread.sleep(3000);
		flightPage.submitFindFlight();
		AssertJUnit.assertEquals(driver.getTitle(), "Select a Flight: Mercury Tours");
	}

	@Test(dependsOnMethods = { "confirmHomepage", "logInToMercury", "findFlights" },groups={"something","everything"})
	public void selectFlights() throws InterruptedException {
		reservePage = new MercuryFlightSelector(driver);
		reservePage.selectPangaeaOut();
		reservePage.selectUnifiedOut();
		reservePage.selectPangaeaIn();
		//reservePage.selectUnifiedIn();
		Thread.sleep(3000);
		reservePage.reserveFlight();
		AssertJUnit.assertEquals(driver.getTitle(), "Book a Flight: Mercury Tours");
	}

	@Test(dependsOnMethods = { "confirmHomepage", "logInToMercury", "findFlights", "selectFlights" },groups={"something","everything"})
	public void bookFlights() throws InterruptedException {
		bookingPage = new MercuryFlightBooker(driver);
		bookingPage.insertFirstName("Bobbert");
		bookingPage.insertLastName("Bob");
		bookingPage.insertMeal("Kosher");
		bookingPage.insertCreditCardType("Visa");
		bookingPage.insertCreditCard("1234567898765432");
		bookingPage.creditExpireMonth("07");
		bookingPage.creditExpireYear("2009");
		bookingPage.creditFirstName("Bobbert");
		bookingPage.creditLastName("Bob");
		bookingPage.clickTicketLess();
		bookingPage.creditBillAddress("123 Home Of Bobbert");
		bookingPage.creditBillCity("Brooklyn");
		bookingPage.creditBillZip("112233");
		bookingPage.creditBillState("NY");
		bookingPage.creditBillCountry("UNITED STATES");
		//bookingPage.clickSameAs();
		
		Thread.sleep(3000);
		bookingPage.bookFlight();
		AssertJUnit.assertEquals(driver.getTitle(), "Flight Confirmation: Mercury Tours");
	}
	

	

	
}