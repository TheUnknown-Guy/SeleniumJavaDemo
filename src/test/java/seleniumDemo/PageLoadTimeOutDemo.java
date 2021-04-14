package seleniumDemo;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PageLoadTimeOutDemo 
{
	@Test
	public void PageLoadTimeOutDemoTest() throws InterruptedException
	{
		WebDriver driver;	
		String baseURL = "https://www.salesforce.com";
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		int width = 800;
		int height = 1000;
		Dimension dimension = new Dimension(width, height);
		driver.manage().window().setSize(dimension);
		driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
		try 
		{
			driver.get(baseURL);
		}
		catch(TimeoutException e)
		{
			System.out.println(e.getMessage());
		}
		
		Reporter.log("Launched Firefox Driver & Opened URL -> " + baseURL, true);
		Thread.sleep(2000);
		driver.quit();
	}


}
