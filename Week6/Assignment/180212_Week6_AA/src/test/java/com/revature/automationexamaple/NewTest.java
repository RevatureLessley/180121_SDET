package com.revature.automationexamaple;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

	@Test(priority = 1, dependsOnMethods = { "confirmHomePage" })
	/****/
	public void mercuryLogin(String username, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		Assert.assertEquals(driver.getTitle(), findTitle);

	}

	@Test(priority = 2, dependsOnMethods = { "mercuryLogin" })
	public void fillFind(int tripType, Integer pass, String fromPort, String fromMonth, Integer fromDay, String toPort,
			String toMonth, Integer toDay, int sclass, String air) {

		FindFlightsPage findFlightsPage = new FindFlightsPage(driver);
		findFlightsPage.find(tripType, pass, fromPort, fromMonth, fromDay, toPort, toMonth, toDay, sclass, air);
		Assert.assertEquals(driver.getTitle(), selectTitle);
	}

	@Test(priority = 3, dependsOnMethods = { "fillFind" })
	public void fillSelect(int out, int in) {
		SelectFlightsPage selectFlightsPage = new SelectFlightsPage(driver);
		selectFlightsPage.select(out, in);
		Assert.assertEquals(driver.getTitle(), bookTitle);
	}

	@Test(priority = 4, dependsOnMethods = {"fillSelect"})
	public void fillBook() {
		
		 Assert.assertEquals(driver.getTitle(), confirmationTitle);
	}
	
	// dataProvider="AAData"

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
			data = new Object[rowcount][4];

			for (int i = 1; i <= rowcount; i++) {
				// Start at row 1, since header row is index[0]
				Row row = sheet.getRow(i);
				data[i - 1] = new Object[] { row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
						row.getCell(2).getNumericCellValue(), row.getCell(3).getNumericCellValue() };
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

}
