package seleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExplicitWaitExpectedConditionsDemo 
{
	private WebDriver driver;	
	private String baseURL = "https://demoqa.com/alerts";

	@BeforeMethod
	public void setUp() throws Exception 
	{
		Reporter.log("Inside the setUp() method", true);
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(baseURL);
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
	public void AlertIsPresentTest()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("timerAlertButton"))));
		driver.findElement(By.id("timerAlertButton")).click();
		//org.openqa.selenium.NoAlertPresentException will be thrown if Explicit wait is not provided
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}
	
	@Test
	public void WaitUntilButtonIsClickableTest() throws InterruptedException
	{
		driver.findElement(By.xpath("//div[@class='header-text' and contains(text(),'Elements')]")).click();
		WebElement dynamicProperties = driver.findElement(By.xpath("//span[text()='Dynamic Properties']"));
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].scrollIntoView()", dynamicProperties);
		dynamicProperties.click();
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		//will add a Explicit Wait of 10 seconds until the button is clickable and then click on it
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("enableAfter"))));
		driver.findElement(By.id("enableAfter")).click();
		//to check of the button is clicked we are waiting 3 seconds after clicking
		Thread.sleep(3000);
	}
	
}
