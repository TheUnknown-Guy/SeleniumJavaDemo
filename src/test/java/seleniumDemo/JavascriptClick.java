package seleniumDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavascriptClick 
{
		private WebDriver driver;	
		private String baseURL = "https://login.yahoo.com/";

		@BeforeMethod
		public void setUp() throws Exception 
		{
			Reporter.log("Inside the setUp() method", true);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(baseURL);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
		public void Test1Method() throws InterruptedException 
		{
			WebElement ele = driver.findElement(By.id("persistent"));
			//org.openqa.selenium.ElementNotInteractableException: element not interactable
			ele.click();
			//use below code for proper click
			JavascriptExecutor js = ((JavascriptExecutor)driver);
			js.executeScript("arguments[0].click();", ele);
		}

}
