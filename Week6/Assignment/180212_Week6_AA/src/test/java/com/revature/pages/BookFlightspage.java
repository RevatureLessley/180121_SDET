package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class BookFlightspage {
	
	private  WebDriver driver;
	
	public BookFlightspage(WebDriver driver) {this.driver = driver;}
	
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
	
	public void input billing
		  driver.findElement(By.name("billAddress1")).sendKeys("101-09 bob street");
		  driver.findElement(By.name("billCity")).sendKeys("bob City");
		  driver.findElement(By.name("billState")).clear();
		  driver.findElement(By.name("billState")).sendKeys("BB");
		  driver.findElement(By.name("billZip")).clear();
		  driver.findElement(By.name("billZip")).sendKeys("111111");
		  Select billCountry = new Select(driver.findElement(By.name("billCountry")));
		  billCountry.selectByVisibleText("UNITED STATES");
		  
		  driver.findElement(By.name("delAddress1")).sendKeys("101-09 bob street");
		  driver.findElement(By.name("delCity")).sendKeys("bob City");
		  driver.findElement(By.name("delState")).clear();
		  driver.findElement(By.name("delState")).sendKeys("BB");
		  driver.findElement(By.name("delZip")).clear();
		  driver.findElement(By.name("delZip")).sendKeys("111111");
		  Select delCountry = new Select(driver.findElement(By.name("delCountry")));
		  delCountry.selectByVisibleText("UNITED STATES");
		  
		  driver.findElement(By.name("buyFlights")).click();
		 
}
