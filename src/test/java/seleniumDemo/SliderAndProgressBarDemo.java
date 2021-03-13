package seleniumDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SliderAndProgressBarDemo 
{
	private WebDriver driver;	
	private String baseURL = "https://www.demoqa.com/slider";

	@BeforeMethod
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

	@AfterMethod
	public void tearDown() 
	{
		Reporter.log("Inside the tearDown() method", true);
		driver.quit();
		Reporter.log("Quit the Browser", true);
	}
	
	@Test
	public void SliderTestExample()
	{
		
		String sliderValue = driver.findElement(By.id("sliderValue")).getAttribute("value");
		Reporter.log("Slider value before the sliding operation is performed -> " + sliderValue, true);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='range-slider range-slider--primary']")));
		WebElement slider = driver.findElement(By.xpath("//input[@class='range-slider range-slider--primary']"));
		Actions actions = new Actions(driver);
		actions.dragAndDropBy(slider, 500, 0).build().perform();
		sliderValue = driver.findElement(By.id("sliderValue")).getAttribute("value");
		Reporter.log("Slider value after the sliding operation is performed -> " + sliderValue, true);
	}
	
	@Test
	public void ProgressBarTestExapmle() throws InterruptedException
	{
		WebElement progressBarText = driver.findElement(By.xpath("//li[@id='item-4']//span[text()='Progress Bar']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", progressBarText);
		progressBarText.click();
		WebElement progressBarValue = driver.findElement(By.xpath("//div[@role='progressbar']"));
		String value = progressBarValue.getAttribute("aria-valuenow");
		Reporter.log("Progress Bar value before START button is clicked -> " + value , true);
		//START button clicked
		driver.findElement(By.id("startStopButton")).click();
		Thread.sleep(2000);
		//STOP
		driver.findElement(By.id("startStopButton")).click();
		value = progressBarValue.getAttribute("aria-valuenow");
		Reporter.log("Progress Bar value after STOP button is clicked -> " + value , true);
		//START
		driver.findElement(By.id("startStopButton")).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resetButton")));
		value = progressBarValue.getAttribute("aria-valuenow");
		Reporter.log("Progress Bar value before RESTART button is clicked -> " + value , true);
		driver.findElement(By.id("resetButton")).click();
	}
	
}
