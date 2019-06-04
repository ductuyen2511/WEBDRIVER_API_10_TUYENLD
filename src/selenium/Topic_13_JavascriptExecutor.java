package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_JavascriptExecutor {
	WebDriver driver;
	JavascriptExecutor js;
	String userName, passWord;
	String customerName, gender, dateOfBirth, address, city, state, pin, phone, email, password;

	By customerNameTextbox = By.xpath("//input[@name ='name']");
	By genderRadioButton = By.xpath("//input[@value = 'm']");
	By dateOfBirthTextbox = By.xpath("//input[@name = 'dob']");
	By addressTextarea = By.xpath("//textarea[@name = 'addr']");
	By cityTextbox = By.xpath("//input[@name = 'city']");
	By stateTextbox = By.xpath("//input[@name = 'state']");
	By pinTextbox = By.xpath("//input[@name = 'pinno']");
	By phoneTextbox = By.xpath("//input[@name = 'telephoneno']");
	By emailTextbox = By.xpath("//input[@name = 'emailid']");
	By passwordTextbox = By.xpath("//input[@name = 'password']");
	By submitButton = By.xpath("//input[@name = 'sub']");

	By customerNameRow = By.xpath("//td[text() = 'Customer Name']/following-sibling::td");
	By genderRow = By.xpath("//td[text() = 'Gender']/following-sibling::td");
	By dateOfBirthRow = By.xpath("//td[text() = 'Birthdate']/following-sibling::td");
	By addressRow = By.xpath("//td[text() = 'Address']/following-sibling::td");
	By cityRow = By.xpath("//td[text() = 'City']/following-sibling::td");
	By stateRow = By.xpath("//td[text() = 'State']/following-sibling::td");
	By pinRow = By.xpath("//td[text() = 'Pin']/following-sibling::td");
	By phoneRow = By.xpath("//td[text() = 'Mobile No.']/following-sibling::td");
	By emailRow = By.xpath("//td[text() = 'Email']/following-sibling::td");

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;

		userName = "mngr198775";
		passWord = "yqUvaha";
		customerName = "Le Duc Tuyen";
		gender = "male";
		dateOfBirth = "1992-01-01";
		address = "123 Tao Đàn";
		city = "HCM";
		state = "District";
		pin = "555666";
		phone = "0989777888";
		email = "ductuyen" + emailRandom() + "@gmail.com";
		password = "123456789";

	}

	public void TC_01_JavascriptExecutor() {
		driver.get("http://live.guru99.com/");

		// Domain
		String domain = (String) js.executeScript("return document.domain");
		System.out.println("Domain :" + domain);
		Assert.assertEquals(domain, "live.guru99.com");

		// URL
		String url = (String) js.executeScript("return document.URL");
		System.out.println("URL :" + url);
		Assert.assertEquals(url, "http://live.guru99.com/");

		WebElement mobilePage = driver.findElement(By.xpath("//a[text() ='Mobile']"));

		js.executeScript("arguments[0].click();", mobilePage);

		WebElement samsung = driver.findElement(By.xpath("//a[text() ='Samsung Galaxy']/parent::h2/following-sibling::div[@class = 'actions']/button"));
		js.executeScript("arguments[0].click();", samsung);
		String sText = js.executeScript("return document.documentElement.innerText.match('Samsung Galaxy was added to your shopping cart.')[0]").toString();
		System.out.println("Message :" + sText);
		Assert.assertEquals(sText, "Samsung Galaxy was added to your shopping cart.");

		// Private policy
		WebElement privatePolicy = driver.findElement(By.xpath("//a[text() = 'Privacy Policy']"));
		js.executeScript("arguments[0].click();", privatePolicy);
		String titlePolicy = (String) js.executeScript("return document.title");
		System.out.println("title policy:" + titlePolicy);
		Assert.assertEquals(titlePolicy, "Privacy Policy");

		// Verify by scrolling
		js.executeScript("window.scrollBy(0,250)");
		WebElement verify = driver.findElement(By.xpath("//th[text() = 'WISHLIST_CNT']/following-sibling::td"));
		System.out.println("Text scrolling :" + verify.getText());
		Assert.assertEquals(verify.getText(), "The number of items in your Wishlist.");

		// Navigate
		js.executeScript("window.location = 'http://demo.guru99.com/v4/'");
		String navigateDomain = (String) js.executeScript("return document.domain");
		System.out.println("Navigate domain :" + navigateDomain);
		Assert.assertEquals(navigateDomain, "demo.guru99.com");

	}

	public void TC_02_RemoveAttribute() {
		driver.get("http://demo.guru99.com/v4");

		driver.findElement(By.xpath("//input[@name ='uid']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name ='password']")).sendKeys(passWord);
		driver.findElement(By.xpath("//input[@name ='btnLogin']")).click();

		driver.findElement(By.xpath("//a[text() ='New Customer']")).click();
		driver.findElement(customerNameTextbox).sendKeys(customerName);
		driver.findElement(genderRadioButton).sendKeys(gender);

		// js.executeScript("arguments[0].removeAttribute('type');",
		// dateOfBirthTextbox);
		removeAttribute(driver.findElement(dateOfBirthTextbox), "type");
		driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirth);

		driver.findElement(addressTextarea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);

		driver.findElement(submitButton).click();

		Assert.assertEquals(driver.findElement(customerNameRow).getText(), customerName);
		Assert.assertEquals(driver.findElement(genderRow).getText(), gender);
		Assert.assertEquals(driver.findElement(dateOfBirthRow).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(addressRow).getText(), address);
		Assert.assertEquals(driver.findElement(cityRow).getText(), city);
		Assert.assertEquals(driver.findElement(stateRow).getText(), state);
		Assert.assertEquals(driver.findElement(pinRow).getText(), pin);
		Assert.assertEquals(driver.findElement(phoneRow).getText(), phone);
		Assert.assertEquals(driver.findElement(emailRow).getText(), email);

	}

	@Test
	public void TC_03_CreateAnAccount() throws Exception {
		js.executeScript("window.location = 'http://live.guru99.com/'");

		WebElement myAcountLink = driver.findElement(By.xpath("//a[text() = 'My Account']"));
		js.executeScript("arguments[0].click();", myAcountLink);

		WebElement createAccountButton = driver.findElement(By.xpath("//a[@title = 'Create an Account']"));
		js.executeScript("arguments[0].click();", createAccountButton);

		senkeyToElementByJS(driver.findElement(By.xpath("//input[@id = 'firstname']")), "Le");
		senkeyToElementByJS(driver.findElement(By.xpath("//input[@id = 'middlename']")), "Duc");
		senkeyToElementByJS(driver.findElement(By.xpath("//input[@id = 'lastname']")), "Tuyen");
		senkeyToElementByJS(driver.findElement(By.xpath("//label[@for = 'email_address']/following-sibling::div/input[@type = 'email']")), email);
		senkeyToElementByJS(driver.findElement(By.xpath("//input[@title = 'Password']")), "tuyen123");
		senkeyToElementByJS(driver.findElement(By.xpath("//input[@title = 'Confirm Password']")), "tuyen123");

		WebElement checkboxClick = driver.findElement(By.xpath("//input[@type='checkbox']"));
		js.executeScript("arguments[0].click();", checkboxClick);
		WebElement registerButton = driver.findElement(By.xpath("//button[@title ='Register']"));
		js.executeScript("arguments[0].click();", registerButton);
		Thread.sleep(3000);

		String sTextComplete = js.executeScript("return document.documentElement.innerText.match('Thank you for registering with Main Website Store.')[0]").toString();
		Assert.assertEquals(sTextComplete, "Thank you for registering with Main Website Store.");

		WebElement accountclick = driver.findElement(By.xpath("//span[@class= 'icon']/following-sibling::span[text() = 'Account']"));
		js.executeScript("arguments[0].click();", accountclick);

		WebElement logoutClick = driver.findElement(By.xpath("//a[@title = 'Log Out']"));
		js.executeScript("arguments[0].click();", logoutClick);
		Thread.sleep(10000);
		String homepage = (String) js.executeScript("return document.title");
		Assert.assertEquals(homepage, "Home page");
	}

	public int emailRandom() {
		Random random = new Random();
		return random.nextInt(999999);
	}

	public Object removeAttribute(WebElement element, String attribute) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	}

	public Object senkeyToElementByJS(WebElement element, String value) {
		return js.executeScript("arguments[0].setAttribute('value','" + value + "')", element);
	}

	// Post-condition
	@AfterClass
	public void afterClass() {
		// Close Browser
		driver.quit();
	}
}
