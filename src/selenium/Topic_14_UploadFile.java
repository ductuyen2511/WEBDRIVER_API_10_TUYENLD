package selenium;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_UploadFile {
	WebDriver driver;

	String rootFolder = System.getProperty("user.dir");

	String fileName01 = "Image01.jpg";
	String fileName02 = "Image02.jpg";
	String fileName03 = "Image03.jpg";

	String fileNamePath01 = rootFolder + "\\UploadFiles\\" + fileName01;
	String fileNamePath02 = rootFolder + "\\UploadFiles\\" + fileName02;
	String fileNamePath03 = rootFolder + "\\UploadFiles\\" + fileName03;

	String chormePath, fifefoxPath;

	@BeforeClass
	public void beforeClass() {
		// driver = new FirefoxDriver();
		// System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		// driver = new ChromeDriver();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		chormePath = rootFolder + "\\UploadFiles\\chrome.exe";
		fifefoxPath = rootFolder + "\\UploadFiles\\firefox.exe";
	}

	public void TC_01_UploadFile() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		/*
		 * for (String file : files) { WebElement uploadFile =
		 * driver.findElement(By.xpath("//input[@type = 'file']"));
		 * uploadFile.sendKeys(file); }
		 */
		// driver.findElement(By.xpath("//span[text() = 'Start upload']")).click();

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(fileNamePath01 + "\n" + fileNamePath02 + "\n" + fileNamePath03);
		Thread.sleep(2000);

		List<WebElement> startButtons = driver.findElements(By.xpath("//tbody[@class='files']//button[@class='btn btn-primary start']"));
		System.out.println(startButtons.size());

		for (WebElement statButton : startButtons) {
			statButton.click();
			Thread.sleep(2000);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text() ='" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() ='" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() ='" + fileName03 + "']")).isDisplayed());
	}

	public void Tc_02_AutoIT() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement uploadChrome = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadChrome.click();
		Thread.sleep(2000);

		if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { chormePath, fileNamePath01 });
		} else {
			Runtime.getRuntime().exec(new String[] { fifefoxPath, fileNamePath01 });
		}

		Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class= 'files']//p[@class = 'name' and text() = 'Image01.jpg']")).isDisplayed());
	}

	public void Tc_03_uploadByRobot() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement uploadChrome = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadChrome.click();
		Thread.sleep(2000);

		StringSelection select = new StringSelection(fileNamePath01);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		Robot robot = new Robot();
		Thread.sleep(2000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	@Test
	public void Tc_04_uploadFileRegister() throws Exception {
		driver.get("https://encodable.com/uploaddemo/");

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type = 'file']"));
		uploadFile.sendKeys(fileNamePath01);
		Thread.sleep(2000);

		WebElement subfolderName = driver.findElement(By.xpath("//input[@id= 'newsubdir1']"));
		String nameFolder = "seleniumonline" + randomSubFolder();
		subfolderName.sendKeys(nameFolder);
		Thread.sleep(2000);

		WebElement emailAddress = driver.findElement(By.xpath("//input[@name = 'email_address']"));
		emailAddress.sendKeys("ductuyen@gmail.com");
		Thread.sleep(2000);

		WebElement firstName = driver.findElement(By.xpath("//input[@name = 'first_name']"));
		firstName.sendKeys("Tuyen Le");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@type = 'button']")).click();
		Thread.sleep(3000);

		// Assert
		String[] emailVerify = driver.findElement(By.xpath("//dd[text() = 'Email Address: ductuyen@gmail.com']")).getText().split(": ");
		String email = emailVerify[2];
		System.out.println(email);
		Assert.assertEquals(email, "ductuyen@gmail.com");

		String[] nameVerify = driver.findElement(By.xpath("//dd[text() = 'First Name: Tuyen Le']")).getText().split(" ");
		String name1 = nameVerify[2];
		String name2 = nameVerify[3];
		System.out.println(name1 + " " + name2);
		Assert.assertEquals(name1 + " " + name2, "Tuyen Le");

		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName01 + "']")).isDisplayed());

		driver.findElement(By.xpath("//a[text() = 'View Uploaded Files']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[starts-with(text(), 'seleniumonline')]")).click();
		Thread.sleep(3000);
		String splitURL = driver.getCurrentUrl().split("=")[2];
		System.out.println("split URL = " + splitURL);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName01 + "']")).isDisplayed());

		driver.findElement(By.xpath("//a[@class ='thumb']//img[@class = 'fcthumb']")).click();
		Thread.sleep(2000);
		String urlExpected = "https://encodable.com/uploaddemo/?action=viewer&path" + splitURL + "/&file=" + fileName01;
		System.out.println("url Expected = " + urlExpected);
		System.out.println("current URL  = " + driver.getCurrentUrl());
		Assert.assertEquals("https://encodable.com/uploaddemo/?action=viewer&path=" + splitURL + "/&file=" + fileName01, driver.getCurrentUrl());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName01 + "']")).isDisplayed());

	}

	public int randomSubFolder() {
		Random random = new Random();
		return random.nextInt(9999999);
	}

	@AfterClass
	public void afterClass() {
		// Close Browser
		driver.quit();
	}
}
