package testcases;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.Constant;
import utilities.DriverFactory;
import utilities.EmailHelper;
import utilities.ExcelReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;


public class Release_2018_01_Test {

	// Creating ExtentReport and ExtentTest reference values
	static ExtentReports report;
	static ExtentTest logger;
	static WebDriver driver = null;
	static ExcelReader excelData = null;

	public static void pause(int period) {
		try {
			Thread.sleep(period);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void beforeClass() {
		driver = DriverFactory.getChromeDriver();
		excelData = new ExcelReader();
		excelData.setExcelFile(Constant.Path_TestData);
		excelData.filstring2indexmap(Constant.Sheet);
		driver.get(Constant.URL1);
		// Create object for Report with filepath
		report = new ExtentReports(Constant.extentReportsPath);
	}

	@AfterClass
	public void afterClass() {
		driver.close();
		driver = null;
	}

	@BeforeTest
	public void startReport() {
		// ExtentReports(String filePath,Boolean replaceExisting)
		// filepath - path of the file, in .htm or .html format - path where your report
		// needs to generate.
		// replaceExisting - Setting to overwrite (TRUE) the existing file or append to
		// it
		// True (default): the file will be replaced with brand new markup, and all
		// existing data will be lost. Use this option to create a brand new report
		// False: existing data will remain, new tests will be appended to the existing
		// report. If the the supplied path does not exist, a new file will be created.
		report = new ExtentReports(Constant.extentReportsPath, true);
		// extent.addSystemInfo("Environment","Environment Name")
		report.addSystemInfo("Host Name", "Mercury Tours Automation").addSystemInfo("Environment", "Automation Testing")
				.addSystemInfo("User Name", "Anonymous");
		// loading the external xml file (i.e., extent-config.xml) which was placed
		// under the base directory
		// You could find the xml file below. Create xml file in your project and copy
		// past the code mentioned below
		report.loadConfig(new File(Constant.extentReportConfigPath));
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		// An implicit wait is to tell WebDriver to poll DOM for a certain amount of
		// time
		pause(6000);

		if (result.getStatus() == ITestResult.SUCCESS) {
			// Pass the test in report
			logger.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		// ending test
		// endTest(logger) : It ends the current test and prepares to create HTML report
		report.endTest(logger);
	}

	@AfterTest
	public void endReport() {
		// writing everything to document
		// flush() - to write or update test information to your report.
		report.flush();
		// Call close() at the very end of your session to clear all resources.
		// If any of your test ended abruptly causing any side-affects (not all logs
		// sent to ExtentReports, information missing), this method will ensure that the
		// test is still appended to the report with a warning message.
		// You should call close() only once, at the very end (in @AfterSuite for
		// example) as it closes the underlying stream.
		// Once this method is called, calling any Extent method will throw an error.
		// close() - To close all the operation
		report.close();
		EmailHelper.sendEmail();
	}

	@Test(priority = 1)
	public void TC001_site_open_positive() {

		// method name as a string
		String method_name = "TC001_site_open_positive";
		String expectedTitle = "";

		// Start the test
		logger = report.startTest(method_name);

		// Log the status in report
		logger.log(LogStatus.INFO, "Opening the site");

		// get data from the excel sheet i.e. TestData.xls
		expectedTitle = excelData.getCellData(Constant.Sheet, Constant.TitleCol, method_name);

		// get page title using selenium driver
		String actualTitle = driver.getTitle();

		// Assert expected against the actual
		Assert.assertEquals(expectedTitle, actualTitle);

	}

	@Test(priority = 2)
	public void TC002_click_RegisterLink_positive() {

		// method name as a string
		String method_name = "TC002_click_RegisterLink_positive";
		// String register_Link = "";
		String register_Page_Title = "";

		// Start the test
		logger = report.startTest(method_name);

		// Log the status in report
		logger.log(LogStatus.INFO, "Clicking Register link");

		// get data from the excel sheet i.e. TestData.xls
		register_Page_Title = excelData.getCellData(Constant.Sheet, Constant.TitleCol, method_name);

		// get page title using selenium driver
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement register = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("REGISTER")));
		register.click();

		// get page title using selenium driver
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);

		// Assert expected against the actual to verify the page has loaded successfully
		// Test Case will fail if the condition fails
		Assert.assertEquals(register_Page_Title, actualTitle);

		System.out.println("Register tab clicked successfully");

	}

