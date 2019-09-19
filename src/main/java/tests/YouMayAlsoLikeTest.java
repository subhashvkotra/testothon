package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.testautothon.annotation.AuthorName;
import com.testautothon.annotation.TestAuthor;
import com.testautothon.page.YouMayAlsoLikePage;
import com.testautothon.utils.Testautothon;

public class YouMayAlsoLikeTest extends Testautothon {

	YouMayAlsoLikePage objYMAL;
	
	@BeforeClass
	public void invoke()
	{
		objYMAL = new YouMayAlsoLikePage(sedriver);
	}
	
	@Parameters({"listCount"})
	@TestAuthor(name = AuthorName.Harish)
	@Test(description="Fetching Name, Handler, Following and Followers top 3 list from You may also like page.")
	public void youMayAlsoLike(String listCount) {
		objYMAL.login();
		Assert.assertEquals("STeP-IN Forum", objYMAL.ymalTitle());
		objYMAL.getYMALList(Integer.parseInt(listCount));
		objYMAL.prepareJSON();
	}
}
