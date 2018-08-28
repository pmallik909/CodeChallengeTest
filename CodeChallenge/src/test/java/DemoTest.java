import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dropbox.test.base.BaseClass;
import com.dropbox.test.pages.LandingPage;

public class DemoTest extends BaseClass{
	
	LandingPage landingPage;
	//LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
	
	public DemoTest(){
		super();
	}
	
	@BeforeTest
	public void browserLaunch() {
		initializeTest();
		landingPage = new LandingPage();
	}
	
	
	@Test
	public void loginTest() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		landingPage.login(properties.getProperty("email"), properties.getProperty("password"));
	}
	
	
	

}
