package seleniumDemo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScreenshotUsingFileHandler 
{
	private WebDriver driver;	
	private String baseURL = "https://www.facebook.com";

	@BeforeMethod
	public void setUp() throws Exception 
	{
		Reporter.log("Inside the setUp() method", true);
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Reporter.log("Launched Firefox Driver & Opened URL -> " + baseURL, true);
	}

	@AfterMethod
	public void tearDown() throws Exception 
	{
		Reporter.log("Inside the tearDown() method", true);
		driver.quit();
		Reporter.log("Quit the Browser", true);
	}
	
	@Test
	public void ScreenshotUsingFileHandlerClassTest()
	{
		String projectPath = System.getProperty("user.dir");
		DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd MMM, yyyy hh-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String fileName = "FileHandlerClassScreenshot " + dtf.format(now) + ".png";
		File destinationFile = new File(projectPath+"\\screenshots\\" + fileName);
		TakesScreenshot srcShot = ((TakesScreenshot)driver);
		File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
		try 
		{
			FileHandler.copy(srcFile, destinationFile);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
