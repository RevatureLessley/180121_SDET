package com.mercury.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class MercurySelectFlight {
private WebDriver driver;
	
	@FindBy(xpath="//input[@name='outFlight']")
	List<WebElement> rbOutFlight;
	@FindBy(xpath="//input[@name='inFlight']")
	List<WebElement> rbInFlight;
	@FindBy(xpath="//input[@name='reserveFlights']")
	WebElement reserveFlights;
	
	
	public MercurySelectFlight(WebDriver driver){
				PageFactory.initElements(driver, this);
	}
	
	public void selectOut1(){
		
		rbOutFlight.get(1).click();
	}
	
	public void selectOut2(){
		
		rbOutFlight.get(2).click();
	}
	
	public void selectOut3(){
		
		rbOutFlight.get(3).click();
	}
	
	public void selectIn1(){
		
		rbInFlight.get(1).click();
	}
	
	public void selectIn2(){
		
		rbInFlight.get(2).click();
	}
	
	public void selectIn3(){
		
		rbInFlight.get(3).click();
	}
	public void reserveFlight(){
		reserveFlights.click();
	}
}

