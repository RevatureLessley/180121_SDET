package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MercuryLogin {
	/*
	 * With test automation, there, of course, exists a design pattern you can 
	 * leverage.
	 * 
	 * Page Object Model
	 * -This design pattern centers around the idea of creating a class to represent
	 * every possible page for an application. This way you just reference the pages
	 * elements through its class.
	 * Typically the class should have a variable name for every interactable
	 * web element for that page. This transforms the class into a repository of
	 * objects that represent every element on the page. An object repository.
	 * 
	 */
	
	//First you need a variable to place your driver in.
	private WebDriver driver;
	
	//Next you need your object repository objects
	private By username = By.xpath("//input[@name='userName']");
	private By password = By.xpath("//input[@name='password']");
	private By login = By.xpath("//input[@name='login']");
	
	public MercuryLogin(WebDriver driver){
		this.driver = driver;
	}
	
	public void inputUsername(String username){
		driver.findElement(this.username).sendKeys(username);
	}
	
	public void inputpassword(String password){
		driver.findElement(this.password).sendKeys(password);
	}
	
	public void submitLoginCredentials(){
		driver.findElement(this.login).click();
	}
	
	public void driverLogInToMercury(String username, String password){
		inputUsername(username);
		inputpassword(password);
		submitLoginCredentials();
	}
}
