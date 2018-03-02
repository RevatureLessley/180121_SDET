package com.pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MercuryFlightFinder {
	
	
	@FindBy(xpath="//input[@name='tripType']")
	List<WebElement> rbTripType;
	
	@FindBy(xpath="//input[@name='servClass']")
	List<WebElement> rbClassType;
	
	@FindBy(xpath="//input[@name='findFlights']")
	WebElement submitFindFlights;
	
	@FindBy(xpath="//select[@name='fromPort']")
	WebElement FromPort;
	
	@FindBy(xpath="//select[@name='fromMonth']")
	WebElement FromMonth;
	
	@FindBy(xpath="//select[@name='fromDay']")
	WebElement FromDay;
	
	@FindBy(xpath="//select[@name='toMonth']")
	WebElement ToMonth;
	
	@FindBy(xpath="//select[@name='toDay']")
	WebElement ToDay;
	
	@FindBy(xpath="//select[@name='airline']")
	WebElement Airline;
	
	
	
	
	public MercuryFlightFinder(WebDriver driver){
		PageFactory.initElements(driver, this);
		
	}
	
	public void selectOneWay(){
		
		rbTripType.get(1).click();
	}
	
	public void selectFirstClass(){
		rbClassType.get(2).click();
	}
	
	public void selectFromPort(String fromPort){
		FromPort.sendKeys(fromPort);
	}

	public void selectFromMonth(String fromMonth){
		FromMonth.sendKeys(fromMonth);
	}
	
	public void selectFromDay(String fromDay){
		FromDay.sendKeys(fromDay);
	}
	
	public void selectToMonth(String toMonth){
		ToMonth.sendKeys(toMonth);
	}
	
	public void selectToDay(String toDay){
		ToDay.sendKeys(toDay);
	}
	
	public void selectAirLine(String airline){
		Airline.sendKeys(airline);
	}
	
	
	public void submitFindFlight(){
		submitFindFlights.click();
	}
}
