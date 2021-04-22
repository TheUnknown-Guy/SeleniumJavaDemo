package seleniumDemo;

import java.util.concurrent.TimeUnit;

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

public class ScrollIntoViewDemo 
{
	private WebDriver driver;	
	private String baseURL = "https://www.flipkart.com";

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
	public void ScrollIntoViewTrueArguementTest() throws InterruptedException
	{
		WebElement close = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(close));
		close.click();
		driver.findElement(By.xpath("//div[text()='Top Offers']")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h2[text()='Top Selling Mobiles']")));
		WebElement topSelling = driver.findElement(By.xpath("//h2[text()='Top Selling Mobiles']"));
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].scrollIntoView(true);", topSelling);
		WebElement viewAll = driver.findElement(By.xpath("//h2[text()='Top Selling Mobiles']/..//a[text()='VIEW ALL']"));
		Thread.sleep(6000);
		viewAll.click();
	}
	
	@Test
	public void ScrollIntoViewFalseArguementTest() throws InterruptedException
	{
		WebElement close = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(close));
		close.click();
		driver.findElement(By.xpath("//div[text()='Top Offers']")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h2[text()='Top Selling Mobiles']")));
		WebElement topSelling = driver.findElement(By.xpath("//h2[text()='Top Selling Mobiles']"));
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].scrollIntoView(false);", topSelling);
		WebElement viewAll = driver.findElement(By.xpath("//h2[text()='Top Selling Mobiles']/..//a[text()='VIEW ALL']"));
		Thread.sleep(6000);
		viewAll.click();
	}
	@Test
	public void ScrollIntoViewMiddleTest() throws InterruptedException
	{
		WebElement close = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(close));
		close.click();
		driver.findElement(By.xpath("//div[text()='Top Offers']")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h2[text()='Top Selling Mobiles']")));
		WebElement topSelling = driver.findElement(By.xpath("//h2[text()='Top Selling Mobiles']"));
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		//scroll element to middle of the page 
		js.executeScript("arguments[0].scrollIntoView({behaviour:'smooth',inline:'center',block:'center'});", topSelling);
		WebElement viewAll = driver.findElement(By.xpath("//h2[text()='Top Selling Mobiles']/..//a[text()='VIEW ALL']"));
		Thread.sleep(6000);
		viewAll.click();
	}
	
	
}
