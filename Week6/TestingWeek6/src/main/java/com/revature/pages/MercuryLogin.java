package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MercuryLogin {
	//First you need a variable to place your driver in
	private WebDriver driver;
	
	//Next you need your object repository objects
	private By username = By.xpath("//input[@name='userName']");
	private By password = By.xpath("//input[@name='password']");
	private By login = By.xpath("//input[@name='login']");
	
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
