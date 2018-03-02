package com.revature.automationexamaple;

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
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.pages.BookFlightsPage;
import com.revature.pages.FindFlightsPage;
import com.revature.pages.LoginPage;
import com.revature.pages.SelectFlightsPage;

public class NewTest {
	public static WebDriver driver;

	public final String homepage = "http://newtours.demoaut.com/";
	public final String homeTitle = "Welcome: Mercury Tours";

	public final String findpage = "http://newtours.demoaut.com/mercuryreservation.php";
	public final String findTitle = "Find a Flight: Mercury Tours:";

	public final String selectpage = "http://newtours.demoaut.com/mercuryreservation2.php";
	public final String selectTitle = "Select a Flight: Mercury Tours";

	public final String bookpage = "http://newtours.demoaut.com/mercurypurchase.php";
	public static String bookTitle = "Book a Flight: Mercury Tours";

	public final String confirmationpage = "http://newtours.demoaut.com/mercurypurchase2.php";
	public static String confirmationTitle = "Flight Confirmation: Mercury Tours";

	@Test(priority = 0)
	/** Smoke test for confirming the home page exists **/
	public void confirmHomePage() {
		Assert.assertEquals(driver.getTitle(), homeTitle);
	}

/*	@Test(priority = 1, dependsOnMethods = { "confirmHomePage" })
	*//****//*
	public void mercuryLogin(String username, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		Assert.assertEquals(driver.getTitle(), findTitle);

	}*/

/*	@Test(priority = 2, dependsOnMethods = { "mercuryLogin" })
	public void fillFind(int tripType, Integer pass, String fromPort, String fromMonth, Integer fromDay, String toPort,
			String toMonth, Integer toDay, int sclass, String air) {

		FindFlightsPage findFlightsPage = new FindFlightsPage(driver);
		findFlightsPage.find(tripType, pass, fromPort, fromMonth, fromDay, toPort, toMonth, toDay, sclass, air);
		Assert.assertEquals(driver.getTitle(), selectTitle);
	}*/

/*	@Test(priority = 3, dependsOnMethods = { "fillFind" })
	public void fillSelect(int out, int in) {
		SelectFlightsPage selectFlightsPage = new SelectFlightsPage(driver);
		selectFlightsPage.select(out, in);
		Assert.assertEquals(driver.getTitle(), bookTitle);
	}*/

/*	@Test(priority = 4, dependsOnMethods = {"fillSelect"})
	public void fillBook(int totalPass, String firstName1, String firstName2, String firstName3, String firstName4,
			String lastName1, String lastName2, String lastName3, String lastName4, String cardType, 
			Integer cardNumber, Integer cardMonth, Integer cardYear, String cardFirstname, String cardMiddleName, 
			String cardLastName, String bilAddress, String bilCity, String bilState, Integer bilZip, String bilCountry,
			String delAddress, String delCity, String delState, Integer delZip, String delCountry) {
		
		BookFlightsPage bookFlightsPage = new BookFlightsPage(driver);
		bookFlightsPage.book(totalPass, firstName1, firstName2, firstName3, firstName4, lastName1, lastName2, 
				lastName3, lastName4, cardType, cardNumber, cardMonth, cardYear, cardFirstname, cardMiddleName, 
				cardLastName, bilAddress, bilCity, bilState, bilZip, bilCountry, delAddress, 
				delCity, delState, delZip, delCountry);
		 Assert.assertEquals(driver.getTitle(), confirmationTitle);
	}*/
	
