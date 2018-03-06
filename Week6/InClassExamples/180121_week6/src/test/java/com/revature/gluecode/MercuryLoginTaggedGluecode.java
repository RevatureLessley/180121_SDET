package com.revature.gluecode;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.revature.mercury.Driver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static com.revature.gluecode.MercuryDriverUtility.driver;

public class MercuryLoginTaggedGluecode {
	
	//Background is calling THIS method implementation
	@Given("^a user is at the mercury tours homepage$")
	public void a_user_is_at_the_mercury_tours_homepage() throws Throwable {
	    
		assertEquals("Welcome: Mercury Tours", driver.getTitle());
		

	}

	@When("^a user inputs their \"([^\"]*)\" and \"([^\"]*)\"$")
	public void a_user_inputs_their_and(String username, String password) throws Throwable {
	    driver.findElement(By.name("userName")).sendKeys(username);
	    assertEquals(username, driver.findElement(By.name("userName")).getAttribute("value"));
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
	
	@When("^After inputting username$")
	public void after_inputting_username() throws Throwable {
	    driver.findElement(By.name("userName")).sendKeys("bobbert");
	    
	}

	@When("^password,$")
	public void password() throws Throwable {
		driver.findElement(By.name("password")).sendKeys("bobbert");
	}

	@When("^a user logs in$")
	public void a_user_logs_in() throws Throwable {
		driver.findElement(By.name("login")).click();
	}

	@Then("^the use is having a great time at the find flights page$")
	public void the_use_is_having_a_great_time_at_the_find_flights_page() throws Throwable {
		assertEquals("Find a Flight: Mercury Tours:", driver.getTitle());

	}
	
}
