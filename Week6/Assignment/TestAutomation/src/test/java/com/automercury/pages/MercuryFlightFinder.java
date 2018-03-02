package com.automercury.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MercuryFlightFinder {
	@FindBy(xpath="//input[@name='tripType']")
	List<WebElement> rbTripType;
	@FindBy(xpath="//select[@name='passCount']/option")
	List<WebElement> selectPassengers;
	@FindBy(xpath="//select[@name='fromPort']/option")
	List<WebElement> selectFromPort;
	@FindBy(xpath="//select[@name='fromMonth']/option")
	List<WebElement> selectFromMonth;
	@FindBy(xpath="//select[@name='toPort']/option")
	List<WebElement> selectToPort;
	@FindBy(xpath="//select[@name='toMonth']/option")
	List<WebElement> selectToMonth;
	@FindBy(xpath="//input[@name='servClass']")
	List<WebElement> rbServClass;
	@FindBy(xpath="//select[@name='airline']/option")
	List<WebElement> selectAirline;
	@FindBy(xpath="//input[@name='findFlights']")
	WebElement btnFindFlights;
	
	public MercuryFlightFinder(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void selectTripType(int type) {
		rbTripType.get(type).click();
	}
	
	public void selectPassengers(int amnt) {
		selectPassengers.get(amnt).click();
	}
	
	public void selectDepartFrom(int loc) {
		selectFromPort.get(loc).click();
	}
	
	public void selectMonthFrom(int month) {
		selectFromMonth.get(month);
	}
	
	public void selectArrivTo(int loc) {
		selectToPort.get(loc).click();
	}
	
	public void selectMonthTo(int month) {
		selectToMonth.get(month).click();;
	}
	
	public void selectServClass(int service) {
		rbServClass.get(service).click();
	}
	
	public void selectAirline(int line) {
		selectAirline.get(line).click();
	}
	
	public void nextPage() {
		btnFindFlights.click();
	}
	
	public void selectFlightDetails(int type, int amnt, int loc0, int month0, int loc1, int month1, int service, int line) {
		selectTripType(type);
		selectPassengers(amnt);
		selectDepartFrom(loc0);
		selectMonthFrom(month0);
		selectArrivTo(loc1);
		selectMonthTo(month1);
		selectServClass(service);
		selectAirline(line);
		nextPage();
	}
}
