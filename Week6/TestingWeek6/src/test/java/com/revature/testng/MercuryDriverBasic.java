package com.revature.testng;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MercuryDriverBasic {
	public static WebDriver driver;
	public final String url = "http://newtours.demoaut.com/";
	
	WebDriverWait wait;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		//We can configure our driver's implicit wait as soon as it is instatiated.
		// for any element wait 4 seconds before fail
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// for specific element wait before fail
		wait = new WebDriverWait(driver, 7);
		
		driver.get(url);
	}

	@AfterTest
	public void teardown() throws InterruptedException {
		Thread.sleep(2500);
		driver.quit();
	}
	
	@Test
	public void confirmHomepage() {
		Assert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
	}
	@Test
	public void logInToMercury() {
		// Our findElement aims to reference an element on the page.
		// It takes a parameter of the static class "By".
		// By represents our element selector
		/*
		 * Selenium, by default offers several selectors
		 * - Name
		 * - Id
		 * - Linktext
		 * - PartialLinkText
		 * - tagName
		 * - className
		 * -- and the most specific
		 * - xpath
		 * - cssSelector
		 */
		/*
		 * .sendKeys() simulates typing.  Use with any text fields
		 * .click() simulates clicking
		 */
		
		/* Implicit wait test
		 * driver.findElement(By.id("bobbert"));*/
		
		//Explicit wait test
		//WebElement el = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("bobbert")));
		
		Wait fwait = new FluentWait(driver)
				.withTimeout(7, TimeUnit.SECONDS) //NOTE: fluent wait has custom check types
				.pollingEvery(750, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
		WebElement el = (WebElement)fwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("bobbert")));
		
		driver.findElement(By.name("userName")).sendKeys("bobbert");
		/*driver.findElement(By.name("password")).sendKeys("bobbert");
		driver.findElement(By.name("login")).click();*/
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("bobbert");
		driver.findElement(By.name("login")).click();
		Assert.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
		
		// This grabs a collection of elements (With elements think: "things I can interact with")
		// Then I click the second one of the collection, which I know will be the second
		// radio button.
		driver.findElements(By.name("tripType")).get(1).click();
		driver.findElement(By.xpath("//input[@value='roundtrip']")).click();	
		driver.findElement(By.name("findFlights")).click();
		List<WebElement> els = driver.findElements(By.name("outFlight"));
		for(WebElement e : els) {
			e.click();
		}
		els = driver.findElements(By.name("inFlight"));
		for(WebElement e : els) {
			e.click();
		}
		driver.findElement(By.name("reserveFlights")).click();
		els = driver.findElements(By.xpath("//select[@name='cc_exp_dt_yr']/option"));
		for(WebElement e : els) {
			e.click();
			
			if(ExpectedConditions.alertIsPresent().apply(driver)!=null) {
				driver.switchTo().alert().accept();
			}
		}
	}
	
}
