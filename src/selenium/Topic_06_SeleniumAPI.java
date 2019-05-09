package selenium;

import static org.junit.Assert.assertFalse;

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
		
		//Check element enable
		
		/*Assert.assertTrue(isEnabled(emailTextbox));	
		Assert.assertTrue(isEnabled(ageRadioUnder18));
		Assert.assertTrue(isEnabled(textAreaEducation));
		Assert.assertTrue(isEnabled(jobRole01Combobox));
		Assert.assertTrue(isEnabled(radioInterestDevelop));
		Assert.assertTrue(isEnabled(slide01));
		Assert.assertTrue(isEnabled(button01));
		
		//Check element disabled
		Assert.assertFalse(isEnabled(passWordDisabled));
		Assert.assertFalse(isEnabled(ageRadioDisabled));
		Assert.assertFalse(isEnabled(biographyDisabled));
		Assert.assertFalse(isEnabled(jobRole02Combobox));
		Assert.assertFalse(isEnabled(radioInterestDisabled));
		Assert.assertFalse(isEnabled(slide02));
		Assert.assertFalse(isEnabled(button02));*/
		
		if (isEnabled(emailTextbox)) {
			System.out.println("\n Email is Enabled");
		} 
		else {
			System.out.println("\n Email is Disabled");
		}
		if (isEnabled(ageRadioUnder18)) {
			System.out.println("\n Radio is Enabled");
		} else {
			System.out.println("\n Radio is Disabled");
		}
		
		if (isEnabled(textAreaEducation)) {
			System.out.println("\n textAreaEducation is Enabled");
		} else {
			System.out.println("\n textAreaEducation is Disabled");
		}
		
		if (isEnabled(jobRole01Combobox)) {
			System.out.println("\n jobRole01Combobox is Enabled");
		} else {
			System.out.println("\n jobRole01Combobox is Disabled");
		}
		
		if (isEnabled(radioInterestDevelop)) {
			System.out.println("\n radioInterestDevelop is Enabled");
		} else {
			System.out.println("\n radioInterestDevelop is Disabled");
		}
		
		if (isEnabled(slide01)) {
			System.out.println("\n slide01 is Enabled");
		} else {
			System.out.println("\n slide01 is Disabled");
		}
		
		if (isEnabled(button01)) {
			System.out.println("\n button01 is Enabled");
		} else {
			System.out.println("\n button01 is Disabled");
		}
		//------------------------------------------------------
		if (isEnabled(passWordDisabled)) {
			System.out.println("\n passWordDisabled is Enabled");
		} else {
			System.out.println("\n passWordDisabled is Disabled");
		}
		if (isEnabled(ageRadioDisabled)) {
			System.out.println("\n ageRadioDisabled is Enabled");
		} else {
			System.out.println("\n ageRadioDisabled is Disabled");
		}
		if (isEnabled(biographyDisabled)) {
			System.out.println("\n biographyDisabled is Enabled");
		} else {
			System.out.println("\n biographyDisabled is Disabled");
		}
		if (isEnabled(jobRole02Combobox)) {
			System.out.println("\n jobRole02Combobox is Enabled");
		} else {
			System.out.println("\n jobRole02Combobox is Disabled");
		}
		if (isEnabled(radioInterestDisabled)) {
			System.out.println("\n radioInterestDisabled is Enabled");
		} else {
			System.out.println("\n radioInterestDisabled is Disabled");
		}
		if (isEnabled(slide02)) {
			System.out.println("\n slide02 is Enabled");
		} else {
			System.out.println("\n slide02 is Disabled");
		}
		if (isEnabled(button02)) {
			System.out.println("\n button02 is Enabled");
		} else {
			System.out.println("\n button02 is Disabled");
		}
		Thread.sleep(3000);
		}

	private String Clear() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEnabled(By by) {
		WebElement element = driver.findElement(by);
		return element.isEnabled();
	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		return element.isDisplayed();
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
