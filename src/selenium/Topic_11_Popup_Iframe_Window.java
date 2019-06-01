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
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
	}

	public void TC_01_Button() throws InterruptedException {
		driver.get("http://www.hdfcbank.com/");
		Thread.sleep(5000);
		//IsDisplayed : just check 1 matching node displayed, not use if size = 0
		//WebElement Return exception if no matching node show, at least 1 matching node,
		//WebElement imagePopup = driver.findElement(By.xpath("//div[@id = 'parentdiv']//img[@class = 'popupbanner at-element-click-tracking']"));
		
		//List WebElement Return empty list if no matching node show
		//size >= 1, unfortunately, size = 1 show in DOM but not displayed ==> FALSE
		//size = 0 not show in DOM, not displayed in UI => FALSE
		//sieze >= 1 show in DOM, displayed in UI => TRUE
		List <WebElement> imagePopups = driver.findElements(By.xpath("//div[@id = 'parentdiv']//img[@class = 'popupbanner at-element-click-tracking']"));
		System.out.println("Display :" + imagePopups.size());
		
		if (imagePopups.size() > 0 && imagePopups.get(0).isDisplayed()) {
			System.out.println("show in DOM, displayed in UI");
		}
		else if(imagePopups.size() > 0 && !imagePopups.get(0).isDisplayed()){
			System.out.println("show in DOM but not displayed");
		}
		else if(imagePopups.size() == 0){
			System.out.println("not show in DOM, not displayed in UI");
		}
		
		if(imagePopups.size() > 0 && imagePopups.get(0).isDisplayed()) {
			driver.findElement(By.xpath("//img[@class = 'popupCloseButton']")).click();
			Thread.sleep(2000);
			Assert.assertFalse(imagePopups.get(0).isDisplayed());
		}
		
		WebElement iframeLook = driver.findElement(By.xpath("//iframe[starts-with(@id, 'viz_iframe')]"));
		driver.switchTo().frame(iframeLook);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id = 'messageText']")).getText(), "What are you looking for?");

		// back to default
		driver.switchTo().defaultContent();

		// 5 images banner show
		//List<WebElement> imageBanner5 = driver.findElements(By.xpath("//div[@id='rightbanner']//div[@class = 'owl-stage']/div[not(@class = 'owl-item cloned')]//img"));
		//Assert.assertEquals(imageBanner5.size(), 5);

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

	public void TC_02_Window() throws InterruptedException {
		driver.get("https://daominhdam.github.io/basic-form/index.html");

		// get current tab's id
		String parentID = driver.getWindowHandle();
		System.out.print("Parent Id = " + parentID);

		// google
		driver.findElement(By.xpath("//a[text() = 'GOOGLE']")).click();
		Thread.sleep(2000);
		switchToWinDowByTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");

		// back to parent
		switchToWinDowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

		// facebook
		driver.findElement(By.xpath("//a[text() = 'FACEBOOK']")).click();
		switchToWinDowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");

		// back to parent
		switchToWinDowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

		// tiki
		driver.findElement(By.xpath("//a[text() = 'TIKI']")).click();
		Thread.sleep(2000);
		switchToWinDowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");

		// close all without parent
		closeAllWindowWithoutParent(parentID);
	}

	public void TC_03() throws InterruptedException {
		driver.get("http://www.hdfcbank.com/");

		String parentId = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text() = 'Agri']")).click();
		Thread.sleep(2000);

		switchToWinDowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		Assert.assertEquals(driver.getTitle(), "HDFC Bank Kisan Dhan Vikas e-Kendra");

		driver.findElement(By.xpath("//p[text() = 'Account Details']")).click();
		Thread.sleep(2000);

		switchToWinDowByTitle("Welcome to HDFC Bank NetBanking");
		Assert.assertEquals(driver.getTitle(), "Welcome to HDFC Bank NetBanking");

		WebElement frameFooter = driver.findElement(By.xpath("//frame[@name = 'footer']"));
		driver.switchTo().frame(frameFooter);

		driver.findElement(By.xpath("//a[text() = 'Privacy Policy']")).click();
		switchToWinDowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		Assert.assertEquals(driver.getTitle(), "HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");

		driver.findElement(By.xpath("//a[text() = 'CSR']")).click();
		driver.switchTo().defaultContent();

		closeAllWindowWithoutParent(parentId);
	}

	@Test
	public void TC_04() throws InterruptedException {
		driver.get("http://live.guru99.com/index.php/");
		String parentId = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text() = 'Mobile']")).click();
		WebElement sonyExperia = driver.findElement(By.xpath("//a[text() = 'Sony Xperia']/parent::h2/following-sibling::div[@class= 'actions']/child::ul/li/a[text() = 'Add to Compare']"));
		sonyExperia.click();
		Thread.sleep(2000);

		WebElement samsungGalaxy = driver.findElement(By.xpath("//a[text() = 'Samsung Galaxy']/parent::h2/following-sibling::div[@class= 'actions']/child::ul/li/a[text() = 'Add to Compare']"));
		samsungGalaxy.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[text() = 'Compare']")).click();
		Thread.sleep(2000);

		switchToWinDowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		closeAllWindowWithoutParent(parentId);
		Thread.sleep(2000);
	}

	// if there are more than 2 tabs, not work
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
		for (String runWindow : allWindow) {
			driver.switchTo().window(runWindow);

			String currenTitle = driver.getTitle();
			if (currenTitle.equals(title)) {
				break;
			}

		}
	}

	@AfterClass
	public void afterClass() {
		// Close Browser
		driver.quit();
	}

}
