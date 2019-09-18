package com.testautothon.page;

import org.openqa.selenium.By;

import com.testautothon.utils.Testautothon;

public class WebPageObjRepo extends Testautothon {
	
	public By searchBox = By.name("q");
	public By link = By.xpath(".//div[text()='GE | Imagination at Work']");

}