	@Test(priority = 3)
	public void TC003_successful_Register() {

		// method name as a string
		String method_name = "TC003_successful_Register";
		// Start the test
		logger = report.startTest(method_name);

		// Log the status in report
		logger.log(LogStatus.INFO, "Registering for user");
		String firstName = "";
		String lastName = "";
		String successfullRegisteredPage = "";
		String phoneNumber = "";
		String emailID = "";
		String address1 = "";
		String address2 = "";
		String city = "";
		String state = "";
		String postalCode = "";
		String country = "";
		String userName = "";
		String password = "";
		String confirmPassword = "";

		// get data from the excel sheet i.e. TestData.xls
		successfullRegisteredPage = excelData.getCellData(Constant.Sheet, Constant.TitleCol, method_name);
		firstName = excelData.getCellData(Constant.Sheet, Constant.FirstNameCol, method_name);
		lastName = excelData.getCellData(Constant.Sheet, Constant.LastNameCol, method_name);
		phoneNumber = excelData.getCellData(Constant.Sheet, Constant.PhoneNumberCol, method_name);
		emailID = excelData.getCellData(Constant.Sheet, Constant.EmailCol, method_name);
		address1 = excelData.getCellData(Constant.Sheet, Constant.Address1Col, method_name);
		address2 = excelData.getCellData(Constant.Sheet, Constant.Address2Col, method_name);
		city = excelData.getCellData(Constant.Sheet, Constant.CityCol, method_name);
		state = excelData.getCellData(Constant.Sheet, Constant.StateCol, method_name);
		postalCode = excelData.getCellData(Constant.Sheet, Constant.PostalCodeCol, method_name);
		country = excelData.getCellData(Constant.Sheet, Constant.CountryCol, method_name);
		userName = excelData.getCellData(Constant.Sheet, Constant.UsernameCol, method_name);
		password = excelData.getCellData(Constant.Sheet, Constant.PasswordCol, method_name);
		confirmPassword = excelData.getCellData(Constant.Sheet, Constant.PasswordCol, method_name);

		// Identifying the elements using selenium driver

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement firstNameTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("firstName")));
		firstNameTextBox.sendKeys(firstName);
		pause(1500);
		WebElement lastNameTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("lastName")));
		lastNameTextBox.sendKeys(lastName);
		pause(1500);
		WebElement phoneNumberTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("phone")));
		phoneNumberTextBox.sendKeys(phoneNumber);
		pause(1500);
		WebElement emailTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("userName")));
		emailTextBox.sendKeys(emailID);
		pause(1500);
		WebElement address1Box = wait.until(ExpectedConditions.elementToBeClickable(By.name("address1")));
		address1Box.sendKeys(address1);
		pause(1500);
		WebElement address2Box = wait.until(ExpectedConditions.elementToBeClickable(By.name("address2")));
		address2Box.sendKeys(address2);
		pause(1500);
		WebElement cityTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("city")));
		cityTextBox.sendKeys(city);
		pause(1500);
		WebElement stateTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("state")));
		stateTextBox.sendKeys(state);
		pause(1500);
		WebElement postalCodeTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("postalCode")));
		postalCodeTextBox.sendKeys(postalCode);
		pause(1500);
		WebElement countryDropDown = wait.until(ExpectedConditions.elementToBeClickable(By.name("country")));
		Select drpCountry = new Select(driver.findElement(By.name("country")));
		drpCountry.selectByValue("234");
		pause(1500);
		WebElement usernameTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
		usernameTextBox.sendKeys(userName);
		pause(1500);
		WebElement passwordTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		passwordTextBox.sendKeys(password);
		pause(1500);
		WebElement confirmpasswordTextBox = wait
				.until(ExpectedConditions.elementToBeClickable(By.name("confirmPassword")));
		confirmpasswordTextBox.sendKeys(confirmPassword);

		pause(5000);
		// WebElement submitBtn =
		// driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td/input"));
		driver.findElement(By.name("register")).click();

		// // get page title using selenium driver
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		//
		// // Assert expected against the actual to verify the page has loaded
		// successfully
		// // Test Case will fail if the condition fails
		Assert.assertEquals(successfullRegisteredPage, actualTitle);

		System.out.println("User Registered successfully");
	}

	@Test(priority = 4)
	public void TC004_click_SignOffLink_positive() {

		// method name as a string
		String method_name = "TC004_click_SignOffLink_positive";
		String sign_off_Link = "";
		String sign_off_Page_Title = "";
		// Start the test
		logger = report.startTest(method_name);

		// Log the status in report
		logger.log(LogStatus.INFO, "Clicking the Sign OFF link");

		// get data from the excel sheet i.e. TestData.xls
		sign_off_Link = excelData.getCellData(Constant.Sheet, Constant.ValidationElementCol, method_name);
		sign_off_Page_Title = excelData.getCellData(Constant.Sheet, Constant.TitleCol, method_name);

		// get page title using selenium driver
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement signOff = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(sign_off_Link)));
		signOff.click();

		// get page title using selenium driver
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);

		// Assert expected against the actual to verify the page has loaded successfully
		// Test Case will fail if the condition fails
		Assert.assertEquals(sign_off_Page_Title, actualTitle);

		System.out.println("Sign Off tab clicked successfully");

	}

	@Test(priority = 5)
	public void TC005_click_SignOnLink_positive() {

		// method name as a string
		String method_name = "TC005_click_SignOnLink_positive";
		// Start the test
		logger = report.startTest(method_name);

		// Log the status in report
		logger.log(LogStatus.INFO, "Clicking the Sign ON link");
		String sign_on_Link = "";
		String sign_on_Page_Title = "";

		// get data from the excel sheet i.e. TestData.xls
		sign_on_Link = excelData.getCellData(Constant.Sheet, Constant.ValidationElementCol, method_name);
		sign_on_Page_Title = excelData.getCellData(Constant.Sheet, Constant.TitleCol, method_name);

		// get page title using selenium driver
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement signOn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(sign_on_Link)));
		signOn.click();

		// get page title using selenium driver
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);

		// Assert expected against the actual to verify the page has loaded successfully
		// Test Case will fail if the condition fails
		Assert.assertEquals(sign_on_Page_Title, actualTitle);

		System.out.println("Sign On tab clicked successfully");

	}

	@Test(priority = 6)
	public void TC006_loginValidUsernamePassword() {

		// method name as a string
		String method_name = "TC006_loginValidUsernamePassword";
		String userName = "";
		String password = "";
		String successfullSignInPage = "";
		// Start the test
		logger = report.startTest(method_name);

		// Log the status in report
		logger.log(LogStatus.INFO, "Logging in with valid username and password");

		// get data from the excel sheet i.e. TestData.xls
		successfullSignInPage = excelData.getCellData(Constant.Sheet, Constant.TitleCol, method_name);
		userName = excelData.getCellData(Constant.Sheet, Constant.UsernameCol, method_name);
		password = excelData.getCellData(Constant.Sheet, Constant.PasswordCol, method_name);

		// Identify the webElement using selenium driver
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement userNameTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("userName")));
		userNameTextBox.sendKeys(userName);
		pause(1500);
		WebElement passwordTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		passwordTextBox.sendKeys(password);

		pause(5000);

		driver.findElement(By.xpath("//input[@name='login']")).click();

		// // get page title using selenium driver
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);

		// // Assert expected against the actual to verify the page has loaded
		// successfully
		// // Test Case will fail if the condition fails
		Assert.assertEquals(successfullSignInPage, actualTitle);

		System.out.println("Logged in successfully");
		pause(5000);

	}

	@Test(priority = 7)
	public void TC007_CompleteFlightBookingSuccessful() {

		// method name as a string
		String method_name = "TC007_CompleteFlightBookingSuccessful";
		String successfulFlightBookedPage = "";
		// Start the test
		logger = report.startTest(method_name);

		// Log the status in report
		logger.log(LogStatus.INFO, "Booking the flight successfully");

		// Getting value from the excel
		successfulFlightBookedPage = excelData.getCellData(Constant.Sheet, Constant.TitleCol, method_name);
		// Identifying the elements using selenium driver
		WebElement flightTypeBtn = driver.findElement(By.xpath("//input[@name='tripType' and @value='roundtrip']"));
		flightTypeBtn.click();
		Select passengerDropDown = new Select(driver.findElement(By.name("passCount")));
		passengerDropDown.selectByValue("1");
		pause(1500);
		Select departingFromDropDown = new Select(driver.findElement(By.name("fromPort")));
		departingFromDropDown.selectByValue("Frankfurt");
		pause(1500);
		Select departingMonthDropDown = new Select(driver.findElement(By.name("fromMonth")));
		departingMonthDropDown.selectByValue("5");
		pause(1500);
		Select departingDateDropDown = new Select(driver.findElement(By.name("fromDay")));
		departingDateDropDown.selectByValue("15");
		pause(1500);
		Select arrivingInDropDown = new Select(driver.findElement(By.name("toPort")));
		arrivingInDropDown.selectByValue("London");
		pause(1500);
		Select returningMonthDropDown = new Select(driver.findElement(By.name("toMonth")));
		returningMonthDropDown.selectByValue("5");
		pause(1500);
		Select returningDateDropDown = new Select(driver.findElement(By.name("toDay")));
		returningDateDropDown.selectByValue("20");
		pause(1500);
		WebElement serviceClassBtn = driver.findElement(By.xpath("//input[@name='servClass' and @value='Business']"));
		serviceClassBtn.click();
		pause(1500);
		WebElement continueBtn = driver.findElement(By.xpath("//input[@name='findFlights' and @type='image']"));
		continueBtn.click();

		// // get page title using selenium driver
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);

		// // Assert expected against the actual to verify the page has loaded
		// successfully
		// // Test Case will fail if the condition fails
		Assert.assertEquals(successfulFlightBookedPage, actualTitle);

		System.out.println("Flight Booked successfully");
	}

	@Test(priority = 8)
	public void TC008_loginInvalidUsername() {

		// method name as a string
		String method_name = "TC008_loginInvalidUsername";
		String userName = "";
		String password = "";
		String sign_on_Link = "";
		String sign_off_Link = "";

		// Start the test
		logger = report.startTest(method_name);

		// Log the status in report
		logger.log(LogStatus.INFO, "Entering Invalid username failed Test");

		// get data from the excel sheet i.e. TestData.xls
		sign_off_Link = excelData.getCellData(Constant.Sheet, Constant.ValidationElementNewCol, method_name);
		sign_on_Link = excelData.getCellData(Constant.Sheet, Constant.ValidationElementCol, method_name);
		userName = excelData.getCellData(Constant.Sheet, Constant.UsernameCol, method_name);
		password = excelData.getCellData(Constant.Sheet, Constant.PasswordCol, method_name);

		// Identify the webElement using selenium driver
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement signOff = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(sign_off_Link)));
		signOff.click();
		pause(6000);
		WebElement signOn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(sign_on_Link)));
		signOn.click();
		pause(6000);
		WebElement userNameTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("userName")));
		userNameTextBox.sendKeys(userName);
		pause(1500);
		WebElement passwordTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		passwordTextBox.sendKeys(password);

		pause(5000);

		driver.findElement(By.xpath("//input[@name='login']")).click();

		Boolean isPresent = driver.findElements(By.xpath("//input[@name='tripType' and @value='roundtrip']"))
				.size() > 0;

		/*
		 * Assert expected against the actual to verify the page has loaded successfully
		 * Test Case will fail if the condition fails i.e with invalid username if the
		 * flight booking page opens successfully, this test case will fail
		 */
		Assert.assertTrue(!isPresent);

		System.out.println("Login failed with invalid username");
		pause(5000);

	}

	@Test(priority = 9)
	public void TC009_loginInvalidPassword() {

		// method name as a string
		String method_name = "TC009_loginInvalidPassword";
		String userName = "";
		String password = "";
		String sign_off_Link = "";
		String sign_on_Link = "";
		// Start the test
		logger = report.startTest(method_name);

		// Log the status in report
		logger.log(LogStatus.INFO, "Entering Invalid password failed Test");

		// get data from the excel sheet i.e. TestData.xls
		sign_off_Link = excelData.getCellData(Constant.Sheet, Constant.ValidationElementNewCol, method_name);
		sign_on_Link = excelData.getCellData(Constant.Sheet, Constant.ValidationElementCol, method_name);
		userName = excelData.getCellData(Constant.Sheet, Constant.UsernameCol, method_name);
		password = excelData.getCellData(Constant.Sheet, Constant.PasswordCol, method_name);

		// Identify the webElement using selenium driver
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement signOff = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(sign_off_Link)));
		signOff.click();
		pause(6000);
		WebElement signOn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(sign_on_Link)));
		signOn.click();
		pause(6000);
		WebElement userNameTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("userName")));
		userNameTextBox.sendKeys(userName);
		pause(1500);
		WebElement passwordTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		passwordTextBox.sendKeys(password);

		pause(5000);

		driver.findElement(By.xpath("//input[@name='login']")).click();

		Boolean isPresent = driver.findElements(By.xpath("//input[@name='tripType' and @value='roundtrip']"))
				.size() > 0;

		/*
		 * Assert expected against the actual to verify the page has loaded successfully
		 * Test Case will fail if the condition fails i.e with invalid username if the
		 * flight booking page opens successfully, this test case will fail
		 */
		Assert.assertTrue(isPresent);

		System.out.println("Log in failed with invalid password");
		pause(5000);

	}

}
