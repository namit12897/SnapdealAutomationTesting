package com.snapdeal.scripts;

import javax.mail.MessagingException;

import org.testng.annotations.Test;

import com.snapdeal.generics.BaseTest;
import com.snapdeal.pages.HomePages;

public class TestScript1 extends BaseTest {
	@Test
	public void allotment() throws InterruptedException, MessagingException{		
		driver.get(URL);
		HomePages hp =new HomePages(driver);
		if(hp.checkSearchBoxIsDisplayed()) {
			System.out.println("Search text box is displayed.");
			hp.setSearchBoxValue(SEARCH_TEXT);
			hp.clickOnSearchButton();
			Thread.sleep(10000);
		}else {
			System.out.println("Search text box is not displayed.");
		}
	}
}