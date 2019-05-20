package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_TextboxAndTextarea {
	WebDriver driver;
	String customerID;
	String customerName, gender, dateOfBirth, address, city, state, pin, phone, email, passowrd;
	String addressEdit,cityEdit,stateEdit, pinEdit, phoneEdit;
	String username, password;

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
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Set value
		username = "mngr139454";
		password ="qwerty1!";
		customerName = "Automation test";
		gender = "male";
		dateOfBirth = "1992-01-01";
		address = "123 Ta di xa";
		city = "Sai Gon";
		state = "Gia Dinh";
		pin = "123123";
		phone = "0909123123";
		email = "autotest" + randomEmail() + "@gmail.com";
		passowrd = "123123";
		
		//Edit
		addressEdit = "1109 Boston";
		cityEdit = "New York";
		stateEdit = "California";
		pinEdit = "456456";
		phoneEdit = "0909999999";
		email = "autotest" + randomEmail() + "@gmail.com";
		
	}

	// Action of Testcase
	@Test
	public void TC_01_CheckUrl() {
		// Check url is correct
		driver.findElement(By.xpath("//input[@name ='uid']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name ='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name ='btnLogin']")).click();
		
		driver.findElement(By.xpath("//a[text() ='New Customer']")).click();
		driver.findElement(customerNameTextbox).sendKeys(customerName);
		driver.findElement(genderRadioButton).sendKeys(gender);
		driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirth);
		driver.findElement(addressTextarea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(passowrd);
		driver.findElement(submitButton).click();

		// get customerID
		customerID = driver.findElement(By.xpath("//td[text() = 'Customer ID']/following-sibling::td")).getText();
		System.out.println("Customer ID is " + customerID);

		// verify
		Assert.assertEquals(driver.findElement(customerNameRow).getText(), customerName);
		Assert.assertEquals(driver.findElement(genderRow).getText(), gender);
		Assert.assertEquals(driver.findElement(dateOfBirthRow).getText(),dateOfBirth);
		Assert.assertEquals(driver.findElement(addressRow).getText(),address);
		Assert.assertEquals(driver.findElement(cityRow).getText(), city);
		Assert.assertEquals(driver.findElement(stateRow).getText(), state);
		Assert.assertEquals(driver.findElement(pinRow).getText(), pin);
		Assert.assertEquals(driver.findElement(phoneRow).getText(), phone);
		Assert.assertEquals(driver.findElement(emailRow).getText(), email);
		
		// Edit customer ID
		driver.findElement(By.xpath("//a[text() = 'Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name = 'cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name = 'AccSubmit']")).click();
		
		By editCustomerNameRow = By.xpath("//input[@name = 'name']");
		By editCustomerAddressRow = By.xpath("//textarea[@name = 'addr']");

		
		Assert.assertEquals(driver.findElement(editCustomerNameRow).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(editCustomerAddressRow).getText(), address);
		
		driver.findElement(addressTextarea).clear();
		driver.findElement(cityTextbox).clear();
		driver.findElement(stateTextbox).clear();
		driver.findElement(pinTextbox).clear();
		driver.findElement(phoneTextbox).clear();
		driver.findElement(emailTextbox).clear();
		
		driver.findElement(addressTextarea).sendKeys(addressEdit);
		driver.findElement(cityTextbox).sendKeys(cityEdit);
		driver.findElement(stateTextbox).sendKeys(stateEdit);
		driver.findElement(pinTextbox).sendKeys(pinEdit);
		driver.findElement(phoneTextbox).sendKeys(phoneEdit);
		driver.findElement(emailTextbox).sendKeys(email);
		
		driver.findElement(By.xpath("//input[@value = 'Submit']")).click();
		
	}

	public void TC_02_CheckTitle() {

	}

	public void TC_03_CheckTitle() {

	}

	// Post-condition
	@AfterClass
	/*public void afterClass() {
		// Close Browser
		driver.quit();
	}*/
	public int randomEmail() {
		Random random = new Random();
		return random.nextInt(9999999);
	}


}
