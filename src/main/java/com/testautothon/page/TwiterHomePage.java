package com.testautothon.page;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.testautothon.helper.Javascript.JavaScriptHelper;

	
public class TwiterHomePage extends BasePageWeb {

	public TwiterHomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	

	@FindBy(xpath ="//div[@id='timeline']//ol[@id='stream-items-id']//li[@data-item-type='tweet']")
	private List<WebElement> getTweetTimelinePostCount;
	

	String sRetweetText = "(//div[@id='timeline']//ol[@id='stream-items-id']//li[@data-item-type='tweet'])[%o]//div[@class='ProfileTweet-action ProfileTweet-action--retweet js-toggleState js-toggleRt']//button[@class='ProfileTweet-actionButton  js-actionButton js-actionRetweet']//span[@class='ProfileTweet-actionCountForPresentation']";
	public WebElement getRetweetTextHandlerEle(int position)
	   {
	    return driver.findElement(By.xpath(String.format(sRetweetText, position)));
	   }

	String sLikeText = "(//div[@id='timeline']//ol[@id='stream-items-id']//li[@data-item-type='tweet'])[%o]//div[@class='ProfileTweet-action ProfileTweet-action--favorite js-toggleState']//button[@class='ProfileTweet-actionButton js-actionButton js-actionFavorite']//span[@class='ProfileTweet-actionCount']";
	public WebElement getLikeTextHandlerEle(int position) {
		return driver.findElement(By.xpath(String.format(sLikeText, position)));
	   }
	
	public List<WebElement> getPostCountList() {
		return getTweetTimelinePostCount;
	}
	
	
	
	
	//JavaScript scroll vertically
	public void scrollVertically(){		
				new JavaScriptHelper(driver).scrollDownVertically();	
	}
	
	//TimeLine Post count based on given Limit by scrolling	
	public int getTimeLineTwiterCount(int sLimitCount){
		
		int sFinalCount = 0;
		int temp = 0;
		for(int i = 0; ;i++){
			
			if(sLimitCount <=getSizeOfTimeLineTwitterPosts()){
				sFinalCount = getSizeOfTimeLineTwitterPosts(); 
				break;
			}
			else{
				scrollVertically();
				if(temp==getSizeOfTimeLineTwitterPosts())
					break;
				temp=getSizeOfTimeLineTwitterPosts();
			}
		}	
	
		return sFinalCount;
	}
	
	
	//Get getTwiterReTweetsHighestCount in Timeline Post Count
	public int getTwiterReTweetsHighestCount(int sLimitCount){
		
		int sTimeLinePostCount = getTimeLineTwiterCount(sLimitCount);
		if(sTimeLinePostCount>=sLimitCount){
			sTimeLinePostCount = sLimitCount;
		}
		int sHighestCount = 0;
		int sReTweetCount =0;
		
		for(int i =1; i<=sTimeLinePostCount;i++){
			
			try{
				sReTweetCount = Integer.parseInt(getRetweetTextHandlerEle(i).getText());
				Thread.sleep(500);
			}catch(Exception e){
				sReTweetCount = 0;
			}
			
			if(sReTweetCount >= sHighestCount){
				sHighestCount = sReTweetCount;				
			}			
		}		
		return sHighestCount;
	}
	
	//Get getTwiterLikesHighestCount in Timeline Post Count
	public int getTwiterLikesHighestCount(int sLimitCount){
		
		int sTimeLinePostCount = getTimeLineTwiterCount(sLimitCount);
		if(sTimeLinePostCount>=sLimitCount){
			sTimeLinePostCount = sLimitCount;
		}
		int sHighestCount = 0;
		int sLikesCount = 0;
		
		for(int i =1; i<=sTimeLinePostCount;i++){
			
			try{
				sLikesCount = Integer.parseInt(getLikeTextHandlerEle(i).getText());
				Thread.sleep(500);
			}catch(Exception e){
				sLikesCount = 0;
			}
			
			if(sLikesCount >= sHighestCount){
				sHighestCount = sLikesCount;				
			}			
		}		
		return sHighestCount;
	}
	
	
	
	//Get Timeline Post Count	
	public int getSizeOfTimeLineTwitterPosts(){
		
		int sFinalCount = 0;
		
		List<WebElement> xpath = getPostCountList();
		int xpathCount = xpath.size();
		System.out.println("Total Twitter TimeLine Count: " + xpathCount);
		sFinalCount = xpathCount;
		return sFinalCount;
		
	}
	

}
