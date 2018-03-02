package com.automercury.testng;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automercury.pages.MercuryBookFlight;
import com.automercury.pages.MercuryFlight;
import com.automercury.pages.MercuryFlightFinder;
import com.automercury.pages.MercuryLogin;

public class MercuryDriver {
	public static WebDriver driver;
	public final String url = "http://newtours.demoaut.com/";
	MercuryLogin mercuryLoginPg;
	MercuryFlightFinder mercuryFFPg;
	MercuryFlight mercuryFlightPg;
	MercuryBookFlight mercuryBFPg;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

	@Test(priority=0)
	public void confirmHomepage() {
		AssertJUnit.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
	}
	
	@Test(dependsOnMethods = "confirmHomepage", dataProvider = "loginData", enabled = false)
	public void loginToMercuryData(String inUsername, String inPassword) {
		driver.findElement(By.xpath("//a[text()='Home']")).click();
		mercuryLoginPg = new MercuryLogin(driver);
		mercuryLoginPg.login(inUsername, inPassword);
		AssertJUnit.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
	}
	
	@Test(dependsOnMethods = "confirmHomepage")
	public void loginToMercury() {
		mercuryLoginPg = new MercuryLogin(driver);
		mercuryLoginPg.login("bobbert", "bobbert");
		AssertJUnit.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
	}
	
	@Test(dependsOnMethods = "loginToMercury", dataProvider = "flightData", enabled = false)
	public void flightDetailsAndPrefsData(int type, int amnt, int loc0, int month0, int loc1, int month1, int service, int line) {
		mercuryFFPg = new MercuryFlightFinder(driver);
		mercuryFFPg.selectFlightDetails(type, amnt, loc0, month0, loc1, month1, service, line);
		AssertJUnit.assertEquals(driver.getTitle(), "Select a Flight: Mercury Tours");
		driver.navigate().back();
	}
	
	@Test(dependsOnMethods = "loginToMercury")
	public void flightDetailsAndPrefs() {
		mercuryFFPg = new MercuryFlightFinder(driver);
		mercuryFFPg.selectFlightDetails(1, 2, 3, 4, 4, 6, 1, 2);
		AssertJUnit.assertEquals(driver.getTitle(), "Select a Flight: Mercury Tours");
	}
	
	@Test(dependsOnMethods = "flightDetailsAndPrefs")
	public void selectDepartAndReturn() {
		mercuryFlightPg = new MercuryFlight(driver);
		mercuryFlightPg.selectDepartReturnFlights(1, 2);
		AssertJUnit.assertEquals(driver.getTitle(), "Book a Flight: Mercury Tours");
	}
	
	@Test(dependsOnMethods = "selectDepartAndReturn")
	public void bookFlight() {
		mercuryBFPg = new MercuryBookFlight(driver);
		mercuryBFPg.bookAFlight(1, "473289479439", 3, 4, "Dylan", "L", "Eda", 1, "23B Hert Street", "", "Bronx", "NY", "07427", 5, 
				0, "24 Mat Street", "", "Queens", "NY", "08934", 5);
		AssertJUnit.assertEquals(driver.getTitle(), "Flight Confirmation: Mercury Tours");
	}
	
	@Test(dependsOnMethods = "bookFlight")
	public void logOut() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//img[@src='/images/forms/Logout.gif']")).click();
		AssertJUnit.assertEquals(driver.getTitle(), "Sign-on: Mercury Tours");
	}
	
	public Workbook openWorkbook() {
		File file = new File("src/test/resources/MercuryData.xlsx");
		FileInputStream fis;
		Workbook workbook = null;
		
		try {
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
		} catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return workbook;
	}
	
	@DataProvider(name="loginData")
	public Object[][] provideLoginDetails() throws IOException{
		Object[][] data = null;
		Workbook workbook = openWorkbook();
		
		Sheet sheet = workbook.getSheet("LoginPage");
		int rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		data = new Object[rowcount][2];
		
		for(int i = 1; i <= rowcount; i++) {
			Row row = sheet.getRow(i);
			data[i-1] = new Object[] {
					row.getCell(0).getStringCellValue(),
					row.getCell(1).getStringCellValue()
			};
		}
		
		workbook.close();
		
		return data;
	}
	
	@DataProvider(name="flightData")
	public Object[][] provideFlightDetails() throws IOException{
		Object[][] data = null;
		Workbook workbook = openWorkbook();
		
		Sheet sheet = workbook.getSheet("FlightFinder");
		int rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		data = new Object[rowcount][2];
		
		for(int i = 1; i <= rowcount; i++) {
			Row row = sheet.getRow(i);
			data[i-1] = new Object[] {
					(int)row.getCell(0).getNumericCellValue(),
					(int)row.getCell(1).getNumericCellValue(),
					(int)row.getCell(2).getNumericCellValue(),
					(int)row.getCell(3).getNumericCellValue(),
					(int)row.getCell(4).getNumericCellValue(),
					(int)row.getCell(5).getNumericCellValue(),
					(int)row.getCell(6).getNumericCellValue(),
					(int)row.getCell(7).getNumericCellValue()
			};
		}
		
		workbook.close();
		
		return data;
	}
	
	@DataProvider(name="flightTimeData")
	public Object[][] provideFlightTimes() throws IOException{
		Object[][] data = null;
		Workbook workbook = openWorkbook();
		
		Sheet sheet = workbook.getSheet("Flight");
		int rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		data = new Object[rowcount][2];
		
		for(int i = 1; i <= rowcount; i++) {
			Row row = sheet.getRow(i);
			data[i-1] = new Object[] {
					(int)row.getCell(0).getNumericCellValue(),
					(int)row.getCell(1).getNumericCellValue()
			};
		}
		
		workbook.close();
		
		return data;
	}
}
