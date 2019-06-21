package selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
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
	
	String workingDirectory = System.getProperty("user.dir");
	String jsFirePath = workingDirectory + "\\Helper\\drag_and_drop_helper.js";
	String jqueryFirePath = workingDirectory + "\\Helper\\jquery_load_helper.js";

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
	
	public void TC_06_DragAndDrop() throws InterruptedException {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement smallTarget = driver.findElement(By.xpath("//div[@id ='draggable']"));
		WebElement bigTarget = driver.findElement(By.xpath("//div[@id ='droptarget']"));
		
		action.dragAndDrop(smallTarget, bigTarget).perform();
		Thread.sleep(2000);
		
		Assert.assertEquals(bigTarget.getText(), "You did great!");	
	}
	
	
	public void TC_07_DragAndDropByCss() throws Exception {
		driver.get("https://bestvpn.org/html5demos/drag/");
		String oneCss = "#one";
		String targetCss = "#bin";
		
		String java_script = readFile(jsFirePath);
		String jQueryLoader = readFile(jqueryFirePath);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(jQueryLoader);
		
		java_script = java_script + "$(\"" + oneCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		js.executeScript(java_script);
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_08_DragAndDropByXpath() throws Exception {
		driver.get("https://bestvpn.org/html5demos/drag/");
		
		String onePath = "//a[@id = 'one']";
		String targetPath = "//div[@id = 'bin']";
		
		drag_the_and_drop_html5_by_xpath(onePath, targetPath);
		Thread.sleep(3000);
	}
	
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

 
	// Post-condition
	@AfterClass
	public void afterClass() {
		// Close Browser
		driver.quit();
	}

}
