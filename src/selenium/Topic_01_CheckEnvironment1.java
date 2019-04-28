package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_CheckEnvironment1 {
	WebDriver driver;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Open Browser
		driver = new FirefoxDriver();

		// Open Link
		driver.get("http://live.guru99.com/");

		// Wait element vissible in 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	//	 Action of Testcase
	@Test
	public void TC_01_CheckUrl() {
		// Check url is correct
		String homePageUrl = driver.getCurrentUrl();
		System.out.println("Home page Url = " + homePageUrl);
		Assert.assertEquals(homePageUrl, "http://live.guru99.com/");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/");
	}

	@Test
	public void TC_02_CheckTitle() {
		String homePageTitle = driver.getTitle();
		System.out.println("Home page Title = " + homePageTitle);
		Assert.assertEquals(homePageTitle, "Home page");

	}

	// Post-condition
	@AfterClass
	public void afterClass() {
		// Close Browser
		driver.quit();
	}

}
