package com.revature.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MercuryFlightFinder {
	
	/*
	 * PageFactory is a class that aids in implementing PAge Object Model.
	 * It seeks to inject the proper instances of web elements upon instantiation of
	 * a class. It provides a more efficient way to implement POM as opposed to
	 * implementing it by normal manual means.
	 */
	
	@FindBy(xpath="//input[@name='tripType']")
	List<WebElement> rbTripType;
	@FindBy(xpath="//input[@name='servClass']")
	List<WebElement> rbClassType;
	@FindBy(xpath="//input[@name='findFlights']")
	WebElement submitFindFlights;
	
	public MercuryFlightFinder(WebDriver driver){
		//The driver is injected with instances of all of the elements above.
		PageFactory.initElements(driver, this);
	}
	
	public void selectOneWay(){
		//The constructor that injected webelements allows us to interact with them
		//directly, and the application knows to apply it to our driver.
		rbTripType.get(1).click();
	}
	
	public void selectFirstClass(){
		rbClassType.get(2).click();
	}
	
	public void selectTripType(int type){
		rbTripType.get(type).click();
	}
	public void selectClassType(int type){
		rbClassType.get(type).click();
	}
	
	public void submitFindFlight(){
		submitFindFlights.click();
	}
}
