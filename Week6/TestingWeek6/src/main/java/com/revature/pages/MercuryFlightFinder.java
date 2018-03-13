package com.revature.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MercuryFlightFinder {
	@FindBy(xpath="//input[@name='tripType']")
	List <WebElement> rbTripType;
	@FindBy(xpath="//input[@name='servClass']")
	List <WebElement> rbClassType;
	@FindBy(xpath="//input[@name='findFlights']")
	WebElement submitFindFlights;
	@FindBy(xpath="//select[@name='fromPort']/option")
	List<WebElement> departingFrom;
	
	public MercuryFlightFinder(WebDriver driver) {
		// PageFactory more abstract version of Page Object Model
		// Ryan suggests using this over POM
		PageFactory.initElements(driver, this);
	}
	
	public void selectOneWay() {
		rbTripType.get(1).click();
	}
	
	public void selectFirstClass() {
		rbClassType.get(2).click();
	}
	
	public void selectTripType(int type) {
		rbTripType.get(type).click();
	}
	
	public void selectClassType(int type) {
		rbClassType.get(type).click();
	}
	
	public void submitFindFlight() {
		submitFindFlights.click();
	}
	
	public void selectDepartFrom(int from) {
		departingFrom.get(from).click();
	}
}
