package seleniumDemo;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TakingScreenshotUponFailure 
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
	public void tearDown(ITestResult testResult) throws Exception 
	{
		if(testResult.getStatus() == ITestResult.FAILURE)
		{
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir");
			File destinationFile = new File(path + "\\screenshots\\" + testResult.getName() + ".png");
			try 
			{
				FileUtils.copyFile(srcFile, destinationFile);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		Reporter.log("Inside the tearDown() method", true);
		driver.quit();
		Reporter.log("Quit the Browser", true);
	}
	
	@Test
	public void Test1Method() throws InterruptedException 
	{
		WebElement ele = driver.findElement(By.id("persistent"));
		ele.click();
	}
	
}
