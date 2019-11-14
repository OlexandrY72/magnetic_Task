package tests;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import utils.BrowserUtils;
import utils.ConfigurationReader;
import utils.WebDriverUtils;

public class RegistrationPage_tests extends BrowserUtils {
	
	private WebDriver driver;
	
	@BeforeSuite
	public void setUp() {
		driver = WebDriverUtils.getDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void testRegistrationFunctionality_Positive() {
		int expectedWaitTime = 10;
		String expectedTitle = ConfigurationReader.getProperty("expectedHomePageTitle");
		// navigating to the homepage
		System.out.println("INFO -------------> Navigating to the homepage");
		driver.get(ConfigurationReader.getProperty("homePageURL"));
		
		// waiting for the logo to load
		waitForVisibility(driver.findElement(By.xpath("//header[@class='banner navbar navbar-default navbar-static-top']//div[@id='logo']")), driver, expectedWaitTime);   // error   //header[@class='banner navbar navbar-default navbar-static-top']//div[@id='logo']
		
		// capturing and comparing the title
		String actualTitle = driver.getTitle();
		System.out.println("INFO -------------> Capturing the title from the page: " + actualTitle);
		Assert.assertTrue(actualTitle.contains(expectedTitle));
		System.out.println("INFO -------------> Compared with our expected title: " + expectedTitle);
		
		// removing pop up
		driver.findElement(By.id("cn-accept-cookie")).click();
		// capturing and going to the registration form
		WebElement registrationButton = driver.findElement(By.xpath("//header[@class='banner navbar navbar-default navbar-static-top']//a[@title='Start your Yaware.TimeManager 14-days FREE TRIAL now!']")); // error  //header[@class='banner navbar navbar-default navbar-static-top']//a[@title='Start your Yaware.TimeManager 14-days FREE TRIAL now!']
		System.out.println("INFO -------------> Waiting for button to become clickable");
		waitForClickability(registrationButton, driver, expectedWaitTime);
		System.out.println("INFO -------------> Clicking on the Register button");
		registrationButton.click();
		
		// verifying if pop up with registration is displayed
		WebElement registrationForm = driver.findElement(By.id("main-register-form")); 
		waitForVisibility(registrationForm, driver, expectedWaitTime);
		
		if(registrationForm.isDisplayed()) {
			
			// entering the data for the First and Last name field
			String firstName = ConfigurationReader.getProperty("firstName");
			String lastName = ConfigurationReader.getProperty("lastName");
			System.out.println("INFO -------------> Got the first name - " + firstName + "\nand the last name - " + lastName);
			enterDataIntoField(firstName + " " + lastName , driver.findElement(By.id("firstname")), expectedWaitTime);
			
			// entering the data for the Email field
			String userID = ConfigurationReader.getProperty("user_ID") + new Random().nextInt(999);
			String emailExtention = ConfigurationReader.getProperty("email_extention");
			System.out.println("INFO -------------> Got the email - " + userID + emailExtention);
			enterDataIntoField(userID + emailExtention, driver.findElement(By.id("registerEmail")), expectedWaitTime);
			
			// entering the data for password field
			String password = ConfigurationReader.getProperty("password");
			System.out.println("INFO -------------> Got the password - " + password);
			enterDataIntoField(password, driver.findElement(By.id("pwd1")), expectedWaitTime);  
			
			// enter the data for the phone field
			String phoneNumber = ConfigurationReader.getProperty("phoneNumber");
			System.out.println("INFO -------------> Got the phone number - " + phoneNumber);
			enterDataIntoField(phoneNumber, driver.findElement(By.id("phone")), expectedWaitTime);
			
			// checking the checkbox
			System.out.println("INFO -------------> Checking the checkbox.");
			driver.findElement(By.name("agreement-check")).click();     // agreement-check on English site
			
			// clicking on register button
			WebElement submitButton = driver.findElement(By.xpath("//div[@class='reg-button']//input[@title='Sign Up']"));
			System.out.println("INFO -------------> Waiting for button to become clickable");
			waitForClickability(submitButton, driver, expectedWaitTime);
			System.out.println("INFO -------------> Clicking on Register button");
			submitButton.click();
			 
			// waiting for a welcome header
			waitForVisibility(driver.findElement(By.xpath("//header[@class='yaware-cjm__title']")), driver, expectedWaitTime * 2);
			System.out.println("INFO -------------> Got the Welcome Page");
		} else {
			System.out.println("WARNING -------------> Registration Form is not displayed");
			throw new NoSuchElementException();
		}
		
	}
	
	public void enterDataIntoField(String data, WebElement field, int waitTime) {
		waitForVisibility(field, driver, waitTime);
		System.out.println("INFO -------------> Entering the data: "+ data +" into the field");
		field.sendKeys(data);
	}
	
	@Test
	public void testLoginFunctionality_Negative() {
		int expectedWaitTime = 10;
		String expectedTitle = ConfigurationReader.getProperty("expectedHomePageTitle");
		// navigating to the homepage
		System.out.println("INFO -------------> Navigating to the homepage");
		driver.get(ConfigurationReader.getProperty("homePageURL"));
		
		// waiting for the logo to load
		waitForVisibility(driver.findElement(By.xpath("//header[@class='banner navbar navbar-default navbar-static-top']//div[@id='logo']")), driver, expectedWaitTime);   
		
		// capturing and comparing the title
		String actualTitle = driver.getTitle();
		System.out.println("INFO -------------> Capturing the title from the page: " + actualTitle);
		Assert.assertTrue(actualTitle.contains(expectedTitle));
		System.out.println("INFO -------------> Compared with our expected title: " + expectedTitle);
		
		// capturing and going to the login form
		WebElement LoginButton = driver.findElement(By.xpath("//header[@class='banner navbar navbar-default navbar-static-top']//a[contains(text(),'Login')]"));
		System.out.println("INFO -------------> Waiting for button to become clickable");
		waitForClickability(LoginButton, driver, expectedWaitTime);
		System.out.println("INFO -------------> Clicking on the Register button");
		LoginButton.click();
		
		// verifying if pop up with login is displayed
		WebElement loginForm = driver.findElement(By.id("main-login-form")); 
		waitForVisibility(loginForm, driver, expectedWaitTime);
		
		if(loginForm.isDisplayed()) {
			
			// entering the data for the userEmail
			String userEmail = ConfigurationReader.getProperty("invalidUserEmail");
			System.out.println("INFO -------------> Got the user_mail - " + userEmail);
			enterDataIntoField(userEmail, driver.findElement(By.id("email")), expectedWaitTime);
			
			// entering the data for password field
			String password = ConfigurationReader.getProperty("invalidUserPassword");
			System.out.println("INFO -------------> Got the password - " + password);
			enterDataIntoField(password, driver.findElement(By.id("password")), expectedWaitTime);  
			
			// clicking on login button
			System.out.println("INFO -------------> Clicking on Login button");
			driver.findElement(By.xpath("//div[@id='main-login-form']//input[@class='s-button button']")).click();
			 
			// waiting for a error message
			String expectedErrorMessage = ConfigurationReader.getProperty("expectedErrorMessage");
			String actualErrorMessage = driver.findElement(By.xpath("//div[@class='f-hint error']//span[@class='error-message']")).getText();
			Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
			System.out.println("INFO -------------> Got the Error message");
		} else {
			System.out.println("WARNING -------------> Login Form is not displayed");
			throw new NoSuchElementException();
		}
	}
}