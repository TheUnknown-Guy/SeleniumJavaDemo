package seleniumDemo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class SelectClassDemo 
{
	private WebDriver driver;	
	private String baseURL = "https://letskodeit.teachable.com/p/practice";
	String path = System.getProperty("user.dir");

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
		System.out.println("Closing the Browser");
	}
	
	@Test
	public void SelectClassDemoTestExample() throws InterruptedException
	{
		//Performing actions on dropdown where user can select multiple values
		WebElement multipleDropdownElement = driver.findElement(By.id("multiple-select-example"));
		Select multiSelect = new Select(multipleDropdownElement);
		multiSelect.selectByIndex(0);
		multiSelect.deselectByIndex(0);
		multiSelect.selectByValue("orange");
		multiSelect.deselectByValue("orange");
		multiSelect.selectByVisibleText("Peach");
		multiSelect.deselectByVisibleText("Peach");
		List<WebElement> elementOptions = multiSelect.getOptions();
		for(WebElement WE : elementOptions)
		{
			System.out.println(WE.getText());
		}
		boolean check = multiSelect.isMultiple();
		System.out.println("Does dropdown allow multiple options to be checked ?  =>  " + check);
		multiSelect.selectByIndex(0);
		multiSelect.selectByValue("orange");
		multiSelect.selectByVisibleText("Peach");
		multiSelect.deselectAll();
		//Performing action on dropdown where user can select only one value
		WebElement singleDropdownElement = driver.findElement(By.id("carselect"));
		Select singleSelect = new Select(singleDropdownElement);
		singleSelect.selectByIndex(0);
		singleSelect.selectByValue("benz");
		singleSelect.selectByVisibleText("Honda");
		//if you perform deselect operation on singleSelect object it will thrown an exception
		//comment out the below 4 line to run the code successfully
		singleSelect.deselectByIndex(0);
		singleSelect.deselectByValue("benz");
		singleSelect.deselectByValue("Honda");
		singleSelect.deselectAll();
		//above all deselect method will throw the exception mentioned below
		//java.lang.UnsupportedOperationException: You may only deselect options of a multi-select
	}

}
