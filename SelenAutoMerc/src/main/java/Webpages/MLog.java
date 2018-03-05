package Webpages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MLog {
	@FindBy(xpath="//input[@name='userName']")
	WebElement username;
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	@FindBy(xpath="//input[@name='login']")
	WebElement signOnBtn;
	
	public MLog(WebDriver drive) {
		PageFactory.initElements(drive, this);
	}
	
	public void enterUsername(String un) {
		username.sendKeys(un);
	}
	
	public void enterPassword(String pw) {
		password.sendKeys(pw);
	}
	
	public void clickSignOn() {
		signOnBtn.click();
	}
	
	public void login(String un, String pw) {
		enterUsername(un);
		enterPassword(pw);
		clickSignOn();
	}
	
}