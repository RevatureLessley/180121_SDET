package com.automercury.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MercuryBookFlight {
	@FindBy(xpath="//select[@name='creditCard']/option")
	List<WebElement> cardTypeSelect;
	@FindBy(xpath="//input[@name='creditnumber']")
	WebElement creditNumber;
	@FindBy(xpath="//select[@name='cc_exp_dt_mn']/option")
	List<WebElement> cardExpireMonth;
	@FindBy(xpath="//select[@name='cc_exp_dt_yr']/option")
	List<WebElement> cardExpireYr;
	@FindBy(xpath="//input[@name='cc_frst_name']")
	WebElement firstname;
	@FindBy(xpath="//input[@name='cc_mid_name']")
	WebElement middlename;
	@FindBy(xpath="//input[@name='cc_last_name']")
	WebElement lastname;
	@FindBy(xpath="/tr[7]/td[2]/input")
	WebElement checkTicketless;
	@FindBy(xpath="//input[@name='billAddress1']")
	WebElement billAddress1;
	@FindBy(xpath="//input[@name='billAddress2']")
	WebElement billAddress2;
	@FindBy(xpath="//input[@name='billCity']")
	WebElement billCity;
	@FindBy(xpath="//input[@name='billState']")
	WebElement billState;
	@FindBy(xpath="//input[@name='billZip']")
	WebElement billZip;
	@FindBy(xpath="//select[@name='billCountry']/option")
	List<WebElement> selectBillCountry;
	@FindBy(xpath="/tr[13]/td[2]/input")
	WebElement checkSameBillAddr;
	@FindBy(xpath="//input[@name='delAddress1']")
	WebElement delAddress1;
	@FindBy(xpath="//input[@name='delAddress2']")
	WebElement delAddress2;
	@FindBy(xpath="//input[@name='delCity']")
	WebElement delCity;
	@FindBy(xpath="//input[@name='delState']")
	WebElement delState;
	@FindBy(xpath="//input[@name='delZip']")
	WebElement delZip;
	@FindBy(xpath="//select[@name='delCountry']/option")
	List<WebElement> selectDelCountry;
	@FindBy(xpath="//input[@name='buyFlights']")
	WebElement buyFlights;
	
	public MercuryBookFlight(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void selectCardType(int type) {
		cardTypeSelect.get(type).click();
	}
	
	public void enterCardNumber(String number) {
		creditNumber.sendKeys(number);
	}
	
	public void selectMonthExpire(int month) {
		cardExpireMonth.get(month).click();
	}
	
	public void selectYearExpire(int year) {
		cardExpireMonth.get(year).click();
	}
	
	
	public void securePurchase() {
		buyFlights.click();
	}
	
	public void bookAFlight(int type, String number, int month, int year) {
		cardTypeSelect.get(type).click();
		enterCardNumber(number);
		selectMonthExpire(month);
		selectYearExpire(month);
		
		securePurchase();
	}
}
