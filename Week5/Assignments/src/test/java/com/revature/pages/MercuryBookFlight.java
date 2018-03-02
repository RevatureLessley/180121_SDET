package com.revature.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MercuryBookFlight {
	
	
	@FindBy(xpath="//INPUT[@type='image']")
	WebElement submitBookFlights;
	
	
	
	public void submitBookFlights(){
		submitBookFlights.click();
	}
}
