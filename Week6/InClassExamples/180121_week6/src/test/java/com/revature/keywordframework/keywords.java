package com.revature.keywordframework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.DataTable;

public class keywords {
	
	public static void performAction(WebDriver driver, DataTable table) throws InterruptedException{
		List<List<String>> data = table.raw(); 
		
		for(List<String> string : data){
			if(string.get(2).equals("text")){
				driver.findElement(By.name(string.get(0))).sendKeys(string.get(1));
			}else if(string.get(2).equals("click")){
				driver.findElement(By.name(string.get(0))).click();
			}else if(string.get(2).equals("weblist")){
				
				//xpath = //select[@name='fromPort']/option[@value='Frankfurt']
				
				driver.findElement(By.xpath(
						"//select[@name='" + string.get(0) + "']/option[@value='" 
				+ string.get(1) + "']"
						)).click();
				
			}
		}
	}
	
}
