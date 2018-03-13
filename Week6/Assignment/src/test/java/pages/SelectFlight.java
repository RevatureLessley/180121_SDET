package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectFlight {
	@FindBy(xpath="//input[@name='outFlight']")
	List<WebElement> outFlight;
	@FindBy(xpath="//input[@name='inFlight']")
	List<WebElement> inFlight;
	@FindBy(xpath="//input[@name='reserveFlights']")
	WebElement reserveFlights;
	
	public SelectFlight(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void selectDepart(int index){
		outFlight.get(index).click();
	}
	public void selectReturn(int index){
		inFlight.get(index).click();
	}
	
	public void selectContinue() throws InterruptedException{
		Thread.sleep(3000);
		reserveFlights.click();
	}
}
