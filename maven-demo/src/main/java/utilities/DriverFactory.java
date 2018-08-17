package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import utilities.Constant;
public class DriverFactory {
	 
	private static  WebDriver driver;
	
	public static WebDriver getFirefoxDriver() {
		// TODO: change to relative path
		System.setProperty("webdriver.gecko.driver", Constant.firefoxPath);
		driver = new FirefoxDriver();
		return driver;
	}
	
	public static WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", Constant.chromePath);
		driver = new ChromeDriver();
		return driver;
	}
	
	public static WebDriver getIEDriver() {
		driver = new InternetExplorerDriver();
		return driver;
	}
}
