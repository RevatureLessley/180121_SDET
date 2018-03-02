package com.automercury.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MercuryLogin {
	@FindBy(xpath="//input[@name='userName']")
	WebElement username;
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	@FindBy(xpath="//input[@name='login']")
	WebElement signOnBtn;
	
	public MercuryLogin(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String inUsername) {
		username.sendKeys(inUsername);
	}
	
	public void enterPassword(String inPassword) {
		password.sendKeys(inPassword);
	}
	
	public void clickSignOn() {
		signOnBtn.click();
	}
	
	public void login(String inUsername, String inPassword) {
		enterUsername(inUsername);
		enterPassword(inPassword);
		clickSignOn();
	}
	
}
