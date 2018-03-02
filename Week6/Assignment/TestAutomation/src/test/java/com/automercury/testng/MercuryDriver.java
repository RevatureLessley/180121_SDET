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

import com.automercury.pages.MercuryFlightFinder;
import com.automercury.pages.MercuryLogin;

public class MercuryDriver {
	public static WebDriver driver;
	public final String url = "http://newtours.demoaut.com/";
	MercuryLogin mercuryLoginPg;
	MercuryFlightFinder mercuryFFPg;

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
	
	@Test(dependsOnMethods = "confirmHomepage", dataProvider = "loginData")
	public void loginToMercury(String inUsername, String inPassword) {
		driver.findElement(By.xpath("//a[text()='Home']")).click();
		mercuryLoginPg = new MercuryLogin(driver);
		mercuryLoginPg.login(inUsername, inPassword);
		AssertJUnit.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
	}
	
	@Test(dependsOnMethods = "loginToMercury")
	public void flightDetailsAndPrefs() {
		mercuryFFPg = new MercuryFlightFinder(driver);
		mercuryFFPg.selectFlightDetails(1, 2, 3, 4, 5, 6, 1, 1);
		AssertJUnit.assertEquals(driver.getTitle(), "Select a Flight: Mercury Tours");
	}
	
	@Test
	public void selectDepartAndReturn() {
		
	}
	
	@Test
	public void bookFlight() {
		
	}
	
	@Test
	public void logOut() {
		
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
	
	@DataProvider(name="loginData")
	public Object[][] provideFlightDetails() throws IOException{
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
}
