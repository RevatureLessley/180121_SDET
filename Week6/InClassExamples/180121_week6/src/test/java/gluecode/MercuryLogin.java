package gluecode;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

//public class MercuryLoginGlue
public class MercuryLogin {
	
	public static WebDriver driver;
	
	@Given("^A user at the login screen for mercury tours\\.$")
	public void a_user_at_the_login_screen_for_mercury_tours() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://newtours.demoaut.com");
		assertEquals("Welcome: Mercury Tours", driver.getTitle());
	}

	@When("^a user shall input a \"([^\"]*)\" and a \"([^\"]*)\" and click submit$")
	public void a_user_shall_input_a_and_a_and_click_submit(String username, String password) throws Throwable {
		com.revature.pages.MercuryLogin loginPage = new com.revature.pages.MercuryLogin(driver);
		loginPage.driverLogInToMercury(username, password);
	}

	@Then("^a user shall be redirected to the find flights page$")
	public void a_user_shall_be_redirected_to_the_find_flights_page() throws Throwable {
		assertEquals("Find a Flight: Mercury Tours:",driver.getTitle());
	}
	
	@After
	public void tearDown(Scenario scenario) {
		if(driver!=null) {
			driver.quit();
		}
	}
}
