package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MercuryFlightSelector {

	private WebDriver driver;
	
	@FindBy(xpath="//input[@name='outFlight']")
	List<WebElement> outFlight;
	@FindBy(xpath="//input[@name='inFlight']")
	List<WebElement> inFlight;
	@FindBy(xpath="//input[@name='reserveFlights']")
	WebElement reserveFlights;
	
	
	public MercuryFlightSelector(WebDriver driver){
				PageFactory.initElements(driver, this);
	}
	
	public void selectPangaeaOut(){
		
		outFlight.get(2).click();
	}
	
	public void selectUnifiedOut(){
		
		outFlight.get(3).click();
	}
	
	public void selectPangaeaIn(){
		
		inFlight.get(2).click();
	}
	
	public void selectUnifiedIn(){
		
		inFlight.get(3).click();
	}
	
	public void reserveFlight(){
		reserveFlights.click();
	}
}
