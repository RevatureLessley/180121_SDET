package com.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MercuryFlightBooker {
	
	@FindBy(xpath="//input[@name='ticketLess'][1]")
	WebElement Ticketless;
	
	@FindBy(xpath="//input[@name='ticketLess'][2]")
	WebElement SameAs;
	
	@FindBy(xpath="//input[@name='passFirst0']")
	WebElement FName;
	
	@FindBy(xpath="//input[@name='passLast0']")
	WebElement LName;
	
	@FindBy(xpath="//select[@name='pass.0.meal']")
	WebElement Meal;
	
	@FindBy(xpath="//select[@name='creditCard']")
	WebElement CreditCard;

	@FindBy(xpath="//input[@name='creditnumber']")
	WebElement CreditCardNum;
	
	@FindBy(xpath="//select[@name='cc_exp_dt_mn']")
	WebElement CreditExpireMonth;
	
	@FindBy(xpath="//select[@name='cc_exp_dt_yr']")
	WebElement CreditExpireYear;
	
	@FindBy(xpath="//input[@name='cc_frst_name']")
	WebElement CreditFName;
	
	@FindBy(xpath="//input[@name='cc_last_name']")
	WebElement CreditLName;
	
	@FindBy(xpath="//input[@name='billAddress1']")
	WebElement BillAddress;
	
	@FindBy(xpath="//input[@name='billCity']")
	WebElement BillCity;
	
	@FindBy(xpath="//input[@name='billZip']")
	WebElement BillZip;
	
	@FindBy(xpath="//input[@name='billState']")
	WebElement BillState;
	
	@FindBy(xpath="//select[@name='billCountry']")
	WebElement BillCountry;
	
	@FindBy(xpath="//input[@name='buyFlights']")
	WebElement BuyFlights;
	
	
	public MercuryFlightBooker(WebDriver driver){
		PageFactory.initElements(driver, this);
		
	}
	
	public void insertFirstName(String fname){
		FName.sendKeys(fname);
	}
	
	public void insertLastName(String lname){
		LName.sendKeys(lname);
	}
	
	public void insertMeal(String meal){
		Meal.sendKeys(meal);
	}
	

	public void insertCreditCardType(String cctype){
		CreditCard.sendKeys(cctype);
	}

	public void insertCreditCard(String cc){
		CreditCardNum.sendKeys(cc);
	}
	
	public void creditExpireMonth(String month){
		CreditCardNum.sendKeys(month);
	}
	
	public void creditExpireYear(String year){
		CreditExpireYear.sendKeys(year);
	}
	
	public void creditFirstName(String fname){
		CreditFName.sendKeys(fname);
	}
	
	public void creditLastName(String lname){
		CreditLName.sendKeys(lname);
	}
	
	public void clickTicketLess(){
		Ticketless.click();
	}
	
	public void creditBillAddress(String billaddress){
		BillAddress.clear();
		BillAddress.sendKeys(billaddress);
	}
	
	public void creditBillCity(String city){
		BillCity.clear();
		BillCity.sendKeys(city);
	}
	
	public void creditBillZip(String zip){
		BillZip.clear();
		BillZip.sendKeys(zip);
	}
	
	public void creditBillState(String state){
		BillState.clear();
		BillState.sendKeys(state);
	}
	
	public void creditBillCountry(String country){
		BillCountry.sendKeys(country);
	}
	
	public void clickSameAs(){
		SameAs.click();
	}
	
	public void bookFlight(){
		BuyFlights.click();
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	