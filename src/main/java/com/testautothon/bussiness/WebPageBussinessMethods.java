package com.testautothon.bussiness;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.testautothon.page.WebPageObjRepo;

public class WebPageBussinessMethods extends WebPageObjRepo {
	
	public void type(By locator, String value)
	{
		sedriver.findElement(locator).sendKeys(value);
	}
	
	public void type(By locator, Keys value)
	{
		sedriver.findElement(locator).sendKeys(value);
	}
	
	public void click(By locator)
	{
		sedriver.findElement(link).click();
	}

}
