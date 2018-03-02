package com.revature.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MercurySelectFlight {
	
	
	@FindBy(xpath="//INPUT[@type='radio']")
	List<WebElement> flight;
	@FindBy(xpath="//INPUT[@type='image']")
	WebElement submitSelectFlights;
	
	public MercurySelectFlight(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void selectDepart360(){
		flight.get(0).click();
	}
	
	public void selectDepart361(){
		flight.get(1).click();
	}
	
	public void selectDepart362(){
		flight.get(2).click();
	}
	
	public void selectDepart363(){
		flight.get(3).click();
	}
	
	public void selectReturn630(){
		flight.get(4).click();
	}
	
	public void selectReturn631(){
		flight.get(5).click();
	}
	
	public void selectReturn632(){
		flight.get(6).click();
	}
	
	public void selectReturn633(){
		flight.get(7).click();
	}
	
	
	public void submitSelectFlight(){
		submitSelectFlights.click();
	}
}
