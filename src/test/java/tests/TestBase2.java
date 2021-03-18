package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import data.LoadProperties;



public class TestBase2 {

	// Sauce Labs Configuration 
	public static final String USERNAME = LoadProperties.sauceLabsData.getProperty("username"); 
	public static final String ACCESS_KEY = LoadProperties.sauceLabsData.getProperty("accesskey"); 
	public static final String sauceURL = "https://"+USERNAME+":"+ACCESS_KEY
	+LoadProperties.sauceLabsData.getProperty("seleniumURL"); 
	
	public static String BaseURL = "http://demo.nopcommerce.com" ; 

	protected ThreadLocal<RemoteWebDriver> driver = null ; 

	@BeforeClass
	@Parameters(value= {"platformName", "browser", "browserVersion"})
	public void setUp(@Optional("chrome") String platformName, String browser, String browserVersion  ) throws MalformedURLException 
	{
		driver = new ThreadLocal<>(); 
		DesiredCapabilities caps = new DesiredCapabilities(); 
		caps.setCapability("platformName", platformName);
		caps.setCapability("browserName", browser);
		caps.setCapability("browserVersion", browserVersion);
		
		
		
		
		// Selenium Grid Local
		// driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps));
		
		// Run on SauceLabs on Cloud
		driver.set(new RemoteWebDriver(new URL(sauceURL),caps));
		
		getDriver().navigate().to(BaseURL);
	}

	public WebDriver getDriver() 
	{
		return driver.get(); 
	}

	@AfterClass
	public void stopDriver() 
	{
		getDriver().quit();
		driver.remove();
	}

}
