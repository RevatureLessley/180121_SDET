package com.revature.testng;

import java.io.File;
import java.io.FileInputStream;

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

public class MercuryDriverAbstract {
	public static WebDriver driver;
	public final String url = "http://newtours.demoaut.com/";
	public MercuryLogin ml;
	public MercuryFlightFinder ff;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}

	@AfterTest
	public void teardown() throws InterruptedException {
		Thread.sleep(2500);
		driver.quit();
	}
	
	@Test(priority=0)
	public void confirmHomepage() {
		Assert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
	}
	@Test(dependsOnMethods="confirmHomepage")
	public void logInToMercury() {
		ml  = new MercuryLogin(driver);
		ml.driverLogInToMercury("bobbert", "bobbert");
		Assert.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
	}
	
	@Test(dependsOnMethods="logInToMercury")
	public void findFlights() throws InterruptedException {
		ff = new MercuryFlightFinder(driver);
		ff.selectFirstClass();
		ff.selectOneWay();
		Thread.sleep(2500);
		ff.submitFindFlight();
		Assert.assertEquals(driver.getTitle(), "Select a Flight: Mercury Tours");
	}
	
	@Test(priority = 8, dataProvider="mercuryData")
	public void doEverything(String username, String password, double tripType, double classType) throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Home']")).click();
		
		Assert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
		ml  = new MercuryLogin(driver);
		ml.driverLogInToMercury(username, password);
		Assert.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
		ff = new MercuryFlightFinder(driver);
		ff.selectTripType((int)tripType);
		ff.selectClassType((int)classType);
		ff.selectDepartFrom(2);
		ff.selectFirstClass();
		Thread.sleep(2500);
		ff.submitFindFlight();
		Assert.assertEquals(driver.getTitle(), "Select a Flight: Mercury Tours");
	}
	
	@DataProvider(name="mercuryData")
	public Object[][] provideAccountDetailsDynamic() throws Exception {
		Object[][] data = null;
		File file = new File("src/test/resources/MercuryData.xlsx");
		FileInputStream fis = new FileInputStream(file);
		
		try(Workbook workbook = new XSSFWorkbook(fis)) {
		
		
		Sheet sheet = workbook.getSheet("Sheet1");
		
		// grab count of all records, minus the header record
		int rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		data = new Object[rowcount][4];
		
		for(int i = 1; i <= rowcount; i++) {
			Row row = sheet.getRow(i);
			data[i-1] = new Object[] {
					row.getCell(0).getStringCellValue(),
					row.getCell(1).getStringCellValue(),
					row.getCell(2).getNumericCellValue(),
					row.getCell(3).getNumericCellValue()
			};
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
}
