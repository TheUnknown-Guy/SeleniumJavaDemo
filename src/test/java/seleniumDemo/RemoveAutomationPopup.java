package seleniumDemo;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RemoveAutomationPopup 
{

	private WebDriver driver;	
	private String baseURL = "https://login.yahoo.com/";

	@BeforeMethod
	public void setUp() throws Exception 
	{
		Reporter.log("Inside the setUp() method", true);
		
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		driver = new EdgeDriver(options);
		
		/*
		 WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("disable-infobars");
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		driver = new ChromeDriver(options);
		*/
		driver.manage().window().maximize();
		driver.get(baseURL);
		driver.manage().deleteAllCookies();
		Reporter.log("Launched Chrome Driver & Opened URL -> " + baseURL, true);
	}

	@AfterMethod
	public void tearDown() throws Exception 
	{
		Reporter.log("Inside the tearDown() method", true);
		Thread.sleep(2000);
		driver.quit();
		Reporter.log("Quit the Browser", true);
	}
	
	@Test
	public void TestMethod1()
	{
		
	}
}
