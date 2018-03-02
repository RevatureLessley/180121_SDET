package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class BookFlightsPage {
	
	private  WebDriver driver;
	
	public BookFlightsPage(WebDriver driver) {this.driver = driver;}
	
	public void inputPassengerInfo(int totalPass, String firstName1, String firstName2, String firstName3, String firstName4,
									String lastName1, String lastName2, String lastName3, String lastName4) {
		
		if(totalPass == 1) {
			 driver.findElement(By.name("passFirst0")).sendKeys(firstName1);
			 driver.findElement(By.name("passLast0")).sendKeys(lastName1);
		}
		else if(totalPass == 2){
			 driver.findElement(By.name("passFirst0")).sendKeys(firstName1);
			 driver.findElement(By.name("passLast0")).sendKeys(lastName1);
			 driver.findElement(By.name("passFirst1")).sendKeys(firstName2);
			 driver.findElement(By.name("passLast1")).sendKeys(lastName2);
		}
		else if(totalPass == 3){
			 driver.findElement(By.name("passFirst0")).sendKeys(firstName1);
			 driver.findElement(By.name("passLast0")).sendKeys(lastName1);
			 driver.findElement(By.name("passFirst1")).sendKeys(firstName2);
			 driver.findElement(By.name("passLast1")).sendKeys(lastName2);
			 driver.findElement(By.name("passFirst2")).sendKeys(firstName3);
			 driver.findElement(By.name("passLast2")).sendKeys(lastName3);
		}
		else if(totalPass == 4){
			 driver.findElement(By.name("passFirst0")).sendKeys(firstName1);
			 driver.findElement(By.name("passLast0")).sendKeys(lastName1);
			 driver.findElement(By.name("passFirst1")).sendKeys(firstName2);
			 driver.findElement(By.name("passLast1")).sendKeys(lastName2);
			 driver.findElement(By.name("passFirst2")).sendKeys(firstName3);
			 driver.findElement(By.name("passLast2")).sendKeys(lastName3);
			 driver.findElement(By.name("passFirst3")).sendKeys(firstName4);
			 driver.findElement(By.name("passLast3")).sendKeys(lastName4);
		}
		
	}
		 
	public void inputCreditCard(String cardType, Integer cardNumber, Integer cardMonth, Integer cardYear,
								String cardFirstname, String cardMiddleName, String cardLastName ) {

		Select creditCard = new Select(driver.findElement(By.name("creditCard")));
		creditCard.selectByVisibleText(cardType);
		
		driver.findElement(By.name("creditnumber")).sendKeys(cardNumber.toString());
		  
		Select cc_exp_dt_mn = new Select(driver.findElement(By.name("cc_exp_dt_mn")));
		cc_exp_dt_mn.selectByVisibleText(cardMonth.toString());
		Select cc_exp_dt_yr = new Select(driver.findElement(By.name("cc_exp_dt_yr")));
		cc_exp_dt_yr.selectByVisibleText(cardYear.toString());
		  
		  driver.findElement(By.name("cc_frst_name")).sendKeys(cardFirstname);
		  driver.findElement(By.name("cc_mid_name")).sendKeys(cardMiddleName);
		  driver.findElement(By.name("cc_last_name")).sendKeys(cardLastName);
	}
	
	public void inputBilling(String bilAddress, String bilCity, String bilState, Integer bilZip, String bilCountry) {
		  driver.findElement(By.name("billAddress1")).sendKeys(bilAddress);
		  driver.findElement(By.name("billCity")).sendKeys(bilCity);
		  driver.findElement(By.name("billState")).clear();
		  driver.findElement(By.name("billState")).sendKeys(bilState);
		  driver.findElement(By.name("billZip")).clear();
		  driver.findElement(By.name("billZip")).sendKeys(bilZip.toString());
		  Select billCountry = new Select(driver.findElement(By.name("billCountry")));
		  billCountry.selectByVisibleText(bilCountry);
	}
	
	public void inputDelivery (String delAddress, String delCity, String delState, Integer delZip, String delCountry) {
		  driver.findElement(By.name("delAddress1")).sendKeys(delAddress);
		  driver.findElement(By.name("delCity")).sendKeys(delCity);
		  driver.findElement(By.name("delState")).clear();
		  driver.findElement(By.name("delState")).sendKeys(delState);
		  driver.findElement(By.name("delZip")).clear();
		  driver.findElement(By.name("delZip")).sendKeys(delZip.toString());
		  Select dCountry = new Select(driver.findElement(By.name("delCountry")));
		  dCountry.selectByVisibleText(delCountry);
	}
	
	public void clickSecurePurchase() {
		  driver.findElement(By.name("buyFlights")).click();
	}
	
	public void book (int totalPass, String firstName1, String firstName2, String firstName3, String firstName4,
			String lastName1, String lastName2, String lastName3, String lastName4, String cardType, 
			Integer cardNumber, Integer cardMonth, Integer cardYear, String cardFirstname, String cardMiddleName, 
			String cardLastName, String bilAddress, String bilCity, String bilState, Integer bilZip, String bilCountry,
			String delAddress, String delCity, String delState, Integer delZip, String delCountry) {
		
		inputPassengerInfo(totalPass, firstName1, firstName2, firstName3, firstName4,
			lastName1, lastName2, lastName3, lastName4);
		inputCreditCard(cardType, cardNumber, cardMonth, cardYear, cardFirstname, cardMiddleName, cardLastName );
		inputBilling(bilAddress, bilCity, bilState, bilZip,  bilCountry);
		inputDelivery (delAddress, delCity, delState,  delZip, delCountry);
		clickSecurePurchase();
	}
}
