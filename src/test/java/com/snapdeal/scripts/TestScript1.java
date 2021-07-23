package com.snapdeal.scripts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.mail.MessagingException;
import org.apache.commons.lang3.StringUtils;
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
			SortByDropDown.click();
			WebElement PriceLowToHigh = driver.findElement(By.xpath("//*[@id=\"content_wrapper\"]/div[7]/div[5]/div[3]/div[1]/div/div[2]/ul/li[3]"));
			PriceLowToHigh.click();
			String price = null;
			Thread.sleep(1000);
			List<WebElement> AllProductName = driver.findElements(By.xpath("//p[@class='product-title ']"));
			System.out.println("Total Number of Products: "+AllProductName.size());
			List<WebElement> AllProductPrice = driver.findElements(By.xpath("//span[@class='lfloat product-price']")); 
			System.out.println("Total Number of Price: "+AllProductPrice.size());
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
		    driver.switchTo().window(tabs2.get(0));
		    driver.close();
		    driver.switchTo().window(tabs2.get(1));
		    String ProductName = driver.findElement(By.xpath("//*[@id=\"productOverview\"]/div[2]/div/div[1]/div[1]/div[1]/h1")).getText();
		    String ProductPrice = driver.findElement(By.xpath("//*[@id=\"buyPriceBox\"]/div[2]/div[1]/div[1]/div[1]/span[1]/span")).getText();
		    WebElement AddToCart = driver.findElement(By.xpath("//span[text()='add to cart']"));
		    AddToCart.click();
		    
		    String ProductNameInCart = driver.findElement(By.xpath("//*[@id=\"cartProductContainer\"]/div/div[2]/div[1]/div/div[2]/span[1]/a")).getText();
		    String ProductPriceInCartWithDigits = driver.findElement(By.xpath("//*[@id=\"cartProductContainer\"]/div/div[2]/div[2]/div/div[1]/div[2]/span")).getText();
			String ProductPriceInCart = ProductPriceInCartWithDigits.replaceAll("[^0-9]", "");
			System.out.println(ProductPriceInCart);
			if(ProductNameInCart.equals(ProductName)) {
		    	if(ProductPriceInCart.equals(ProductPrice)) {
		    		System.out.println("Product Price is same as it was selected.");
		    	}else {
		    		System.out.println("Product Price has is difference.");
			    	
		    		
//		    		StringUtils.difference(ProductPriceInCart,ProductPrice);
		    		
		    		
		    	}
		    }else {
		    	System.out.println("Different Product has been added to cart. Product selected was: "+ProductName+" and Product displaying was: "+ProductNameInCart);
		    }
		}else {
			System.out.println("Search text box is not displayed.");
		}
		Thread.sleep(5000);
		driver.close();
	}
}