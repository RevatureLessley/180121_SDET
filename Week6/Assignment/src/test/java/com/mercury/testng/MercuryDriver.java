package com.mercury.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mercury.pages.MercuryBookFlight;
import com.mercury.pages.MercuryFindFlight;
import com.mercury.pages.MercurySelectFlight;



public class MercuryDriver {
	public static WebDriver driver;
	public final String url = "http://newtours.demoaut.com/";
	public MercuryDriver LoginPage;
	public MercuryFindFlight flightPage;
	public MercuryFindFlight selectFlight;
	public MercuryBookFlight bookFlight;
	private MercurySelectFlight reservePage;


	public MercuryDriver(WebDriver driver2) {
		// TODO Auto-generated constructor stub
	}

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
		LoginPage = new MercuryDriver(driver);
		LoginPage.driverLogInToMercury("bobbert", "bobbert");
		AssertJUnit.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
	}

	private void driverLogInToMercury(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	@Test(dependsOnMethods = { "confirmHomepage", "logInToMercury" },groups={"something","everything"})
	public void findFlights() throws InterruptedException {
		selectFlight = new MercuryFindFlight(driver);
		selectFlight.selectOneWay();
		selectFlight.selectFirstClass();
		selectFlight.selectFromPort("paris");
		selectFlight.selectFromMonth("June");
		selectFlight.selectFromDay("1");
		selectFlight.selectToMonth("July");
		selectFlight.selectToDay("26");
		selectFlight.selectAirLine("American Airlines");
		
		selectFlight.submitFindFlight();
		AssertJUnit.assertEquals(driver.getTitle(), "Select a Flight: Mercury Tours");
	}

	@Test(dependsOnMethods = { "confirmHomepage", "logInToMercury", "findFlights" },groups={"something","everything"})
	public void selectFlights() throws InterruptedException {
		reservePage = new MercurySelectFlight(driver);
		reservePage.selectOut1();
		reservePage.selectOut2();
		reservePage.selectIn1();
		
		
		
		reservePage.reserveFlight();
		AssertJUnit.assertEquals(driver.getTitle(), "Book a Flight: Mercury Tours");
	}

	@Test(dependsOnMethods = { "confirmHomepage", "logInToMercury", "findFlights", "selectFlights" },groups={"something","everything"})
	public void bookFlights() throws InterruptedException {
		bookFlight = new MercuryBookFlight(driver);
		bookFlight.insertFirstName("Steven");
		bookFlight.insertLastName("C");
		bookFlight.insertCreditCardType("American Express");
		bookFlight.insertCreditCard("1234432112344321");
		bookFlight.creditExpireMonth("12");
		bookFlight.creditExpireYear("2018");
		bookFlight.creditFirstName("Steven");
		bookFlight.creditLastName("C");
		bookFlight.creditBillAddress("1800 Revature Place");
		bookFlight.creditBillState("NY");
		bookFlight.creditBillCity("Bronx");
		bookFlight.creditBillZip("10453");
		bookFlight.creditBillCountry("UNITED STATES");
		
		
		
		bookFlight.bookFlight();
		AssertJUnit.assertEquals(driver.getTitle(), "Flight Confirmation: Mercury Tours");
	}
	

	

	
}