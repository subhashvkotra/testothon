package com.testautothon.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.google.gson.JsonObject;
import com.testautothon.utils.WriteJSON;
 /**
  * 
  * @author Harish Rokkam
  *
  */
public class YouMayAlsoLikePage extends BasePageWeb{
	
	public YouMayAlsoLikePage(WebDriver driver) {
		super(driver);
	}

	//Local variables
	String[] aYmalName = new String[3];
	String[] aYmalHandler = new String[3];
	String[] aYmalFollower = new String[3];
	String[] aYmalFollowing = new String[3];
	
	// Xpath for you may also like page
	String sYmal = ".//div[@class='RelatedUsers-users']/div[%o]//a";
	String sYmalName = ".//div[@class='RelatedUsers-users']/div[%o]//a//strong[@class='fullname']";
    String sYmalHandler = ".//div[@class='RelatedUsers-users']/div[%o]//a//span[@class=\"username u-dir u-textTruncate\"]"; 
    String sYmalFollow = ".//div[@data-component-context='profile_hover']//span[text()='%s']/following-sibling::span[@class='ProfileCardStats-statValue']";
    String sYmalLogin = ".//span[text()=' Log in']";
    String sYmalTitle = ".//h1[@class='ProfileHeaderCard-name']/a";
    
    /**
     * 
     * @return
     */
    public WebElement ymalLoginEle()
    {
    	return driver.findElement(By.xpath(String.format(sYmalLogin)));
    }
    
    /**
     * 
     * @return
     */
    public WebElement ymalTitleEle()
    {
    	return driver.findElement(By.xpath(String.format(sYmalTitle)));
    }
    
    /**
     * 
     * @param position
     * @return
     */
    public WebElement ymalEle(int position)
    {
    	return driver.findElement(By.xpath(String.format(sYmal, position)));
    }

    /**
     * 
     * @param position
     * @return
     */
    protected WebElement ymalNameEle(int position)
    {
    	return driver.findElement(By.xpath(String.format(sYmalName, position)));
    }

    /**
     * 
     * @param position
     * @return
     */
    protected WebElement ymalHandlerEle(int position)
    {
    	return driver.findElement(By.xpath(String.format(sYmalHandler, position)));
    }

    /**
     * 
     * @param value
     * @return
     */
    protected WebElement ymalFollowingEle()
    {
    	return driver.findElement(By.xpath(String.format(sYmalFollow, "Following")));
    }

    /**
     * 
     * @param value
     * @return
     */
    protected WebElement ymalFollowerEle()
    {
    	return driver.findElement(By.xpath(String.format(sYmalFollow, "Followers")));
    }
    
    /**
     * 
     * @return
     */
    public String ymalName(int position)
    {
    	return ymalNameEle(position).getText();
    }

    /**
     * 
     * @return
     */
    public String ymalHandler(int position)
    {
    	return ymalHandlerEle(position).getText();
    }

    /**
     * 
     * @return
     */
    public String ymalFollowing()
    {
    	return ymalFollowingEle().getText();
    }

    /**
     * 
     * @return
     */
    public String ymalFollower()
    {
    	return ymalFollowerEle().getText();    	
    }
    
    /**
     * 
     * @param position
     */
    public void mouseOver(int position){
    	Actions action = new Actions(driver);
    	action.moveToElement(ymalEle(position)).build().perform();
    }
    
    /**
     * 
     * @return
     */
    public int ymalEleCount()
    {
    	return driver.findElements(By.xpath(".//div[@class='RelatedUsers-users']/div")).size();
    }
    
    /**
     * 
     * @return
     */
    public String ymalTitle()
    {
    	return ymalTitleEle().getText();
    }
    
    /***
     * 
     */
    public void login()
    {
    	ymalLoginEle().click();
    }
    
    public void getYMALList(int listCount)
    {
    	int iYmalCount = ymalEleCount();
		int loop = 0;
		if(iYmalCount<=3)
			loop = iYmalCount;
		else
			loop = 3;
		for(int i=0; i<loop; i++)
		{
			aYmalName[i] = ymalName(i+1);
			aYmalHandler[i] = ymalHandler(i+1);
			mouseOver(i+1);
			sleepFor(2);
			aYmalFollowing[i] = ymalFollowing();
			aYmalFollower[i] = ymalFollower();
		}
    }
    
    /**
     * 
     */
    public void prepareJSON()
    {
    	WriteJSON objWriteJSON = new WriteJSON();
    	for(int i=0;i<aYmalName.length;i++)
    		objWriteJSON.prepareBiographiesJSON(aYmalName[i],aYmalHandler[i],Integer.parseInt(aYmalFollowing[i].replace(",", "")),Integer.parseInt(aYmalFollower[i].replace(",", "")));
    }
}
