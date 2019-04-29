package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Part_1 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.guru99.com/");
	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");
		driver.findElement(By.cssSelector("#send2")).click();

		String emailErrorMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
		System.out.println("Email error message " + emailErrorMessage);
		
		String passwordErrorMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
		System.out.println("Password error message " + passwordErrorMessage);

		Assert.assertEquals(emailErrorMessage, "This is a required field.");
		Assert.assertEquals(passwordErrorMessage, "This is a required field.");
	}
	
	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.name("login[password]")).sendKeys("");
		driver.findElement(By.cssSelector("#send2")).click();
		
		String inputInvalidEmail = driver.findElement(By.id("advice-validate-email-email")).getText();
		System.out.println("Email invalid error message " + inputInvalidEmail);
		
		Assert.assertEquals(inputInvalidEmail, "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	
	@Test
	public void TC_03_LoginWithPasswordLessThanSixChars() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");
		driver.findElement(By.cssSelector("#send2")).click();
		
		String inputPasswordLessThanSixChars = driver.findElement(By.id("advice-validate-password-pass")).getText();
		System.out.println("Password less than six chars error message " + inputPasswordLessThanSixChars);
		
		Assert.assertEquals(inputPasswordLessThanSixChars, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_LoginWithPasswordIncorrect() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123123123");
		driver.findElement(By.cssSelector("#send2")).click();
		driver.findElement(By.className("error-msg"));
		
		String inputPasswordIncorrect = driver.findElement(By.xpath("//li/ul/li")).getText();
		System.out.println("Password incorrect error message" + inputPasswordIncorrect);
		
		Assert.assertEquals(inputPasswordIncorrect, "Invalid login or password.");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
