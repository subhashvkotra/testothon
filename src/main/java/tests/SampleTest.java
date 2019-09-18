package tests;

import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testautothon.annotation.AuthorName;
import com.testautothon.annotation.TestAuthor;
import com.testautothon.bussiness.WebPageBussinessMethods;
import com.testautothon.page.WebPageObjRepo;

public class SampleTest extends WebPageObjRepo{
	
	WebPageBussinessMethods objWebPageBussinessMethods;
	
	@BeforeClass
	public void invoke()
	{
		objWebPageBussinessMethods = new WebPageBussinessMethods();
	}
	
	@TestAuthor(name = AuthorName.Harish)
	@Test(description="This is a sample test1")
	public void sample1() {
		objWebPageBussinessMethods.type(searchBox, "GE");
		objWebPageBussinessMethods.type(searchBox, Keys.ENTER);
		objWebPageBussinessMethods.click(link);
	}
	@TestAuthor(name = AuthorName.Thrinadh)
	@Test(description="This is a sample test2", priority =1)
	public void Sample2() {
		System.out.print("Sampel Verification");
	}
}
