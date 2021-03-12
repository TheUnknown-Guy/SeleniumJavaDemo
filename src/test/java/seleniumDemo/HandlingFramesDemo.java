package seleniumDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingFramesDemo 
{
	private WebDriver driver;	
	private String baseURL = "https://demoqa.com/frames";

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
	public void SwitchFrameTestExample() throws InterruptedException
	{
		//USING FRAME INDEX
		Reporter.log("----- SWITCHING USING FRAME INDEX -----", true);
		String headingText = driver.findElement(By.xpath("(//div[@id='framesWrapper']//div)[1]")).getText();
		Reporter.log("Text Data displayed on the webpage -> " + headingText, true);
		//Switching to the frame - using "frame(int index)" method where 0 is the first frame on the webpage
		Reporter.log("Webdriver focus is shift to the First frame", true);
		driver.switchTo().frame(0);
		String frameOneText = driver.findElement(By.id("sampleHeading")).getText();
		Reporter.log("Text Data displayed on the Frame ONE -> " + frameOneText, true);
		//Switching to the default content back to main webpage
		driver.switchTo().defaultContent();
		Reporter.log("Webdriver focus is shift to the default webpage", true);
		String header = driver.findElement(By.className("main-header")).getText();
		Reporter.log("Getting the header displayed on the main webpage -> " + header, true);
		//Switching to the frame - using "frame(int index)" method where 1 is the second frame on the webpage
		driver.switchTo().frame(1);
		String frameTwoText = driver.findElement(By.id("sampleHeading")).getText();
		Reporter.log("Text Data displayed on the Frame TWO -> " + frameTwoText, true);
		//Switching to the default content back to main webpage
		driver.switchTo().defaultContent();
		Reporter.log("Webdriver focus is shift to the default webpage", true);
		String header1 = driver.findElement(By.className("main-header")).getText();
		Reporter.log("Getting the header displayed on the main webpage -> " + header1, true);
		//USING FRAME ID or NAME
		Reporter.log("----- SWITCHING USING FRAME ID or NAME -----", true);
		//Switching to the frame - using "frame(String nameOrID)" method where frame1 is the first frame id on the webpage
		Reporter.log("Webdriver focus is shift to the First frame", true);
		driver.switchTo().frame("frame1");
		String frameOneText1 = driver.findElement(By.id("sampleHeading")).getText();
		Reporter.log("Text Data displayed on the Frame ONE -> " + frameOneText1, true);
		//Switching to the default content back to main webpage
		driver.switchTo().defaultContent();
		Reporter.log("Webdriver focus is shift to the default webpage", true);
		String header2 = driver.findElement(By.className("main-header")).getText();
		Reporter.log("Getting the header displayed on the main webpage -> " + header2, true);
		//Switching to the frame - using "frame(String nameOrID)" method where frame2 is the first frame id on the webpage
		driver.switchTo().frame("frame2");
		String frameTwoText1 = driver.findElement(By.id("sampleHeading")).getText();
		Reporter.log("Text Data displayed on the Frame TWO -> " + frameTwoText1, true);
		//Switching to the default content back to main webpage
		driver.switchTo().defaultContent();
		Reporter.log("Webdriver focus is shift to the default webpage", true);
		String header3 = driver.findElement(By.className("main-header")).getText();
		Reporter.log("Getting the header displayed on the main webpage -> " + header3, true);
		//USING FRAME WEBELEMENT
		Reporter.log("----- SWITCHING USING FRAME WEBELEMENT (xpath) -----", true);
		////Switching to the frame - using "frame(WebElement element)" method where element is the xpath of Frame
		Reporter.log("Webdriver focus is shift to the First frame", true);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//div[@id='framesWrapper']//div)[1]")));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='frame1Wrapper']//iframe")));
		String frameOneText2 = driver.findElement(By.id("sampleHeading")).getText();
		Reporter.log("Text Data displayed on the Frame ONE -> " + frameOneText2, true);
		//Switching to the default content back to main webpage
		driver.switchTo().defaultContent();
		Reporter.log("Webdriver focus is shift to the default webpage", true);
		String header4 = driver.findElement(By.className("main-header")).getText();
		Reporter.log("Getting the header displayed on the main webpage -> " + header4, true);
		//Switching to the frame - using "frame(WebElement element)" method where element is the xpath of Frame
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='frame2Wrapper']//iframe")));
		//Horizontal scroll - bottom of page
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//vertical scroll
		((JavascriptExecutor)driver).executeScript("window.scrollBy(200, 0)");
		String frameTwoText2 = driver.findElement(By.id("sampleHeading")).getText();
		Reporter.log("Text Data displayed on the Frame TWO -> " + frameTwoText2, true);
		//Switching to the default content back to main webpage
		driver.switchTo().defaultContent();
		Reporter.log("Webdriver focus is shift to the default webpage", true);
		String header5 = driver.findElement(By.className("main-header")).getText();
		Reporter.log("Getting the header displayed on the main webpage -> " + header5, true);
	}
}
