package seleniumDemo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumMinimizeBrowserDemo 
{
	@Test(enabled = true)
	public void MinimizeBrowserUsingPointClass() throws InterruptedException
	{
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		Point p = new Point(0,-1000);
		Thread.sleep(2000);
		driver.manage().window().setPosition(p);
		driver.get("http://www.google.com");
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@value=\"I'm Feeling Lucky\"])[2]")).click();
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(5000);
		driver.quit();
	}
	
	@Test
	public void MinimizeBrowserUsingRobotClass() throws InterruptedException, AWTException
	{
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.google.com");
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_SPACE);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_SPACE);
		robot.keyRelease(KeyEvent.VK_N);
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@value=\"I'm Feeling Lucky\"])[2]")).click();
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(5000);
		driver.quit();
	}
	
	@Test
	public void MinimizeBrowserusingSelenium4() throws InterruptedException
	{
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.google.com");
		Thread.sleep(2000);
		//need selenium version 4.0.0-beta-1 & above
		//driver.manage().window().minimize();
		Thread.sleep(2000);
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@value=\"I'm Feeling Lucky\"])[2]")).click();
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(5000);
		driver.quit();
	}
}
