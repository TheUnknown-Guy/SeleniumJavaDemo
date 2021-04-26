package seleniumDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SendKeysAlternative 
{
	private WebDriver driver;	
	private String baseURL = "https://www.demoqa.com/text-box";

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
	public void tearDown()
	{
		Reporter.log("Inside the tearDown() method", true);
		driver.quit();
		Reporter.log("Quit the Browser", true);
	}
	
	@Test
	public void DemoTestExample() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Full Name']")));
		WebElement textbox = driver.findElement(By.xpath("//input[@placeholder='Full Name']"));
		WrapsDriver wd = (WrapsDriver)textbox;
		JavascriptExecutor js = (JavascriptExecutor)wd.getWrappedDriver();
		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", textbox, "value","Automation Testing");
		
		WebElement email = driver.findElement(By.xpath("//input[@placeholder='name@example.com']"));
		WrapsDriver wd1 = (WrapsDriver)textbox;
		JavascriptExecutor js1 = (JavascriptExecutor)wd1.getWrappedDriver();
		js1.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", email, "value","AutomationTesting@gmail.com");
		Thread.sleep(4000);
	}
	
}
