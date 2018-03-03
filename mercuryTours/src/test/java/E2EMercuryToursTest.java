import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

/**
 * e2e test for the mercury tour from registering as new user to the
 * confirmation page using TestNG
 * 
 * @author kamel
 *
 */
public class E2EMercuryToursTest {
	public static WebDriver driver;
	public final String url = "http://newtours.demoaut.com/";
	String newUser="hobert";
	String newPW="hobby";

	@Test
	public void confirmHomepage() {
		Assert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
	}

	@Test(dependsOnMethods = "confirmHomepage")
	public void registerNewUser() {
		driver.findElement(By.linkText("REGISTER")).click();
		driver.findElement(By.xpath("//input[@id=\"email\"]")).sendKeys(newUser);
    	driver.findElement(By.xpath("//tbody/tr[5]/td/form/table/tbody/tr[15]/td[2]/input[@name=\"password\"]")).sendKeys(newPW);
    	driver.findElement(By.xpath("//form/table/tbody/tr[16]/td[2]/input[@name=\"confirmPassword\"]")).sendKeys(newPW);
        driver.findElement(By.xpath("//form/table/tbody/tr[18]/td/input[@name=\"register\"]")).click();
	    Assert.assertEquals(driver.getTitle(), "Register: Mercury Tours");
	   
	}
    
	
	@Test(dependsOnMethods = "registerNewUser")
    public void logIn() {
	    driver.findElement(By.xpath("//html/body/div/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[1]/a")).click();	
		driver.findElement(By.xpath("//input[@name=\"userName\"]")).sendKeys(newUser);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(newPW);
		driver.findElement(By.name("login")).click();
		Assert.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
	}
	
	@Test(dependsOnMethods = "logIn")
	public void bookFlight() {
		driver.findElements(By.name("tripType")).get(1).click();
		driver.findElement(By.xpath("//input[@value='roundtrip']")).click();
		driver.findElement(By.name("findFlights")).click();
		
		List<WebElement> els = driver.findElements(By.name("outFlight"));
		for(WebElement e : els){
			e.click();
		}
		els = driver.findElements(By.name("inFlight"));
		for(WebElement e : els){
			e.click();
		}
		driver.findElement(By.name("reserveFlights")).click();
		els = driver.findElements(By.xpath("//select[@name='cc_exp_dt_yr']/option"));
		for(WebElement e: els){
			e.click();
			if(ExpectedConditions.alertIsPresent().apply(driver)!=null){
				driver.switchTo().alert().accept();
			}
		}
	Assert.assertEquals(driver.getTitle(), "Book a Flight: Mercury Tours");
	}
	@Test(dependsOnMethods = "bookFlight")
	public void bayFlight() {
		driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[22]/td/input")).click();
		
	}
	
	@AfterMethod
	public void AfterMethod() throws InterruptedException {
		Thread.sleep(2000);
	}
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}

	@AfterTest
	public void afterTest() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

	

}
