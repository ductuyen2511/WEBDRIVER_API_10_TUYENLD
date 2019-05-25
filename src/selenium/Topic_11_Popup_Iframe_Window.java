package selenium;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup_Iframe_Window {
	WebDriver driver;
	JavascriptExecutor js;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
	}

	public void TC_01_Button() {
		driver.get("http://www.hdfcbank.com/");

		WebElement iframeLook = driver.findElement(By.xpath("//iframe[starts-with(@id, 'viz_iframe')]"));
		driver.switchTo().frame(iframeLook);

		Assert.assertEquals(driver.findElement(By.xpath("//span[@id = 'messageText']")).getText(), "What are you looking for?");

		// back to default
		driver.switchTo().defaultContent();

		// 5 images banner show
		List<WebElement> imageBanner5 = driver.findElements(By.xpath("//div[@id='rightbanner']//div[@class = 'owl-stage']/div[not(@class = 'owl-item cloned')]//img"));
		Assert.assertEquals(imageBanner5.size(), 5);

		/*
		 * for(WebElement image: imageBanner5) { Assert.assertTrue(image.isDisplayed());
		 * }
		 */

		// 8 images banner show
		List<WebElement> imageBanner8 = driver.findElements(By.xpath("//div[@class ='flipBanner']//img[@class='front icon']"));
		Assert.assertEquals(imageBanner8.size(), 8);

		for (WebElement image : imageBanner8) {
			Assert.assertTrue(image.isDisplayed());
		}
	}

	@Test
	public void TC_02_Window() throws InterruptedException {
		driver.get("https://daominhdam.github.io/basic-form/index.html");

		// get current tab's id
		String parentID = driver.getWindowHandle();
		System.out.print("Parent Id = " + parentID);
		
		//google
		driver.findElement(By.xpath("//a[text() = 'GOOGLE']")).click();
		Thread.sleep(2000);
		switchToWinDowByTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");
		
		//back to parent
		switchToWinDowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		
		//facebook
		driver.findElement(By.xpath("//a[text() = 'FACEBOOK']")).click();
		switchToWinDowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		//back to parent
		switchToWinDowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		
		//tiki
		driver.findElement(By.xpath("//a[text() = 'TIKI']")).click();
		Thread.sleep(2000);
		switchToWinDowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		
		//close all without parent
		closeAllWindowWithoutParent(parentID);
	}

	// if there are motr than 2 tab, not work
	public void switchToChildWindowById(String parent) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindow : allWindow) {
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public boolean closeAllWindowWithoutParent(String parent) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindow : allWindow) {
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parent);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else

			return false;
	}

	public void switchToWinDowByTitle(String title) {
		Set<String> allWindow = driver.getWindowHandles();
		for(String runWindow : allWindow) {
			driver.switchTo().window(runWindow);
			
			String currenTitle = driver.getTitle();
			if(currenTitle.equals(title)) {
				break;
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
