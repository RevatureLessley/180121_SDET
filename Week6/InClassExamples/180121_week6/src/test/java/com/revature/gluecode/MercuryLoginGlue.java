package com.revature.gluecode;

import static org.junit.Assert.assertEquals;


import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.revature.keywordframework.keywords;
import com.revature.pages.MercuryLogin;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.revature.gluecode.MercuryDriverUtil.driver;

public class MercuryLoginGlue {
	
	public static MercuryLogin loginPage;
	
	@Given("^a user is at the login screen for mercury tours\\.$")
	public void reachLoginPage() throws Throwable {
		assertEquals("Welcome: Mercury Tours", driver.getTitle());
	}

	@When("^a user shall input a username and a password and click submit$")
	public void inputCredentials(DataTable table) throws Throwable {

	
		//loginPage.driverLogInToMercury(data.get(1).get(0), data.get(1).get(1));
/*		driver.findElement(By.name(data.get(1).get(0))).sendKeys(data.get(1).get(1));
		driver.findElement(By.name(data.get(2).get(0))).sendKeys(data.get(2).get(1));
		driver.findElement(By.name(data.get(3).get(0))).click();*/
		
		keywords.performAction(driver, table);
	}

	@Then("^a user shall be redirected to the find flights page$")
	public void ConfirmLanding() throws Throwable {
		assertEquals("Find a Flight: Mercury Tours:", driver.getTitle());
	}

}
