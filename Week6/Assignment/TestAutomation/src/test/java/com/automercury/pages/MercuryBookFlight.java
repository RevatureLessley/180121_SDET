package com.automercury.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MercuryBookFlight {
	WebDriver driver;
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
	@FindBy(xpath="//td[2]/input[@name='ticketLess']") 
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
	@FindBy(xpath="//tr[13]/td[2]/input")
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
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void checkAcceptAlert() {
		if(ExpectedConditions.alertIsPresent().apply(driver)!=null){
			driver.switchTo().alert().accept();
		}
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
		
		checkAcceptAlert();
	}
	
	public void enterFirstname(String inFirstname) {
		firstname.sendKeys(inFirstname);
	}
	
	public void enterMiddlename(String inMiddlename) {
		middlename.sendKeys(inMiddlename);
	}
	
	public void enterLastname(String inLastname) {
		lastname.sendKeys(inLastname);
	}
	
	public void isTicketlessTravel(int is) {
		if(checkTicketless.isSelected() && is == 0) {
			checkTicketless.click();
		} else if(!checkTicketless.isSelected() && is == 1) {
			checkTicketless.click();
		}
	}
	
	public void enterBillAddress1(String addr) {
		billAddress1.sendKeys(addr);
	}
	
	public void enterBillAddress2(String addr) {
		billAddress2.sendKeys(addr);
	}
	
	public void enterBillCity(String city) {
		billCity.sendKeys(city);
	}
	
	public void enterBillState(String state) {
		billState.sendKeys(state);
	}
	
	public void enterBillPostalCode(String zip) {
		billZip.sendKeys(zip);
	}
	
	public void selectCountryBill(int country) {
		selectBillCountry.get(country).click();
	}
	
	public void isSameAsBillAddr(int is) {
		if(checkSameBillAddr.isSelected() && is == 0) {
			checkSameBillAddr.click();
		} else if(!checkSameBillAddr.isSelected() && is == 1) {
			checkSameBillAddr.click();
		}
	}
	
	public void enterDelAddress1(String addr) {
		delAddress1.sendKeys(addr);
	}
	
	public void enterDelAddress2(String addr) {
		delAddress2.sendKeys(addr);
	}
	
	public void enterDelCity(String city) {
		delCity.sendKeys(city);
	}
	
	public void enterDelState(String state) {
		delState.sendKeys(state);
	}
	
	public void enterDelPostalCode(String zip) {
		delZip.sendKeys(zip);
	}
	
	public void selectCountryDel(int country) {
		selectDelCountry.get(country).click();
		
		checkAcceptAlert();
	}
	
	public void securePurchase() {
		buyFlights.click();
	}
	
	public void bookAFlight(int type, String number, int month, int year, String fname, String mname, String lname, int isTicketless, String addr1,
			String addr2, String city, String state, String zip, int country, int isSameBill, String addr3, String addr4, String city1,
			String state1, String zip1, int country1) {
		cardTypeSelect.get(type).click();
		enterCardNumber(number);
		selectMonthExpire(month);
		selectYearExpire(month);
		enterFirstname(fname);
		enterMiddlename(mname);
		enterLastname(lname);
		isTicketlessTravel(isTicketless);
		enterBillAddress1(addr1);
		enterBillAddress2(addr2);
		enterBillCity(city);
		enterBillState(state);
		enterBillPostalCode(zip);
		selectCountryBill(country);
		isSameAsBillAddr(isSameBill);
		if(isSameBill == 0) {
			enterDelAddress1(addr3);
			enterDelAddress2(addr4);
			enterDelCity(city1);
			enterDelState(state1);
			enterDelPostalCode(zip1);
			selectCountryDel(country1);
		}

		securePurchase();
	}
}
