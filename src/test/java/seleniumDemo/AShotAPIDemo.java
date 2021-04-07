package seleniumDemo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class AShotAPIDemo 
{

	private WebDriver driver;	
	private String baseURL = "https://www.udemy.com/";

	@BeforeMethod
	public void setUp() throws Exception 
	{
		Reporter.log("Inside the setUp() method", true);
		 WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(baseURL);
		driver.manage().deleteAllCookies();
		Reporter.log("Launched Chrome Driver & Opened URL -> " + baseURL, true);
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
	public void AShotAPIDmeoFullScreenshot() throws IOException
	{
		String projectPath = System.getProperty("user.dir");
		DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd MMM, yyyy hh-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String fileName = "FullPageScreenshot " + dtf.format(now) + ".png";
		File destinationFile = new File(projectPath+"\\screenshots\\" + fileName);
		Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		ImageIO.write(fpScreenshot.getImage(),"PNG", destinationFile);
	}
}