	@Test(priority = 1, dataProvider="AAData")
	public void doEverything(String username, String password, double tripType, double pass, String fromPort, String fromMonth, 
			double fromDay, String toPort, String toMonth, double toDay, double sclass, String air, double out, double in, double totalPass, 
			String firstName1, String firstName2, String firstName3, String firstName4, String lastName1, String lastName2, 
			String lastName3, String lastName4, String cardType, double cardNumber, double cardMonth, double cardYear, 
			String cardFirstname, String cardMiddleName, String cardLastName, String bilAddress, String bilCity, 
			String bilState, double bilZip, String bilCountry, String delAddress, String delCity, String delState, 
			double delZip, String delCountry ){
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		Assert.assertEquals(driver.getTitle(), findTitle);
		
		FindFlightsPage findFlightsPage = new FindFlightsPage(driver);
		findFlightsPage.find((int)tripType, (int)pass, fromPort, fromMonth, (int)fromDay, toPort, toMonth, (int)toDay, (int)sclass, air);
		Assert.assertEquals(driver.getTitle(), selectTitle);
		
		SelectFlightsPage selectFlightsPage = new SelectFlightsPage(driver);
		selectFlightsPage.select((int)out, (int)in);
		Assert.assertEquals(driver.getTitle(), bookTitle);
		
		BookFlightsPage bookFlightsPage = new BookFlightsPage(driver);
		bookFlightsPage.book((int)totalPass, firstName1, firstName2, firstName3, firstName4, lastName1, lastName2, 
				lastName3, lastName4, cardType, (int)cardNumber, (int)cardMonth,(int) cardYear, cardFirstname, cardMiddleName, 
				cardLastName, bilAddress, bilCity, bilState, (int)bilZip, bilCountry, delAddress, 
				delCity, delState, (int)delZip, delCountry);
		 Assert.assertEquals(driver.getTitle(), confirmationTitle);
	}
	@BeforeTest
	/**
	 * Before any test, set property of wedbriver to the driver located in out
	 * drivers folder, as well as instantiated a new ChromeDriver() instance, open
	 * up the browser and the go to the provided url.
	 **/
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(homepage);
	}

	@AfterTest
	/**
	 * After all test have been completed, pause for 2 seconds, and then close the
	 * driver, close the browser
	 **/
	public void teardown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

	@DataProvider(name = "AAData")
	public Object[][] provideAccountDetailsDynamic() throws Exception {
		Object[][] data = null;
		File file = new File("src/test/resources/AAData.xlsx");
		FileInputStream fis = new FileInputStream(file);

		try (Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet("Sheet1");

			// grab count of all records, minus the header record
			int rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			data = new Object[rowcount][40];

			for (int i = 1; i <= rowcount; i++) {
				// Start at row 1, since header row is index[0]
				Row row = sheet.getRow(i);
				data[i - 1] = new Object[] { 
						row.getCell(0).getStringCellValue(),  	// A
						row.getCell(1).getStringCellValue(),	// B
						row.getCell(2).getNumericCellValue(), 	// C
						row.getCell(3).getNumericCellValue(),	// D
						row.getCell(4).getStringCellValue(),	// E
						row.getCell(5).getStringCellValue(),	// F
						row.getCell(6).getNumericCellValue(),	// G
						row.getCell(7).getStringCellValue(),	// H
						row.getCell(8).getStringCellValue(),	// I
						row.getCell(9).getNumericCellValue(),	// J
						row.getCell(10).getStringCellValue(),	// K
						row.getCell(11).getStringCellValue(),	// L
						row.getCell(12).getNumericCellValue(),	// M
						row.getCell(13).getNumericCellValue(),	// N
						row.getCell(14).getNumericCellValue(),	// O
						row.getCell(15).getStringCellValue(),	// P
						row.getCell(16).getStringCellValue(),	// Q
						row.getCell(17).getStringCellValue(),	// R
						row.getCell(18).getStringCellValue(),	// S
						row.getCell(19).getStringCellValue(),	// T
						row.getCell(20).getStringCellValue(),	// U
						row.getCell(21).getStringCellValue(),	// V
						row.getCell(22).getStringCellValue(),	// W
						row.getCell(23).getStringCellValue(),	// X
						row.getCell(24).getNumericCellValue(),	// Y
						row.getCell(25).getNumericCellValue(),	// Z
						row.getCell(26).getNumericCellValue(),	// AA
						row.getCell(27).getStringCellValue(),	// AB
						row.getCell(28).getStringCellValue(),	// AC
						row.getCell(29).getStringCellValue(),	// AD
						row.getCell(30).getStringCellValue(),	// AE
						row.getCell(31).getStringCellValue(),	// AF
						row.getCell(32).getStringCellValue(),	// AG
						row.getCell(33).getNumericCellValue(),	// AH
						row.getCell(34).getStringCellValue(),	// AI
						row.getCell(35).getStringCellValue(),	// AJ
						row.getCell(36).getStringCellValue(),	// AK
						row.getCell(37).getStringCellValue(),	// AL
						row.getCell(38).getNumericCellValue(),	// AM
						row.getCell(39).getStringCellValue()	// AN
					};
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

}
