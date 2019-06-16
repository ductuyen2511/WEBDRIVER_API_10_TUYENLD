package selenium;

import java.sql.Timestamp;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
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

		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); FAIL
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(startButton).click();
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
		Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");

	}

	public void TC_02_Explicit_Invisible_LoadingIcon() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		// wait = new WebDriverWait(driver, 3); FAIL
		wait = new WebDriverWait(driver, 5);
		driver.findElement(startButton).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(iconLoading));

		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
		Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
	}

	public void TC_03_Explicit_Visible_HelloWorldText() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		// wait = new WebDriverWait(driver, 3); FAIL
		wait = new WebDriverWait(driver, 5);
		driver.findElement(startButton).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(helloworldText));

		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
		Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
	}

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

	@Test
	public void TC_05_ExplicitWait() {

		By datetimePicker = By.xpath("//div[contains(@class, 'demo-container')]//div[@class = 'calendarContainer']");
		By loadingAjax = By.xpath("//div[@class = 'raDiv']");
		By dateClick = By.xpath("//table[@class ='rcMainTable']//td[@class = 'rcWeekend']//a[text() ='15']");
		By dateSelected = By.xpath("//*[contains(@class, 'rcSelected')]//a[text() = '15']");

		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.visibilityOfElementLocated(datetimePicker));
		System.out.println("Selected day :" + driver.findElement(By.xpath("//span[@id = 'ctl00_ContentPlaceholder1_Label1']")).getText());

		driver.findElement(dateClick).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingAjax));
		wait.until(ExpectedConditions.visibilityOfElementLocated(dateSelected));

		WebElement dateVerify = driver.findElement(By.xpath("//span[@id = 'ctl00_ContentPlaceholder1_Label1']"));
		System.out.println("Date Selected is : " + dateVerify.getText());
		Assert.assertEquals(dateVerify.getText(), "Saturday, June 15, 2019");
	}

	public void TC_06_FluentWait() {
		driver.get("https://daominhdam.github.io/fluent-wait/");
		WebElement countdown = driver.findElement(By.xpath("//div[@id = 'javascript_countdown_time']"));
		wait.until(ExpectedConditions.visibilityOf(countdown));
		
		new FluentWait<WebElement>(countdown)
			.withTimeout(15, TimeUnit.SECONDS)
			.pollingEvery(1, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class);
		 	/*.until(new Function<WebElement, Boolean>(){
		 		public Boolean apply(WebElement element) {
		 			boolean flag = element.getText().endsWith("00");
		 			System.out.println("Time = " + element.getText());
		 			return flag;
		 		}
		 	});*/
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
