package tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.testautothon.annotation.AuthorName;
import com.testautothon.annotation.TestAuthor;
import com.testautothon.page.TwiterHomePage;
import com.testautothon.page.YouMayAlsoLikePage;
import com.testautothon.utils.Testautothon;

public class TwitterTest extends Testautothon{
	
	YouMayAlsoLikePage objYMAL;
	TwiterHomePage twitterPage;

	@BeforeClass
	public void invoke()
	{
		objYMAL = new YouMayAlsoLikePage(sedriver);
		twitterPage = new TwiterHomePage(sedriver);
	}
	
	
	@Parameters({"iTimeLinePostsCount"})
	@TestAuthor(name = AuthorName.Thrinadh)
	@Test(description="Extract Top Highest Retweets Count", priority =1)
	public void getHighestRetweets(String iTimeLinePostsCount) {
		System.out.print("Entered -- getHighestRetweets");
		int iRetweetsHighestCount = twitterPage.getTwiterReTweetsHighestCount(Integer.parseInt(iTimeLinePostsCount));
		System.out.print("Highest RetweetsCount" +iRetweetsHighestCount);

	}
	
	@Parameters({"iTimeLinePostsCount"})
	@TestAuthor(name = AuthorName.Thrinadh)
	@Test(description="Extract Top Highest Likes Count", priority = 2)
	public void getHighestLikes(String iTimeLinePostsCount) {
		System.out.print("Entered -- getHighestLikes");
		int iLikesHighestCount = twitterPage.getTwiterLikesHighestCount(Integer.parseInt(iTimeLinePostsCount));
		System.out.print("Highest LikesCount" +iLikesHighestCount);
	}
	
	@Parameters({"listCount"})
	@TestAuthor(name = AuthorName.Harish)
	@Test(description="Fetching Name, Handler, Following and Followers top 3 list from You may also like page.", priority = 3)
	public void youMayAlsoLike(String listCount) {
		objYMAL.login();
		Assert.assertEquals("STeP-IN Forum", objYMAL.ymalTitle());
		objYMAL.getYMALList(Integer.parseInt(listCount));
		objYMAL.prepareJSON();
	}
}
