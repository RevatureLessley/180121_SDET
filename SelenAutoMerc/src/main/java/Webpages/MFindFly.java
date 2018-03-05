package Webpages;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MFindFly {
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
	
	public MFindFly(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void selectTripType(int trip) {
		rbTripType.get(trip).click();
	}
	
	public void selectPassengers(int num) {
		selectPassengers.get(num).click();
	}
	
	public void selectDepartFrom(int whr) {
		selectFromPort.get(whr).click();
	}
	
	public void selectMonthFrom(int month) {
		selectFromMonth.get(month);
	}
	
	public void selectArrivTo(int loct) {
		selectToPort.get(loct).click();
	}
	
	public void selectMonthTo(int month) {
		selectToMonth.get(month).click();;
	}
	
	public void selectServClass(int s) {
		rbServClass.get(s).click();
	}
	
	public void selectAirline(int ali) {
		selectAirline.get(ali).click();
	}
	
	public void nextPage() {
		btnFindFlights.click();
	}
	
	public void selectFlightDetails(int type, int amnt, int fplace, int fmonth, int lplace , int lmonth, int s, int ali) {
		selectTripType(type);
		selectPassengers(amnt);
		selectDepartFrom(fplace);
		selectMonthFrom(fmonth);
		selectArrivTo(lplace);
		selectMonthTo(lmonth);
		selectServClass(s);
		selectAirline(ali);
		nextPage();
	}
}