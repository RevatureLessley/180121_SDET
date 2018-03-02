package com.revature.testng;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.pages.MercuryFlightFinder;
import com.revature.pages.MercuryLogin;

public class MercuryDriverNewAndImproved {
	public static WebDriver driver;
	public final String url = "http://newtours.demoaut.com/";
	public MercuryLogin LoginPage;
	public MercuryFlightFinder flightPage;

	@BeforeTest(groups={"setup"})
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		//Can configure implicit wait as sson as it is instantiated
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get(url);
	}

	@AfterTest(groups={"teardown"})
	public void teardown() throws InterruptedException {
		Thread.sleep(2500);
		driver.quit();
	}

	@Test(dependsOnMethods = "confirmHomepage",groups={"something","everything"})
	public void logInToMercury() {
		LoginPage = new MercuryLogin(driver);
		LoginPage.driverLogInToMercury("bobbert", "bobbert");
		AssertJUnit.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
	}

	@Test(dependsOnMethods = { "confirmHomepage", "logInToMercury" },groups={"something","everything"})
	public void findFlights() throws InterruptedException {
		flightPage = new MercuryFlightFinder(driver);
		flightPage.selectOneWay();
		flightPage.selectFirstClass();
		Thread.sleep(1000);
		flightPage.submitFindFlight();
		AssertJUnit.assertEquals(driver.getTitle(), "Select a Flight: Mercury Tours");
	}

	@Test(priority = 0, groups={"something","everything"})
	public void confirmHomepage() {
		AssertJUnit.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
	}
	
	@Test(priority = 8, dataProvider="mercuryData", groups="everything")
	public void doEverything(String username, String password, double tripType, double classType){
		driver.findElement(By.xpath("//a[text()='Home']")).click();
		
		AssertJUnit.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
		LoginPage = new MercuryLogin(driver);
		LoginPage.driverLogInToMercury(username, password);
		AssertJUnit.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
		flightPage = new MercuryFlightFinder(driver);
		flightPage.selectTripType((int)tripType);
		flightPage.selectClassType((int)classType);
		flightPage.submitFindFlight();
		AssertJUnit.assertEquals(driver.getTitle(), "Select a Flight: Mercury Tours");
	}

	/*
	 * You can define test orders by setting a test 'priority' where lower
	 * priorities run first, or utilize the 'dependsOnMethods' attribute to
	 * define order as well.
	 */

	@DataProvider(name="mercuryData")
	public Object[][] provideAccountDetailsDynamic() throws Exception{
		Object[][] data = null;
		File file = new File("src/test/resources/MercuryData.xlsx");
		FileInputStream fis = new FileInputStream(file);	
		
		try(Workbook workbook = new XSSFWorkbook(fis)){
			
		Sheet sheet = workbook.getSheet("Sheet1");
		
		//grab count of all records, minus the header record
		int rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		data = new Object[rowcount][4];
		
		for(int i = 1; i <= rowcount; i++){
			//Start at row 1, since header row is index[0]
			Row row = sheet.getRow(i);
			data[i-1] = new Object[]{
					row.getCell(0).getStringCellValue(),
					row.getCell(1).getStringCellValue(),
					row.getCell(2).getNumericCellValue(),
					row.getCell(3).getNumericCellValue()
			};
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return data;
	}
}
