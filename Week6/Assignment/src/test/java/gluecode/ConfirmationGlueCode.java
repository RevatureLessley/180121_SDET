package gluecode;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.FlightFinder;
import pages.HomePage;
import pages.PayForFlight;
import pages.SelectFlight;

public class ConfirmationGlueCode {
	
	public static WebDriver driver;
	
	@Before
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://newtours.demoaut.com");		
	}
	
	@Given("^a user is at the login screen for mercury tours\\.$")
	public void givenHomePage() throws Throwable {
		assertEquals("Welcome: Mercury Tours", driver.getTitle());
	}

	@When("^a user shall input a username and a password and click submit$")
	public void thenSubmitForm() throws Throwable {
		HomePage login = new HomePage(driver);
		login.inputUsername("bobbert");
		login.inputpassword("bobbert");
		login.clickSubmit();
	}

	@Given("^a user is redirected to the flight finder page$")
	public void givenFlightFinderPage() throws Throwable {
		assertEquals("Find a Flight: Mercury Tours:", driver.getTitle());
	}

	@Then("^a user shall fill out the form$")
	public void thenSubmitFlightForm() throws Throwable {
		FlightFinder ff = new FlightFinder(driver);
		ff.selectFromPort(6);
		ff.selectToPort(3);
		ff.selectToDay(15);
		ff.selectToMonth("December");
		ff.selectOneWay(1);
		ff.selectServiceClass(2);
		ff.submitFindFlight();
	}

	@Given("^a user is redirected to the select flight page$")
	public void givenSelectFlightPage() throws Throwable {
		assertEquals("Select a Flight: Mercury Tours", driver.getTitle());
	}

	@Then("^a user shall choose a radio button from each group$")
	public void thenChooseRadioButtons() throws Throwable {
		SelectFlight sf = new SelectFlight(driver);
		sf.selectDepart(2);
		sf.selectReturn(3);
		sf.selectContinue();
	}

	@Given("^a user is redirected to the book a flight page$")
	public void givenBookPage() throws Throwable {
		assertEquals("Book a Flight: Mercury Tours", driver.getTitle());
	}

	@Then("^a user shall fill out the booking form$")
	public void thenBookingForm() throws Throwable {
		PayForFlight pay = new PayForFlight(driver);
		pay.creditNumber("123456789");
		pay.passFirstName("Bobbert");
		pay.passLastName("bobbert");
		pay.ccFirstName("Bobby");
		pay.ccLastName("Bobbertson");
		pay.exp_year("2010");
		pay.exp_month(12);
		pay.selectTicketLess();
		Thread.sleep(5000);

		pay.selectContinue();
	}

	@Given("^a user is redirected to the confirmation page$")
	public void thenConfirmation() throws Throwable {
		assertEquals("Flight Confirmation: Mercury Tours", driver.getTitle());
	}
	
	@After
	public void teardown(Scenario scenario){
		if(driver!=null){
			driver.quit();
		}
	}
	
}
