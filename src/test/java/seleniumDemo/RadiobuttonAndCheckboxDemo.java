package seleniumDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RadiobuttonAndCheckboxDemo 
{
	private WebDriver driver;	
	private String baseURL = "https://letskodeit.teachable.com/p/practice";

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
		System.out.println("Launched Firefox Driver & opened URL -> https://letskodeit.teachable.com/p/practice");
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
	public void RadioButtonTestExample()
	{
		driver.findElement(By.id("bmwradio")).click();
		boolean checkSelected  = driver.findElement(By.id("benzradio")).isSelected();
		boolean checkDisplayed = driver.findElement(By.id("hondaradio")).isDisplayed();
		driver.findElement(By.id("hondaradio")).click();
		boolean checkEnabled = driver.findElement(By.id("bmwradio")).isEnabled();
		System.out.println("Checking if the radio button Benz is selected or not ? => " + checkSelected);
		System.out.println("Checking if the radio button Honda is displayed or not ? => " + checkDisplayed);
		System.out.println("Checking if the radio button BMW is enabled or not ? => " + checkEnabled);
	}
	
	@Test
	public void CheckboxTestExample()
	{
		driver.findElement(By.id("bmwcheck")).click();
		boolean checkSelected  = driver.findElement(By.id("benzcheck")).isSelected();
		boolean checkDisplayed = driver.findElement(By.id("hondacheck")).isDisplayed();
		boolean checkEnabled = driver.findElement(By.id("bmwcheck")).isEnabled();
		System.out.println("Checking if the checkbox Benz is selected or not ? => " + checkSelected);
		System.out.println("Checking if the checkbox Honda is displayed or not ? => " + checkDisplayed);
		System.out.println("Checking if the checkbox BMW is enabled or not ? => " + checkEnabled);
		//selecting multiple checkboxes
		driver.findElement(By.id("hondacheck")).click();
		driver.findElement(By.id("benzcheck")).click();
		//performing uncheck operation on above checked boxes
		driver.findElement(By.id("hondacheck")).click();
		driver.findElement(By.id("benzcheck")).click();
	}

}
