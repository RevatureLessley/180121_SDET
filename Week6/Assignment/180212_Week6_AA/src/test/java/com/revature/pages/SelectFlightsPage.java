package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectFlightsPage {
	private WebDriver driver;

	public SelectFlightsPage(WebDriver driver) {
		this.driver = driver;
	}

	public void inputOutFlight(int out) {
		driver.findElements(By.name("outFlight")).get(out).click();
	}

	public void inputInFlight(int in) {
		driver.findElements(By.name("inFlight")).get(in).click();
	}

	public void clickContinue() {
		driver.findElement(By.name("reserveFlights")).click();
	}

	public void select(int out, int in) {
		inputOutFlight(out);
		inputInFlight(in);
		clickContinue();
	}
}
