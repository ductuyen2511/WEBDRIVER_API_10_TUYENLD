package TestNG;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_04_DataProvider {

	WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void initData(String browserName) {
		if(browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		
		}else if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equals("headlesschrome")) {
			System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1366x768");
			driver = new ChromeDriver(options);
		}else {
			System.out.println("Please input correct browser name !");
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "user/pass", invocationCount = 3)
	public void TC_01(String username, String password) {
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id = 'pass']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id = 'send2']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text() ='My Dashboard']")).isDisplayed());

		driver.findElement(By.xpath("//header[@id ='header']//span[text()= 'Account']")).click();
		driver.findElement(By.xpath("//a[text() = 'Log Out']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src, 'logo.png')]")).isDisplayed());
	}
	
	@DataProvider(name = "user/pass")
	public Object[][] UserInformation(){
		return new Object[][] {
			{"ductuyen580@gmail.com","123456"}
		};
	}

	@AfterClass
	public void cleanData() {
		driver.quit();
	}

}
