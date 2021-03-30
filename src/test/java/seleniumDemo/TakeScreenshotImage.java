package seleniumDemo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TakeScreenshotImage 
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
	public void ScreenshotTestMethod()
	{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM, yyyy hh-mm-ss");
			LocalDateTime now = LocalDateTime.now();
			String fileName = dtf.format(now);
			System.out.println(fileName);
			String projectPath = System.getProperty("user.dir");
			String title = driver.getTitle();
			Reporter.log("Title of current page is --> " + title, true);
			TakesScreenshot srcShot = ((TakesScreenshot)driver);
			File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
			File destinationFile = new File(projectPath+"\\screenshots\\" + fileName + ".png");
			try 
			{
				FileUtils.copyFile(srcFile, destinationFile);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
	}

}
