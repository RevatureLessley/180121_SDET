package com.revature.testng.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookFlight {

	// Firstname Field
	@FindBy(xpath = "//input[@name='passFirst0']")
	WebElement inputFirstName;
	// Lastname Field
	@FindBy(xpath = "//input[@name='passLast0']")
	WebElement inputLastName;
	// Meal
	@FindBy(xpath = "//select[@name='pass.0.meal']/option")
	List<WebElement> mealOptions;

	// Submit button
	@FindBy(xpath = "//input[@name='buyFlights']")
	WebElement submit;

	public BookFlight(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void fillFirstName(String firstname) {
		inputFirstName.sendKeys(firstname);
	}

	public void fillLasttName(String lastname) {
		inputLastName.sendKeys(lastname);
	}
	
	public void selectMeal(int meal){
		mealOptions.get(meal).click();
	}

	public void submit() {
		submit.click();
	}
}
