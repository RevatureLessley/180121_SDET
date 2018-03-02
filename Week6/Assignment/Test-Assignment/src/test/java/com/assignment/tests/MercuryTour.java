package com.assignment.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class MercuryTour {
	public static WebDriver driver;
	public final String url = "http://newtours.demoaut.com/";
	
	@BeforeTest
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}
	
	@AfterTest
	public void teardown() throws InterruptedException{
		Thread.sleep(2500);
		driver.quit();
	}
	
	@Test
	public void fromWelcomeToConfirmation() {
		//Welcome Page
		Assert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
		driver.findElement(By.name("userName")).sendKeys("RRoss");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.name("login")).click();
		
		//Flight Finder Page
		Assert.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
		iterateRadio("tripType");
		iterateSelect("fromPort");
		iterateSelect("fromMonth");
		iterateSelect("fromDay");
		iterateSelect("toPort");
		iterateSelect("toMonth");
		iterateSelect("toDay");
		iterateRadio("servClass");
		iterateSelect("airline");
		driver.findElement(By.name("findFlights")).click();
		
		//Select Flight Page
		Assert.assertEquals(driver.getTitle(), "Select a Flight: Mercury Tours");
		iterateRadio("outFlight");
		iterateRadio("inFlight");
		driver.findElement(By.name("reserveFlights")).click();
		
		//Book Flight Page
		Assert.assertEquals(driver.getTitle(), "Book a Flight: Mercury Tours");
		driver.findElement(By.name("passFirst0")).sendKeys("Rick");
		driver.findElement(By.name("passLast0")).sendKeys("Ross");
		iterateSelect("pass.0.meal");
		iterateSelect("creditCard");
		driver.findElement(By.name("creditnumber")).sendKeys("1111222233331919");
		iterateSelect("cc_exp_dt_mn");
//		for year
//		still trying to make sense of this...
//		WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(30));
//        wait.Until(ExpectedConditions.ElementIsVisible(By.Id("csclose")));
//
//        driver.FindElement(By.Id("csclose")).Click();
		
		driver.findElement(By.name("cc_frst_name")).sendKeys("Rick");
		driver.findElement(By.name("cc_mid_name")).sendKeys("Rickity");
		driver.findElement(By.name("cc_last_name")).sendKeys("Ross");
		driver.findElements(By.name("ticketLess")).get(0).click();
		driver.findElements(By.name("ticketLess")).get(1).click();
		driver.findElement(By.name("billAddress1")).sendKeys("820 Street Rd");
		driver.findElement(By.name("billAddress2")).sendKeys("5th Floor");
		driver.findElement(By.name("billCity")).sendKeys("SunnySide");
		driver.findElement(By.name("billState")).sendKeys("NY");
		driver.findElement(By.name("billZip")).sendKeys("10010");
		//iterateSelect("billCountry");
		driver.findElement(By.name("delAddress1")).sendKeys("820 Street Rd");
		driver.findElement(By.name("delAddress2")).sendKeys("5th Floor");
		driver.findElement(By.name("delCity")).sendKeys("SunnySide");
		driver.findElement(By.name("delState")).sendKeys("NY");
		driver.findElement(By.name("delZip")).sendKeys("10010");
		//iterateSelect("delCountry");
		driver.findElement(By.name("buyFlights")).click();
	}
	
	public void iterateSelect(String name) {
		List<WebElement> els = driver.findElements(By.xpath("//select[@name='"+ name + "']/option"));
		for(WebElement e : els) {
			System.out.println(e);
			e.click();
		}
	}
	
	public void iterateRadio(String name) {
		List<WebElement> els = driver.findElements(By.name(name));
		for(WebElement e : els) {
			e.click();
		}
	}
}
