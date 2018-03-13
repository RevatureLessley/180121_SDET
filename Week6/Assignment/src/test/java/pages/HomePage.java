package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	private WebDriver driver;
	
	private By username = By.xpath("//input[@name='userName']");
	private By password = By.xpath("//input[@name='password']");
//	private By login = By.xpath("//input[@name='login']");
	private By login = By.name("login");
	
	public HomePage(WebDriver driver){
		this.driver = driver;
	}
	
	public void inputUsername(String username){
		driver.findElement(this.username).sendKeys(username);
	}
	
	public void inputpassword(String password){
		driver.findElement(this.password).sendKeys(password);
	}
	
	public void clickSubmit(){
		driver.findElement(this.login).click();
	}
	
	public void driverLogInToMercury(String username, String password){
		inputUsername(username);
		inputpassword(password);
		clickSubmit();
	}
}
