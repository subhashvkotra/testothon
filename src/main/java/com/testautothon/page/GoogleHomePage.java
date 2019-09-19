package com.testautothon.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleHomePage extends BasePageWeb{

	public GoogleHomePage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(name ="q")
	private WebElement searchBox;
	
	@FindBy(xpath=".//div[text()='GE | Imagination at Work']")
	private WebElement searchReturnLink;
	

	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSearchReturnLink() {
		return searchReturnLink;
	}
	
}
