package seleniumDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNG_Parameter 
{
	private WebDriver driver;	
	private String baseURL = "https://demoqa.com/alerts";

	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) throws Exception 
	{
		Reporter.log("Inside the setUp() method", true);
		if(browser.equals("chrome"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();;
			driver = new EdgeDriver();
		}
		else
		{
			Reporter.log("Please provide the correct browser name", true);
		}
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
	
	@Test
	public void AlertMethodTestExample()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//handling simple alert
		driver.findElement(By.id("alertButton")).click();
		driver.switchTo().alert().accept();
		//handling alert which appears after few seconds
		driver.findElement(By.id("timerAlertButton")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		//handling confirmation alert
		//accepting the alert
		driver.findElement(By.id("confirmButton")).click();
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirmResult")));
		String confirmAlertResult1 = driver.findElement(By.id("confirmResult")).getText();
		Reporter.log("On the Confirm Alert, user has selected -> " + confirmAlertResult1, true);
		//dismissing the alert
		driver.findElement(By.id("confirmButton")).click();
		driver.switchTo().alert().dismiss();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirmResult")));
		String confirmAlertResult2 = driver.findElement(By.id("confirmResult")).getText();
		Reporter.log("On the Confirm Alert, user has selected -> " + confirmAlertResult2, true);
		//handling Prompt alert
		//passing data & accepting the alert
		driver.findElement(By.id("promtButton")).click();
		driver.switchTo().alert().sendKeys("Testing Prompt Alert");
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("promptResult")));
		String promptAlertResult1 = driver.findElement(By.id("promptResult")).getText();
		Reporter.log("On the Prompt Alert, user has entered -> " + promptAlertResult1, true);
		//passing data & dismissing the alert
		driver.findElement(By.id("promtButton")).click();
		driver.switchTo().alert().sendKeys("Testing Prompt Alert");
		driver.switchTo().alert().dismiss();
	}

}
