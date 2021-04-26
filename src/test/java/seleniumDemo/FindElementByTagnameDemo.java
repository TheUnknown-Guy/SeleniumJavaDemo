package seleniumDemo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindElementByTagnameDemo 
{
	private WebDriver driver;	
	private String baseURL = "https://www.demoqa.com/";

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
	public void FindElementTagNameTest()
	{
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		System.out.println("No. of elements found on the webpage with tag name 'a' is -> " + elements.size());
		System.out.println("*** Printing the 'href' attribute of each element by tagname 'a' found on webpage ***");
		for(WebElement WE : elements)
		{
			System.out.println(WE.getAttribute("href"));
		}
	}
	
}
