package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FlightFinder {
	
	@FindBy(xpath="//input[@name='tripType']")
	List<WebElement> rbTripType;
	@FindBy(xpath="//input[@name='servClass']")
	List<WebElement> rbClassType;
	@FindBy(xpath="//input[@name='findFlights']")
	WebElement submitFindFlights;
	
	@FindBy(xpath="//select[@name='fromPort']")
	WebElement fromPort;	
	@FindBy(xpath="//select[@name='toPort']")
	WebElement toPort;	
	@FindBy(xpath="//select[@name='toDay']")
	WebElement toDay;
	@FindBy(xpath="//select[@name='toMonth']")
	WebElement toMonth;
	
	public FlightFinder(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void selectOneWay(int one){
		rbTripType.get(one).click();
	}
	
	public void selectServiceClass(int serviceClass){
		rbClassType.get(serviceClass).click();
	}
	
	public void selectFromPort(int port){
		Select clickThis = new Select(fromPort);
		clickThis.selectByIndex(port);
	}
	
	public void selectToPort(int port) {
		Select clickThis = new Select(toPort);
		clickThis.selectByIndex(port);
	}
	
	public void selectToDay(int day) {
		Select clickThis = new Select(toDay);
		clickThis.selectByIndex(day);
	}
	
	public void selectToMonth(String month) {
		Select clickThis = new Select(toMonth);
		clickThis.selectByVisibleText("December");
	}
	
	public void submitFindFlight() throws InterruptedException{
		Thread.sleep(3000);
		submitFindFlights.click();
	}
}
