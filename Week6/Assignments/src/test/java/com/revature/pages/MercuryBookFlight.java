package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MercuryBookFlight {
	
	
	@FindBy(xpath="//INPUT[@type='image']")
	WebElement submitBookFlights;
	@FindBy(xpath="(//INPUT[@maxlength='60'])[1]")
	WebElement fname;
	@FindBy(xpath="(//INPUT[@maxlength='60'])[2]")
	WebElement lname;
	@FindBy(xpath="//INPUT[@type='text']")
	WebElement cnumber;
	

	public void cNumber(String cnum) {
		cnumber.sendKeys(cnum);
	}
	
	public void firstName(String firstname) {
		fname.sendKeys(firstname);
	}
	
	public void lastName(String lastname) {
		lname.sendKeys(lastname);
	}
	
	public MercuryBookFlight(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void submitBookFlights(){
		submitBookFlights.click();
	}
}
