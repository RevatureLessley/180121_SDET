package PageFrameworks;

import java.io.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import Webpages.MLog;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class MerDriver {
	public static WebDriver drive;
	public final String url = "http://newtours.demoaut.com/";
	MLog mlog;
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		drive = new ChromeDriver();
		drive.get(url);
	}
	@AfterTest
	public void teardown() {
		drive.quit();
	}
	@DataProvider(name="login")
	public Object[][] showLoginDetails() throws IOException{
		Object[][] data = null;
		Workbook workbook = openWorkbook();
		
		Sheet sheet = workbook.getSheet("LoginPage");
		int count = sheet.getLastRowNum() - sheet.getFirstRowNum();
		data = new Object[count][2];
		
		for(int i = 1; i <= count; i++) {
			Row r = sheet.getRow(i);
			data[i-1] = new Object[] {
					r.getCell(0).getStringCellValue(),
					r.getCell(1).getStringCellValue()
			};
		}
		
		workbook.close();
		
		return data;
	}
	@Test(priority=0)
	public void confirmHomepage() {
		AssertJUnit.assertEquals(drive.getTitle(), "Welcome: Mercury Tours");
	}
	@Test(dependsOnMethods = "confirmHomepage", dataProvider = "login", enabled = false)
	public void loginToMercuryData(String un, String pw) {
		drive.findElement(By.xpath("//a[text()='Home']")).click();
		mlog = new MLog(drive);
		mlog.login(un, pw);
		AssertJUnit.assertEquals(drive.getTitle(), "Find a Flight: Mercury Tours:");
	}
	@Test(dependsOnMethods = "confirmHomepage")
	public void loginm() {
		mlog = new MLog(drive);
		mlog.login("bobbert", "bobbert");
		AssertJUnit.assertEquals(drive.getTitle(), "Find a Flight: Mercury Tours:");
	}
	public Workbook openWorkbook() {
		File file = new File("src/test/resources/MercuryData.xlsx");
		FileInputStream filest;
		Workbook workbook = null;
		
		try {
			filest = new FileInputStream(file);
			workbook = new XSSFWorkbook(filest);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException i) {
			i.printStackTrace();
		}
		
		return workbook;
	}
}
	

	
