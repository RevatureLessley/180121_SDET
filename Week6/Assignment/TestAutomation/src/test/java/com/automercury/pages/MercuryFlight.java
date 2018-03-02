package com.automercury.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MercuryFlight {
	@FindBy(xpath="//input[@name='outFlight']")
	List<WebElement> rbOutFlights;
	@FindBy(xpath="//input[@name='inFlight']")
	List<WebElement> rbInFlights;
	@FindBy(xpath="//input[@name='reserveFlights']")
	WebElement btnReserveFlights;
	
	public MercuryFlight(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void selectOutFlight(int flight) {
		rbOutFlights.get(flight).click();
	}
	
	public void selectInFlight(int flight) {
		rbInFlights.get(flight).click();
	}
	
	public void nextPage() {
		btnReserveFlights.click();
	}
	
	public void selectDepartReturnFlights(int flight0, int flight1) {
		selectOutFlight(flight0);
		selectInFlight(flight1);
		nextPage();
	}
}
