package seleniumDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ModalDialogBoxDemo 
{
	private WebDriver driver;	
	private String baseURL = "https://demoqa.com/modal-dialogs";

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
	public void ModalDialogCloseButtonTestExample() throws InterruptedException
	{
		//Handling Modal dialog box by simple inspecting the Close button or 'X' icon on the dialog
		//Method 1 - Closing the dialog by clicking on the CLOSE button
		//Modal Box 1
		driver.findElement(By.id("showSmallModal")).click();
		Thread.sleep(2000);
		String modalWindowText = driver.findElement(By.className("modal-body")).getText();
		Reporter.log("Text displayed on the modal dialog box -> " + modalWindowText, true);
		//closing the modal window using close button
		driver.findElement(By.id("closeSmallModal")).click();
		//Modal Box 2
		driver.findElement(By.id("showLargeModal")).click();
		Thread.sleep(2000);
		String modalWindowText1 = driver.findElement(By.xpath("//div[@class='modal-body']//p")).getText();
		Reporter.log("Text displayed on the modal dialog box -> " + modalWindowText1, true);
		//closing the modal window using close button
		driver.findElement(By.id("closeLargeModal")).click();
	}
	
	@Test
	public void ModalDialogXButtonTestExample() throws InterruptedException
	{
		//Handling Modal dialog box by simple inspecting the 'X' icon on the dialog
		//Method 2 - Closing the dialog by clicking on the "X" icon
		//Modal Box 1
		driver.findElement(By.id("showSmallModal")).click();
		Thread.sleep(2000);
		String modalWindowText = driver.findElement(By.className("modal-body")).getText();
		Reporter.log("Text displayed on the modal dialog box -> " + modalWindowText, true);
		//closing the modal window using "X" icon
		driver.findElement(By.xpath("//button[@class='close']//span[text()='×']")).click();
		//Modal Box 2
		driver.findElement(By.id("showLargeModal")).click();
		Thread.sleep(2000);
		String modalWindowText1 = driver.findElement(By.xpath("//div[@class='modal-body']//p")).getText();
		Reporter.log("Text displayed on the modal dialog box -> " + modalWindowText1, true);
		//closing the modal window using "X" icon
		driver.findElement(By.xpath("//button[@class='close']//span[text()='×']")).click();
	}

}
