package seleniumDemo;

import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DisableBrowserNotifications 
{
	
	@Test
	public void TestBrowserNotificatin()
	{
		WebDriver driver;	
		 String baseURL = "https://www.yatra.com/bus-booking";
	 	WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(baseURL);
		driver.manage().deleteAllCookies();
		Reporter.log("Launched Chrome Driver & Opened URL -> " + baseURL, true);
	}

}
