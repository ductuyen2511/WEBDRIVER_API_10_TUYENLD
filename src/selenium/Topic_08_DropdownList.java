package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_DropdownList {
	WebDriver driver;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// Action of Testcase
	@Test
	public void TC_02_DropdownlistSelect() throws Exception {
		// step 01
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		WebElement jobRoleDropdownlist = driver.findElement(By.xpath("//select[@id ='job1']"));
		Select select = new Select(jobRoleDropdownlist);

		// step 02
		Assert.assertFalse(select.isMultiple());

		// step 03
		select.selectByVisibleText("Automation Tester");
		Thread.sleep(2000);

		// step 04
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");

		// step 05
		select.selectByValue("manual");
		Thread.sleep(2000);

		// step 06
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");

		// step 07
		select.selectByIndex(3);
		Thread.sleep(2000);

		// step 08
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
		System.out.println(select.getOptions().size());

		// step 09
		Assert.assertEquals(select.getOptions().size(), 5);
	}

	@Test
	public void TC_03_DropdownlistCuston() throws Exception {
		// Jquey
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		// parent //span[@id='number-button']
		// allItems //ul[@id = 'number-menu']/li

		SelectItemDropdown("//span[@id='number-button']","//ul[@id ='number-menu']/li","19");
		Thread.sleep(2000);
		
		SelectItemDropdown("//span[@id='number-button']","//ul[@id ='number-menu']/li","10");
		Thread.sleep(2000);
		
		SelectItemDropdown("//span[@id='number-button']","//ul[@id ='number-menu']/li","15");
		Thread.sleep(2000);
		
	}

	public void SelectItemDropdown(String parentLocator, String allItemsDropdown, String expectedText) {
		WebElement parentElement = driver.findElement(By.xpath(parentLocator));
		parentElement.click();
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsDropdown)));
		
		List <WebElement> allElement = driver.findElements(By.xpath(allItemsDropdown));
		System.out.println("All item = " + allElement.size());
		
		//for
		for (int i = 0; i < allElement.size(); i ++){
			String item = allElement.get(i).getText();
			System.out.println("Item text element thu :" + i + " = " + item);
		
			if (item.equals(expectedText)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allElement.get(i));
				allElement.get(i).click();
				break;
			}
		}
		// Wait all value show
	}

	// Post-condition
	@AfterClass
	public void afterClass() {
		// Close Browser
		driver.quit();
	}

}
