package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Checkbox_Radio_Alert {
	WebDriver driver;
	JavascriptExecutor js;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
	}

	public void TC_01_Button() {
		driver.get("http://live.guru99.com/");
		// click by selenium
		/*
		 * driver.findElement(By.
		 * xpath("//div[@class = 'footer']//a[text() = 'My Account']")).click();;
		 * Assert.assertEquals(driver.getCurrentUrl(),
		 * "http://live.guru99.com/index.php/customer/account/login/");
		 * 
		 * driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();;
		 * Assert.assertEquals(driver.getCurrentUrl(),
		 * "http://live.guru99.com/index.php/customer/account/create/");
		 */

		// click by Javascript
		WebElement myAccount = driver.findElement(By.xpath("//div[@class = 'footer']//a[text() = 'My Account']"));
		js.executeScript("arguments[0].click();", myAccount);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");

		WebElement createAccount = driver.findElement(By.xpath("//a[@title = 'Create an Account']"));
		js.executeScript("arguments[0].click();", createAccount);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	}

	public void TC_02_Checkbox() throws Exception {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		WebElement checkboxDualZone = driver.findElement(By.xpath("//label[text() = 'Dual-zone air conditioning']/preceding-sibling::input"));

		js.executeScript("arguments[0].click();", checkboxDualZone);
		Thread.sleep(2000);
		Assert.assertTrue(checkboxDualZone.isSelected());

		js.executeScript("arguments[0].click();", checkboxDualZone);
		Thread.sleep(2000);
		Assert.assertFalse(checkboxDualZone.isSelected());
	}

	public void TC_03_CheckTitle() {

	}


	public void TC_04_acceptAlert() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text() = 'Click for JS Alert']")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id = 'result']")).getText(), "You clicked an alert successfully");
	}

	public void TC_05_confirmAlert() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text() = 'Click for JS Confirm']")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id = 'result']")).getText(), "You clicked: Cancel");
	}

	public void TC_06_promptAlert() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text() = 'Click for JS Prompt']")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys("Automation Testing");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id = 'result']")).getText(), "You entered: Automation Testing");
	}

	@Test
	public void TC_07_authenticationAlert() {
		String userNameAndPass = "admin";
		String url = "http://the-internet.herokuapp.com/basic_auth";

		driver.get(inputAuthen(url,userNameAndPass,userNameAndPass));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}

	public String inputAuthen(String url, String username, String password) {
		System.out.println("Old URL is " + url);
		String[] splitUrl = url.split("//");
		url = splitUrl[0] + "//" + username + ":" + password + "@" + splitUrl[1];
		System.out.println("New URL is " + url);
		return url;
	}
	
	// Post-condition
	@AfterClass
	public void afterClass() {
		// Close Browser
		driver.quit();
	}

}
