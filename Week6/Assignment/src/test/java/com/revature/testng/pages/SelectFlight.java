package com.revature.testng.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectFlight {

	// OutFlight
	@FindBy(xpath = "//input[@name='outFlight']")
	List<WebElement> rbOutFlight;
	// InFlight
	@FindBy(xpath = "//input[@name='inFlight']")
	List<WebElement> rbInFlight;

	// Submit button
	@FindBy(xpath = "//input[@name='reserveFlights']")
	WebElement submit;

	public SelectFlight(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void selectOutFlight(int choice) {
		rbOutFlight.get(choice).click();
	}
	
	public void selectInFlight(int choice) {
		rbInFlight.get(choice).click();
	}

	public void submit() {
		submit.click();
	}
}
