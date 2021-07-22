package com.snapdeal.generics;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FWUtils{
	/**
	 * This Method Is Used To Verify The Page Title
	 * @param driver
	 * @param expectedTitle
	 */
	public static void verifyPageTitle(WebDriver driver,String expectedTitle){
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.titleContains(expectedTitle));
		String actualTitle = driver.getTitle();
		if(actualTitle.equals(expectedTitle)){
			System.out.println("The Expected Page is Dispalyed: "+expectedTitle);
		}
		else{
			System.out.println("The Expected Page is not Dispalyed: "+actualTitle);
		}
	}
	public static void take_Screen_Shoot(WebDriver driver,String path){
		try{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File(path);
		FileUtils.copyFile(src, des);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}