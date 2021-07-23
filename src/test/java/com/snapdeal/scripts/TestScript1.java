package com.snapdeal.scripts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestScript1{
	static{
		System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver","./driver/geckodriver.exe");
	}
	public static WebDriver driver;
	public static String sBrowser = "chrome";
	public static void main(String [] args) throws InterruptedException, MessagingException {
		test();
	}
	public static void test() throws InterruptedException, MessagingException{	
		if(sBrowser.equals("chrome")){
			driver = new ChromeDriver();
		}
		else if(sBrowser.equals("firefox")){
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.snapdeal.com/");
		WebElement SearchBox = driver.findElement(By.xpath("//input[@placeholder='Search products & brands']"));
		if(SearchBox.isDisplayed()) {
			System.out.println("Search text box is displayed.");
			SearchBox.sendKeys("Samsung android phone");	
			WebElement SearchButton = driver.findElement(By.xpath("//span[@class='searchTextSpan']"));
			SearchButton.click();
			WebElement SortByDropDown = driver.findElement(By.xpath("//i[@class='sd-icon sd-icon-expand-arrow sort-arrow']"));
//			if(SortByDropDown.isDisplayed()) {
				SortByDropDown.click();
//			}
			WebElement PriceLowToHigh = driver.findElement(By.xpath("//*[@id=\"content_wrapper\"]/div[7]/div[5]/div[3]/div[1]/div/div[2]/ul/li[3]"));
//			if(PriceLowToHigh.isDisplayed()) {
				PriceLowToHigh.click();
//			}
			String price;
//			List<WebElement> AllProductImageList = driver.findElements(By.xpath("//p[@class='product-title ']/../../../../div[@class='product-tuple-image ']"));
//			System.out.println("Total Number of Products Image List: "+AllProductImageList.size());
			List<WebElement> AllProductName = driver.findElements(By.xpath("//p[@class='product-title ']"));
			System.out.println("Total Number of Products: "+AllProductName.size());
			List<WebElement> AllProductPrice = driver.findElements(By.xpath("//span[@class='lfloat product-price']")); 
			System.out.println("Total Number of Price: "+AllProductPrice.size());
//			for (int j = 1; j < AllProductPrice.size(); j++) {
//				price = AllProductPrice.get(j).getText();
//				System.out.println(price);
//				}
			for(int i=0;i<AllProductName.size();i++) {
				System.out.println(AllProductName.get(i).getText());
			 }
			for(int i=0;i<AllProductName.size();i++) {
				if(AllProductName.get(i).getText().contains("Samsung")) {
					AllProductName.get(i).click();
					price = AllProductPrice.get(i).getText();
					System.out.println(price);
				}
			 }
		    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs2.get(1));
		    driver.close();
		    driver.switchTo().window(tabs2.get(0));
		}else {
			System.out.println("Search text box is not displayed.");
		}
		Thread.sleep(5000);
		driver.close();
	}
}