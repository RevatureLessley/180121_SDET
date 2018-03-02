package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class FindFlightsPage {
	
	private  WebDriver driver; 
	
	public FindFlightsPage(WebDriver driver) {this.driver = driver;}
	
	public void inputTripType (int option) {
		driver.findElements(By.name("tripType")).get(option).click();
		}
	
	public void inputPassengers (Integer pass) {
		String number = pass.toString();
		
		Select passengers = new Select(driver.findElement(By.name("passCount")));
		passengers.selectByVisibleText(number);
	}
	
	public void inputDeparting(String fromPort) {
		Select departing = new Select(driver.findElement(By.name("fromPort")));
		departing.selectByVisibleText(fromPort);
	}
	
	public void inputDepartureDate (String fromMonth, Integer fromDay) {
		String number = fromDay.toString();
		
		Select dateLeaveMonth = new Select(driver.findElement(By.name("fromMonth")));
		dateLeaveMonth.selectByVisibleText(fromMonth);
		Select dateLeaveDay = new Select(driver.findElement(By.name("fromDay")));
		dateLeaveDay.selectByVisibleText(number);
	}
	
	public void inputArriving(String toPort) {
		Select arriving = new Select(driver.findElement(By.name("toPort")));
		arriving.selectByVisibleText(toPort);
	}
	
	public void inputArrivalDate(String toMonth, Integer toDay) {
		String number = toDay.toString();
		
		Select dateReturnMoth = new Select(driver.findElement(By.name("toMonth")));
		dateReturnMoth.selectByVisibleText(toMonth);
		Select dateReturnDay = new Select(driver.findElement(By.name("toDay")));
		dateReturnDay.selectByVisibleText(number);
	}
	
	public void inputServiceClass (int sclass) {
		driver.findElements(By.name("servClass")).get(sclass).click();
	}
	
	public void inputAirline (String air) {
	Select airline = new Select(driver.findElement(By.name("airline")));
	airline.selectByVisibleText(air);
	}
	
	public void clickContinue() {
	  driver.findElement(By.name("findFlights")).click();
	}
	
	public void find (int option, Integer pass, String fromPort, String fromMonth, Integer fromDay,
						String toPort, String toMonth, Integer toDay, int sclass, String air) {
		
		inputTripType (option);
		inputPassengers (pass);
		inputDeparting(fromPort);
		inputDepartureDate (fromMonth, fromDay);
		inputArriving(toPort);
		inputArrivalDate(toMonth, toDay);
		inputServiceClass (sclass);
		inputAirline (air);
		clickContinue();
	}
}
