package seleniumDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class HandlingAccordianAndAutoCompleteDemo 
{
	private WebDriver driver;	
	private String baseURL = "https://www.demoqa.com/accordian";

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
	public void tearDown() throws Exception 
	{
		Reporter.log("Inside the tearDown() method", true);
		Thread.sleep(2000);
		driver.quit();
		Reporter.log("Quit the Browser", true);
	}
	
	@Test
	public void HandlingAccourdianTestExample()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Reporter.log("Performing actions on the Accordian", true);
		WebElement heading1 = driver.findElement(By.id("section1Heading"));
		WebElement heading2 = driver.findElement(By.id("section2Heading"));
		WebElement heading3 = driver.findElement(By.id("section3Heading"));
		
		String heading1Text = heading1.getText();
		Reporter.log("Heading One text on the Accordian -> " + heading1Text, true);
		heading1.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='section1Content']//p")));
		WebElement content1 = driver.findElement(By.xpath("//div[@id='section1Content']//p"));
		String content1Text = content1.getAttribute("innerHTML");
		Reporter.log("Content text under heading one in the Accordian -> " + content1Text, true);
		
		String heading2Text = heading2.getText();
		Reporter.log("Heading Two text on the Accordian -> " + heading2Text, true);
		heading2.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("section2Content")));
		WebElement content2 = driver.findElement(By.id("section2Content"));
		String content2Text = content2.getText();
		Reporter.log("Content text under heading two in the Accordian -> " + content2Text, true);
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", heading2);
		
		String heading3Text = heading3.getText();
		Reporter.log("Heading Three text on the Accordian -> " + heading3Text, true);
		heading3.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("section3Content")));
		WebElement content3 = driver.findElement(By.id("section3Content"));
		String content3Text = content3.getText();
		Reporter.log("Content text under heading three in the Accordian -> " + content3Text, true);
	}
	
	@Test
	public void AutoCompleteTestExample()
	{
		WebElement element = 	driver.findElement(By.xpath("//li[@id='item-1']//span[text()='Auto Complete']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		WebElement inputOne = driver.findElement(By.xpath("(//div[@class='auto-complete__input']//input)[1]"));
		String[] color = {"Red", "Green", "Blue", "Yellow", "Black", "Magenta"};
		for (String str : color)
		{
			inputOne.sendKeys(str);
			inputOne.sendKeys(Keys.ENTER);
		}
		WebElement inputTwo = driver.findElement(By.xpath("(//div[@class='auto-complete__input']//input)[2]"));
		String[] color2 = {"Red", "Green", "Blue", "Yellow", "Black", "Magenta"};
		for (String str : color2)
		{
			inputTwo.sendKeys(str);
			inputTwo.sendKeys(Keys.ENTER);
		}
		
	}

}
