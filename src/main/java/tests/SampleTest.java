package tests;

import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testautothon.annotation.AuthorName;
import com.testautothon.annotation.TestAuthor;
import com.testautothon.bussiness.WebPageBussinessMethods;
import com.testautothon.page.BasePageWeb;
import com.testautothon.page.GoogleHomePage;
import com.testautothon.page.WebPageObjRepo;
import com.testautothon.utils.Testautothon;

public class SampleTest extends Testautothon{
	
	BasePageWeb basePageWeb;
	GoogleHomePage googlePage;
	
	@BeforeClass
	public void invoke()
	{
		basePageWeb = new BasePageWeb(sedriver);
		googlePage = new GoogleHomePage(sedriver);
	}
	
	@TestAuthor(name = AuthorName.Harish)
	@Test(description="This is a sample test1")
	public void sample1() {
		basePageWeb.type(googlePage.getSearchBox(), "GE");
		basePageWeb.type(googlePage.getSearchBox(), Keys.ENTER);
		googlePage.getSearchReturnLink().click();
	}

	
	@TestAuthor(name =AuthorName.Subhash)
	@Test(description = "This is a sample MOBILE Test case")
	public void sample_mobile() {
		apdriver.get(baseUrl);
	}
		

	@TestAuthor(name = AuthorName.Thrinadh)
	@Test(description="This is a sample test2", priority =1)
	public void Sample2() {
		System.out.print("Sampel Verification");

	}
}
