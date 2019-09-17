package tests;

import org.testng.annotations.Test;

import com.testautothon.annotation.AuthorName;
import com.testautothon.annotation.TestAuthor;
import com.testautothon.utils.Testautothon;

public class SampleTest extends Testautothon{
	
	@TestAuthor(name = AuthorName.Subhash)
	@Test(description="This is a sample test")
	public void sample() {
		
		//this is just the sample test for launching browser
	}

}
