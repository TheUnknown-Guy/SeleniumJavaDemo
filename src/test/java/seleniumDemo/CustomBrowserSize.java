package seleniumDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomBrowserSize 
{
	private WebDriver driver;	
	private String baseURL = "https://demoqa.com/alerts";

	@Test
	public void TestBrowserSizeCustom() throws InterruptedException
	{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		int width = 800;
		int height = 1000;
		Dimension dimension = new Dimension(width, height);
		driver.manage().window().setSize(dimension);
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		Reporter.log("Launched Firefox Driver & Opened URL -> " + baseURL, true);
		Thread.sleep(2000);
		driver.quit();
	}

}