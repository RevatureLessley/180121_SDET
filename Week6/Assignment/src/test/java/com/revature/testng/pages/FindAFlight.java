package com.revature.testng.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindAFlight {

	// Type
	@FindBy(xpath = "//input[@name='tripType']")
	List<WebElement> rbTripType;
	// Passengers
	@FindBy(xpath = "//select[@name='passCount']/option")
	List<WebElement> passCountSelect;
	// Departing from
	@FindBy(xpath = "//select[@name='fromPort']/option")
	List<WebElement> departingFrom;
	// Departing month
	@FindBy(xpath = "//select[@name='fromMonth']/option")
	List<WebElement> fromMonth;
	// Departing date
	@FindBy(xpath = "//select[@name='fromDate']/option")
	List<WebElement> fromDate;
	// Service Class
	@FindBy(xpath = "//input[@name='servClass']")
	List<WebElement> rbClassType;

	// Submit button
	@FindBy(xpath = "//input[@name='findFlights']")
	WebElement submitFindFlights;

	public FindAFlight(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void selectTripType(int type) {
		rbTripType.get(type).click();
	}

	public void selectClassType(int type) {
		rbClassType.get(type).click();
	}

	public void selectPassCount(int num) {
		passCountSelect.get(num).click();
	}

	public void selectDepartingPort(int port) {
		departingFrom.get(port).click();
	}

	public void selectDepartingMonth(int month) {
		fromMonth.get(month).click();
	}

	public void selectDepartingDate(int date) {
		fromDate.get(date).click();
	}

	public void submitFindFlight() {
		submitFindFlights.click();
	}
}
