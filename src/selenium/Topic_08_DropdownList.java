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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// Action of Testcase
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

	public void TC_03_DropdownlistCustomJquery() throws Exception {
		// Jquey
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		// parent //span[@id='number-button']
		// allItems //ul[@id = 'number-menu']/li

		SelectItemDropdown("//span[@id='number-button']", "//ul[@id ='number-menu']/li", "19");
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text() = '19']")).isDisplayed());

		SelectItemDropdown("//span[@id='number-button']", "//ul[@id ='number-menu']/li", "10");
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text() = '10']")).isDisplayed());

		SelectItemDropdown("//span[@id='number-button']", "//ul[@id ='number-menu']/li", "15");
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text() = '15']")).isDisplayed());
	}

	public void TC_04_DropdownlistCustomAngular() throws Exception {
		driver.get("https://material.angular.io/components/select/examples");
		
		SelectItemDropdown("//mat-label[text() = 'State']/ancestor::Span/preceding-sibling::mat-select//div[@class = 'mat-select-arrow-wrapper']","//mat-option/span","Florida");
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//mat-label[text() = 'State']/ancestor::Span/preceding-sibling::mat-select//div[@class = 'mat-select-value']//span[text() = 'Florida']")).isDisplayed());
		
		SelectItemDropdown("//mat-label[text() = 'State']/ancestor::Span/preceding-sibling::mat-select//div[@class = 'mat-select-arrow-wrapper']","//mat-option/span","California");
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//mat-label[text() = 'State']/ancestor::Span/preceding-sibling::mat-select//div[@class = 'mat-select-value']//span[text() = 'California']")).isDisplayed());
	}
	
	
	public void TC_05_DropdownlistKendoUI() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		
		SelectItemDropdown("//span[@aria-owns = 'color_listbox']//span[@class = 'k-select']","//ul[@id = 'color_listbox']/li","Orange");
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns ='color_listbox']//span[@class = 'k-input' and text() ='Orange']")).isDisplayed());
		
		SelectItemDropdown("//span[@aria-owns = 'color_listbox']//span[@class = 'k-select']","//ul[@id = 'color_listbox']/li","Black");
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns ='color_listbox']//span[@class = 'k-input' and text() ='Black']")).isDisplayed());
	}
	
	
	public void TC_06_DropdownlistReactJS() throws Exception {
		//div[@role ='listbox' and contains(@class,'fluid')]
		//div[contains(@class,'visible menu transition' )]
		driver.get("https://react.semantic-ui.com/modules/dropdown/");
		SelectItemDropdown("//div[@role ='listbox' and contains(@class,'fluid')]","//div[contains(@class,'visible menu transition' )]/div","Matt");
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role ='listbox' and contains(@class,'fluid')]//div[@class ='text' and text() = 'Matt']")).isDisplayed());
		
		SelectItemDropdown("//div[@role ='listbox' and contains(@class,'fluid')]","//div[contains(@class,'visible menu transition' )]/div","Christian");
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role ='listbox' and contains(@class,'fluid')]//div[@class ='text' and text() = 'Christian']")).isDisplayed());
		
	}

	public void TC_07_DropdownlistVueJS() throws Exception {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		SelectItemDropdown("//li[@class ='dropdown-toggle']","//ul[@class = 'dropdown-menu']/li","Third Option");
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='dropdown-toggle' and contains(text(), 'Third Option')]")).isDisplayed());
		
		SelectItemDropdown("//li[@class ='dropdown-toggle']","//ul[@class = 'dropdown-menu']/li","Second Option");
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='dropdown-toggle' and contains(text(), 'Second Option')]")).isDisplayed());
	}
	
	@Test
	public void TC_08_DropdownlistEditable() throws Exception {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		driver.findElement(By.xpath("//div[@data-effects = 'default']/input")).sendKeys("A");
		Thread.sleep(2000);
		SelectItemDropdown("//div[@data-effects = 'default']/input","//div[text() = 'Default']/following-sibling::div/ul/li[@class ='es-visible']","Smart");
		Thread.sleep(2000);
		//div[@data-effects = 'default']/input/following-sibling::ul/li[contains(@class,'selected')]
		//Assert.assertTrue(driver.findElement(By.xpath("//div[text() = 'Default']/following-sibling::div/ul/li[@class ='es-visible' and text() = 'Smart']")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@data-effects = 'default']/input")).clear();
		driver.findElement(By.xpath("//div[@data-effects = 'default']/input")).sendKeys("A");
		SelectItemDropdown("//div[@data-effects = 'default']/input","//div[text() = 'Default']/following-sibling::div/ul/li[@class ='es-visible']","Nissan");
		Thread.sleep(2000);
	}
	
	
	public void SelectItemDropdown(String parentLocator, String allItemsDropdown, String expectedText) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		// click dropdowm;ist
		WebElement parentElement = driver.findElement(By.xpath(parentLocator));
		js.executeScript("arguments[0].click();", parentElement);
		Thread.sleep(2000);

		//wait dropdownlist show
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsDropdown)));
		
		//get all element ito list
		List<WebElement> allElement = driver.findElements(By.xpath(allItemsDropdown));
		System.out.println("All item = " + allElement.size());

		// for
		/*for (int i = 0; i < allElement.size(); i++) {
			String item = allElement.get(i).getText();
			System.out.println("Item text element thu :" + i + " = " + item);

			if (item.equals(expectedText)) {
				js.executeScript("arguments[0].scrollIntoView(true);", allElement.get(i));
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", allElement.get(i));
				break;
			}
		}*/
		
		for(WebElement childrenElement : allElement) {
			System.out.println("Text moi lan get =" + childrenElement.getText());
			
			if(childrenElement.getText().contains(expectedText)) {
				if(childrenElement.isDisplayed()) {
					System.out.println("Click by Selenium =" + childrenElement.getText());
					childrenElement.click();
				}else {
					js.executeScript("arguments[0].scrollIntoView(true);", childrenElement);
					Thread.sleep(1000);
					System.out.println("Click by JS =" + childrenElement.getText());
					js.executeScript("arguments[0].click();", childrenElement);
				}
					
			}
		}
	}

	// Post-condition
	@AfterClass
	public void afterClass() {
		// Close Browser
		driver.quit();
	}

}
