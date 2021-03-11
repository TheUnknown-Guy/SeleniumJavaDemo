package seleniumDemo;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SwitchTabsAndWindowsDemo 
{
	private WebDriver driver;	
	private String baseURL = "https://demoqa.com/browser-windows";

	@BeforeMethod
	public void setUp() throws Exception 
	{
		System.out.println("Inside the setUp() method");
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		System.out.println("Launched Firefox Driver - open letskodeit.teachable.com");
	}

	@AfterMethod
	public void tearDown() throws Exception 
	{
		System.out.println("Inside the tearDown() method");
		Thread.sleep(2000);
		driver.quit();
		System.out.println("Quit the Browser");
	}

	@Test
	public void SwitchTabTestExample() throws InterruptedException
	{
		//Method1
		String parentHandle = driver.getWindowHandle();
		driver.findElement(By.id("tabButton")).click();
		ArrayList<String> windowHandle = new ArrayList<String>(driver.getWindowHandles());
		for(String handle : windowHandle)
		{
			System.out.println(handle);
			if(!(handle.equals(parentHandle)))
			{
				driver.switchTo().window(handle);
				Thread.sleep(3000);
				String text  = driver.findElement(By.id("sampleHeading")).getText();
				System.out.println("Fetching the text value from the newly opened tab  -> " + text);
				driver.close();
			}
		}
		driver.switchTo().window(parentHandle);
		//Method2 
		Thread.sleep(3000);
		driver.findElement(By.id("tabButton")).click();
		ArrayList<String> windowHandle1 = new ArrayList<String>(driver.getWindowHandles());
		String childTab = windowHandle1.get(1);
		driver.switchTo().window(childTab);
		Thread.sleep(3000);
		String text  = driver.findElement(By.id("sampleHeading")).getText();
		System.out.println("Fetching the text value from the newly opened tab  -> " + text);
		driver.close();
		driver.switchTo().window(parentHandle);
	}

	@Test
	public void SwitchWindowTestExample() throws InterruptedException
	{
		//Method1
		String parentHandle = driver.getWindowHandle();
		driver.findElement(By.id("windowButton")).click();
		ArrayList<String> windowHandle = new ArrayList<String>(driver.getWindowHandles());
		for(String handle : windowHandle)
		{
			System.out.println(handle);
			if(!(handle.equals(parentHandle)))
			{
				driver.switchTo().window(handle);
				Thread.sleep(3000);
				String text  = driver.findElement(By.id("sampleHeading")).getText();
				System.out.println("Fetching the text value from the newly opened tab  -> " + text);
				driver.close();
			}
		}
		driver.switchTo().window(parentHandle);
		//Method2 
		Thread.sleep(3000);
		driver.findElement(By.id("windowButton")).click();
		ArrayList<String> windowHandle1 = new ArrayList<String>(driver.getWindowHandles());
		String childTab = windowHandle1.get(1);
		driver.switchTo().window(childTab);
		Thread.sleep(3000);
		String text  = driver.findElement(By.id("sampleHeading")).getText();
		System.out.println("Fetching the text value from the newly opened tab  -> " + text);
		driver.close();
		driver.switchTo().window(parentHandle);
	}

	@Test
	public void SwitchToMessageWindowTestExample() throws InterruptedException
	{
		//Method1
		String parentHandle = driver.getWindowHandle();
		driver.findElement(By.id("messageWindowButton")).click();
		ArrayList<String> windowHandle = new ArrayList<String>(driver.getWindowHandles());
		for(String handle : windowHandle)
		{
			System.out.println(handle);
			if(!(handle.equals(parentHandle)))
			{
				driver.switchTo().window(handle);
				Thread.sleep(3000);
				String text  = driver.findElement(By.xpath("//body")).getText();
				System.out.println("Fetching the text value from the newly opened tab  -> " + text);
				driver.close();
			}
		}
		driver.switchTo().window(parentHandle);
		//Method2 
		Thread.sleep(3000);
		driver.findElement(By.id("messageWindowButton")).click();
		ArrayList<String> windowHandle1 = new ArrayList<String>(driver.getWindowHandles());
		String childTab = windowHandle1.get(1);
		driver.switchTo().window(childTab);
		Thread.sleep(3000);
		String text  = driver.findElement(By.xpath("//body")).getText();
		System.out.println("Fetching the text value from the newly opened tab  -> " + text);
		driver.close();
		driver.switchTo().window(parentHandle);
	}
	
}
