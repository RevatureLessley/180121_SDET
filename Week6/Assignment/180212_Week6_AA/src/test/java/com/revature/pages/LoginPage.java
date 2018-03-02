package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private  WebDriver driver;
	
	public LoginPage(WebDriver driver){this.driver = driver;}
	
	public void inputUsername(String username) {driver.findElement(By.name("userName")).sendKeys(username);}
	public void inputPassword(String password) {driver.findElement(By.name("password")).sendKeys(password);}
	public void clickLogin() {driver.findElement(By.name("login")).click();}	

	public void login(String username, String password) {
		inputUsername(username);
		inputPassword(password);
		clickLogin();
	}
}
