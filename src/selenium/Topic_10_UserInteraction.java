package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_UserInteraction {
	WebDriver driver;
	JavascriptExecutor js;
	Actions action;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
		action = new Actions(driver);
	}
	
	
	public void TC_01_MoveMouseToElemnt() {
		driver.get("http://www.myntra.com/");
		WebElement profileText = driver.findElement(By.xpath("//span[text() = 'Profile']"));
		action.moveToElement(profileText).perform();
		driver.findElement(By.xpath("//a[text() = 'log in']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'login-box']")).isDisplayed());

	}
	
	
	public void TC_02_ClickAndHoldElement() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> listElement = driver.findElements(By.xpath("//ol[@id = 'selectable']//li"));
		action.clickAndHold(listElement.get(0)).moveToElement(listElement.get(3)).release().perform();

		List<WebElement> selectedElement = driver.findElements(By.xpath("//ol[@id = 'selectable']//li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(selectedElement.size(), 4);
	}
	
	public void TC_03_ClickAndHoldRandomItem() throws InterruptedException {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> listElementRandom = driver.findElements(By.xpath("//ol[@id = 'selectable']//li"));
		action.keyDown(Keys.CONTROL).perform();
		listElementRandom.get(0).click();
		listElementRandom.get(3).click();
		listElementRandom.get(5).click();
		listElementRandom.get(10).click();
		action.keyUp(Keys.CONTROL).perform();
		Thread.sleep(2000);

		List<WebElement> selectedElement = driver.findElements(By.xpath("//ol[@id = 'selectable']//li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(selectedElement.size(), 4);
	}

	public void TC_04_doubleClickButton() throws InterruptedException {
		driver.get("http://www.seleniumlearn.com/double-click");
		WebElement doubleClick = driver.findElement(By.xpath("//button[text() ='Double-Click Me!']"));
		action.doubleClick(doubleClick).perform();

		Assert.assertEquals(driver.switchTo().alert().getText(), "The Button was double-clicked.");
		driver.switchTo().alert().accept();
	}
	
	public void TC_05_RightClick() throws InterruptedException {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightClick = driver.findElement(By.xpath("//span[text() = 'right click me']"));
		action.contextClick(rightClick).perform();

		WebElement beforeHover = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-visible')]"));
		action.moveToElement(beforeHover).perform();
		
		WebElement afterHover = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-visible') and contains(@class,'context-menu-visible')]"));
		Assert.assertTrue(afterHover.isDisplayed());
		
		//afterHover.click();
		//Alert alert = driver.switchTo().alert();
		//Assert.assertEquals(alert.getText(), "clicked: quit");
		//System.out.println("Message :" +  alert.getText());
		//alert.accept();
	}
	
	@Test
	public void TC_06_DragAndDrop() throws InterruptedException {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement smallTarget = driver.findElement(By.xpath("//div[@id ='draggable']"));
		WebElement bigTarget = driver.findElement(By.xpath("//div[@id ='droptarget']"));
		
		action.dragAndDrop(smallTarget, bigTarget).perform();
		Thread.sleep(2000);
		
		Assert.assertEquals(bigTarget.getText(), "You did great!");
		
	}
	// Post-condition
	@AfterClass
	public void afterClass() {
		// Close Browser
		driver.quit();
	}

}
