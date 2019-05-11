package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_SeleniumAPI {
	WebDriver driver;
	// Enable Element
	By emailTextbox = By.xpath("//input[@id ='mail']");
	By ageRadioUnder18 = By.xpath("//input[@id ='under_18']");
	By ageRadioOver18 = By.xpath("//input[@id ='over_18']");
	By textAreaEducation = By.xpath("//textarea[@id ='edu']");
	By jobRole01Combobox = By.xpath("//select[@id ='job1']");
	By radioInterestDevelop = By.xpath("//input[@id ='development']");
	By slide01 = By.xpath("//input[@id ='slider-1']");
	By button01 = By.xpath("//button[@id ='button-enabled']");

	// Disable Element
	By passWordDisabled = By.xpath("//input[@id ='password']");
	By ageRadioDisabled = By.xpath("//input[@id ='radio-disabled']");
	By biographyDisabled = By.xpath("//textarea[@id ='bio']");
	By jobRole02Combobox = By.xpath("//select[@id ='job2']");
	By radioInterestDisabled = By.xpath("//input[@id ='check-disbaled']");
	By slide02 = By.xpath("//input[@id ='slider-2']");
	By button02 = By.xpath("//button[@id ='button-disabled']");

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Open Browser
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://daominhdam.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_IsDisplayed() throws InterruptedException {
		if (isElementDisplayed(emailTextbox)) {
			sendkeyToElement(emailTextbox, Clear());
			sendkeyToElement(emailTextbox, "Automation Testing");
		}
		if (isElementDisplayed(ageRadioUnder18)) {
			clickToElement(ageRadioUnder18);
		}
		if (isElementDisplayed(textAreaEducation)) {
			sendkeyToElement(textAreaEducation, "Automation Testing");
		}
		Thread.sleep(5000);
	}

	@Test
	public void TC_02_EnalbledAndDisabled() throws Exception {

		// Check element enable
		isEnabled(emailTextbox);
		isEnabled(ageRadioUnder18);
		isEnabled(textAreaEducation);
		isEnabled(jobRole01Combobox);
		isEnabled(radioInterestDevelop);
		isEnabled(slide01);
		isEnabled(button01);

		// Check element disabled
		isEnabled(passWordDisabled);
		isEnabled(ageRadioDisabled);
		isEnabled(biographyDisabled);
		isEnabled(jobRole02Combobox);
		isEnabled(radioInterestDisabled);
		isEnabled(slide02);
		isEnabled(button02);

		Thread.sleep(3000);
	}

	@Test
	public void TC_03_SelectedAndUnSelected() {
		clickToElement(ageRadioUnder18);
		clickToElement(radioInterestDevelop);
		
		Assert.assertTrue(isSelected(ageRadioUnder18));
		Assert.assertTrue(isSelected(radioInterestDevelop));
		
		clickToElement(ageRadioOver18);
		clickToElement(radioInterestDevelop);
		
		Assert.assertFalse(isSelected(ageRadioUnder18));
		Assert.assertFalse(isSelected(radioInterestDevelop));
	}

	private String Clear() {
		// TODO Auto-generated method stub
		return null;
	}

	public void isEnabled(By by) {
		WebElement element = driver.findElement(by);

		if (element.isEnabled()) {
			System.out.println("Element" + by + "is enabled");
		} else {
			System.out.println("Element" + by + "is disabled");
		}
	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		return element.isDisplayed();
	}

	public boolean isSelected(By by) {
		WebElement element = driver.findElement(by);
		return element.isSelected();
	}

	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	public void sendkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
