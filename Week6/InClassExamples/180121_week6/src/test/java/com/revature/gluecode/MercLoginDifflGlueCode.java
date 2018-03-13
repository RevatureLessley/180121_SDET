package com.revature.gluecode;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static com.revature.gluecode.MercuryDriverUtil.driver;

public class MercLoginDifflGlueCode {

	@Given("^a user is hanging out at the login screen for mercury tours\\.$")
	public void a_user_is_hanging_out_at_the_login_screen_for_mercury_tours() throws Throwable {
		assertEquals("Welcome: Mercury Tours", driver.getTitle());
	}

	@When("^After inputting username$")
	public void after_inputting_username() throws Throwable {
		driver.findElement(By.name("userName")).sendKeys("bobbert");
	}

	@When("^password, a user logs in$")
	public void password_a_user_logs_in() throws Throwable {
		driver.findElement(By.name("password")).sendKeys("bobbert");
		driver.findElement(By.name("login")).click();
	}

	@Then("^the user is having a great time at the find flights page$")
	public void the_user_is_having_a_great_time_at_the_find_flights_page() throws Throwable {
		assertEquals("Find a Flight: Mercury Tours:", driver.getTitle());
	}

	@When("^a user inputs their \"([^\"]*)\" and \"([^\"]*)\"$")
	public void a_user_inputs_their_and(String username, String password) throws Throwable {
		driver.findElement(By.name("userName")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
	}

	@When("^then clicks submit$")
	public void then_clicks_submit() throws Throwable {
		driver.findElement(By.name("login")).click();
	}

	@Then("^the user finds themselves at the find flight page\\.$")
	public void the_user_finds_themselves_at_the_find_flight_page() throws Throwable {
		assertEquals("Find a Flight: Mercury Tours:", driver.getTitle());
	}
}
