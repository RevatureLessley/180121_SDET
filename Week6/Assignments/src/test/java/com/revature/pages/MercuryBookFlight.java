package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MercuryBookFlight {
	
	
	@FindBy(xpath="//INPUT[@type='image']")
	WebElement submitBookFlights;
	
	public MercuryBookFlight(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void submitBookFlights(){
		submitBookFlights.click();
	}
}
