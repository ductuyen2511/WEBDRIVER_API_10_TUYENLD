package selenium;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_16_WebdriverWait {
	WebDriver driver;
	WebDriverWait wait;
	By startButton = By.xpath("//div[@id = 'start']/button");
	By helloworldText = By.xpath("//div[@id = 'finish']/h4");
	By iconLoading = By.xpath("//div[@id = 'loading']/img");

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	public void TC_01_ImplicitWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); FAIL
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(startButton).click();
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
		Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
		
	}
	
	public void TC_02_Explicit_Invisible_LoadingIcon(){
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		//wait = new WebDriverWait(driver, 3); FAIL
		wait = new WebDriverWait(driver, 5);
		driver.findElement(startButton).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(iconLoading));
		
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
		Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
	}
	
	public void TC_03_Explicit_Visible_HelloWorldText() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		//wait = new WebDriverWait(driver, 3); FAIL
		wait = new WebDriverWait(driver, 5);
		driver.findElement(startButton).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(helloworldText));
		
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
		Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
	}
	
	@Test
	public void TC_04_Explicit_And_Implicit() {
		wait = new WebDriverWait(driver, 8);
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		System.out.println("Start time : " + getDateTimeSecond());
		wait.until(ExpectedConditions.invisibilityOfElementLocated(helloworldText));
		System.out.println("End Time : " + getDateTimeSecond());
		
		System.out.println("Start time : " + getDateTimeSecond());
		wait.until(ExpectedConditions.invisibilityOfElementLocated(iconLoading));
		System.out.println("End Time : " + getDateTimeSecond());
		
		System.out.println("Start time : " + getDateTimeSecond());
		driver.findElement(startButton).click();
		System.out.println("End Time : " + getDateTimeSecond());
		
		System.out.println("Start time : " + getDateTimeSecond());
		wait.until(ExpectedConditions.invisibilityOfElementLocated(iconLoading));
		System.out.println("End Time : " + getDateTimeSecond());
		
		System.out.println("Start time : " + getDateTimeSecond());
		wait.until(ExpectedConditions.invisibilityOfElementLocated(startButton));
		System.out.println("End Time : " + getDateTimeSecond());
	}
	
	public Date getDateTimeSecond() {
		Date date = new Date();
		date = new Timestamp(date.getTime());
		return date;
	}

	// Post-condition
	@AfterClass
	public void afterClass() {
		// Close Browser
		driver.quit();
	}
}
