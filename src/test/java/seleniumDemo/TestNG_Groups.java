package seleniumDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNG_Groups 
{
	private WebDriver driver;	
	private String baseURL = "https://www.demoqa.com/tool-tips";

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception 
	{
		Reporter.log("Inside the setUp() method", true);
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		Reporter.log("Launched Firefox Driver & Opened URL -> " + baseURL, true);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() 
	{
		Reporter.log("Inside the tearDown() method", true);
		driver.quit();
		Reporter.log("Quit the Browser", true);
	}
	
	@Test(groups = {"Sanity"})
	public void TestMethod1()
	{
		String title = driver.getTitle();
		Reporter.log("Inside Test Method - 1 | Page title is -> " + title );
	}
	@Test(groups = {"Sanity", "Smoke"})
	public void TestMethod2()
	{
		String title = driver.getTitle();
		Reporter.log("Inside Test Method - 2 | Page title is -> " + title );
	}
	@Test(groups = {"Sanity", "Smoke"})
	public void TestMethod3()
	{
		String title = driver.getTitle();
		Reporter.log("Inside Test Method - 3 | Page title is -> " + title );
	}
	@Test(groups = {"Regression"})
	public void TestMethod4()
	{
		String title = driver.getTitle();
		Reporter.log("Inside Test Method - 4 | Page title is -> " + title );
	}
	@Test(groups = {"Recurring"}, invocationCount = 5, invocationTimeOut = 10000)
	public void TestMethod5()
	{
		String title = driver.getTitle();
		Reporter.log("Inside Test Method - 5 | Page title is -> " + title );
	}
	@Test(groups = {"Regression", "Smoke", "Sanity"})
	public void TestMethod6()
	{
		String title = driver.getTitle();
		Reporter.log("Inside Test Method - 6 | Page title is -> " + title );
	}

}
