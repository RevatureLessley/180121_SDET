package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PayForFlight {
	@FindBy(xpath="//input[@name='passFirst0']")
	WebElement passFirst0;
	@FindBy(xpath="//input[@name='passLast0']")
	WebElement passLast0;
	@FindBy(xpath="//input[@name='creditnumber']")
	WebElement creditNumber;
	@FindBy(xpath="//input[@name='cc_frst_name']")
	WebElement ccFirstName;
	@FindBy(xpath="//input[@name='cc_last_name']")
	WebElement ccLastName;
	@FindBy(xpath="//select[@name='cc_exp_dt_mn']")
	WebElement cc_exp_dt_mn;
	@FindBy(xpath="//select[@name='cc_exp_dt_yr']")
	WebElement cc_exp_dt_yr;
	@FindBy(xpath="//input[@name='ticketLess']")
	WebElement ticketLess;
	@FindBy(xpath="//input[@name='buyFlights']")
	WebElement buyFlights;
	
	public PayForFlight(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void passFirstName(String name) {
		passFirst0.sendKeys(name);
	}
	
	public void passLastName(String name) {
		passLast0.sendKeys(name);
	}
	
	public void creditNumber(String number) {
		creditNumber.sendKeys(number);
	}
	
	public void ccFirstName(String name) {
		ccFirstName.sendKeys(name);
	}
	public void ccLastName(String name) {
		ccLastName.sendKeys(name);
	}
	public void exp_month(int index) {
		Select exp_month = new Select(cc_exp_dt_mn);
		exp_month.selectByIndex(index);
	}
	public void exp_year(String year) {
		Select exp_year = new Select(cc_exp_dt_yr);
		exp_year.selectByValue(year);
	}
	public void selectTicketLess() {
		ticketLess.click();
	}
	
	public void selectContinue() throws InterruptedException{
		Thread.sleep(3000);
		buyFlights.click();
	}
}
