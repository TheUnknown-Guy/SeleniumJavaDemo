package seleniumDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionsClassSendKeysClick 
{
	private WebDriver driver;	
	private String baseURL = "https://demoqa.com/automation-practice-form";

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
	public void ActionSendKeysAndClickDemoTest() throws InterruptedException
	{
		WebElement firstName = driver.findElement(By.id("firstName"));
		WebElement lastName = driver.findElement(By.id("lastName"));
		
		Actions action = new Actions(driver);
		action.sendKeys(firstName, "Selenium").perform();
		action.sendKeys(lastName, "Automation").perform();
		
		WebElement radiobtn = driver.findElement(By.id("gender-radio-3"));
		action.click(radiobtn).perform();
		
		Thread.sleep(5000);
	}
	
}
