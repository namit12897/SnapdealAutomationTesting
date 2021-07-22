package com.snapdeal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePages {
	WebDriver driver;
	
	@FindBy(xpath="//input[@placeholder='Search products & brands']")
	private WebElement SearchBox;
	
	@FindBy(xpath="//span[@class='searchTextSpan']")
	private WebElement SearchButton;
	
	public HomePages(WebDriver driver){
		PageFactory.initElements(driver,this);
	}
	
	public boolean checkSearchBoxIsDisplayed(){
		return SearchBox.isDisplayed();
	}
	
	public void clickOnSearchBox(){
		SearchBox.click();
		SearchBox.clear();
	}
	
	public void setSearchBoxValue(String Value){
		SearchBox.sendKeys(Value);
	}
	
	public void clickOnSearchButton(){
		SearchButton.click();
	}
}