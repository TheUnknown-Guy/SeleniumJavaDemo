package seleniumDemo;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ToolTipHoverDemo 
{
	private WebDriver driver;	
	private String baseURL = "https://www.demoqa.com/tool-tips";

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
	public void HoveringOverToolTipTestExample() throws InterruptedException
	{
		WebElement toolTipOnButton = driver.findElement(By.id("toolTipButton"));
		Actions actions = new Actions(driver);
		actions.moveToElement(toolTipOnButton).build().perform();
		Reporter.log("Fetching the text value displayed as tooltip on the button", true);
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='You hovered over the Button']")));
		WebElement toolTipText = driver.findElement(By.xpath("//div[text()='You hovered over the Button']"));
		String toolTipTextString = toolTipText.getText();
		Reporter.log("Tooltip text displayed on the button -> " + toolTipTextString , true);
		Thread.sleep(4000);
		Reporter.log("Fetching the text value displayed as tooltip on the InputField", true);
		WebElement toolTipOnInputField = driver.findElement(By.id("toolTipTextField"));
		actions.moveToElement(toolTipOnInputField).build().perform();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='You hovered over the text field']")));
		toolTipText = driver.findElement(By.xpath("//div[text()='You hovered over the text field']"));
		toolTipTextString = toolTipText.getText();
		Reporter.log("Tooltip text displayed on the InputField -> " + toolTipTextString , true);
		Thread.sleep(4000);
		Reporter.log("Fetching the text value displayed as tooltip on the Anchor Tag 1 field", true);
		WebElement toolTipOnAnchorTag = driver.findElement(By.xpath("//a[text()='Contrary']"));
		actions.moveToElement(toolTipOnAnchorTag).build().perform();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='You hovered over the Contrary']")));
		toolTipText = driver.findElement(By.xpath("//div[text()='You hovered over the Contrary']"));
		toolTipTextString = toolTipText.getText();
		Reporter.log("Tooltip text displayed on the AnchorTag text field -> " + toolTipTextString , true);
		Thread.sleep(4000);
		Reporter.log("Fetching the text value displayed as tooltip on the Anchor Tag 2 field", true);
		WebElement toolTipOnAnchorTag2 = driver.findElement(By.xpath("//a[text()='1.10.32']"));
		actions.moveToElement(toolTipOnAnchorTag2).build().perform();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='You hovered over the 1.10.32']")));
		toolTipText = driver.findElement(By.xpath("//div[text()='You hovered over the 1.10.32']"));
		toolTipTextString = toolTipText.getText();
		Reporter.log("Tooltip text displayed on the AnchorTag text field -> " + toolTipTextString , true);
		Thread.sleep(4000);
	}

}
