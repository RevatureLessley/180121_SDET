package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MercuryLogin {
	
	private WebDriver driver;
	
	private By username = By.xpath("//INPUT[@type='text']");
	private By password = By.xpath("//INPUT[@type='password']");
	private By login = By.xpath("//INPUT[@type='image']");
	
	public MercuryLogin(WebDriver driver) {
		this.driver = driver;
	}

	public void inputUsername(String username) {
		driver.findElement(this.username).sendKeys(username);
	}
	
	public void inputPassword(String password) {
		driver.findElement(this.password).sendKeys(password);
	}
	
	public void submitLoginCredentials() {
		driver.findElement(this.login).click();
	}
	
	public void driverLogInToMercury(String username, String password) {
		inputUsername(username);
		inputPassword(password);
		submitLoginCredentials();
	}
	
}
