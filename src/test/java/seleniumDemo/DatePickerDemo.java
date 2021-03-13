package seleniumDemo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePickerDemo 
{
	private WebDriver driver;	
	private String baseURL = "https://www.demoqa.com/date-picker";

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
	public void DatePickerTestExample() throws InterruptedException
	{
		String month = "December";
		String year = "2023";
		String day = "30";
		String timeValue = "10:30";
		WebElement inputDate = driver.findElement(By.id("datePickerMonthYearInput"));
		inputDate.click();
		WebElement monthElement = driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']"));
		Select monthSelect = new Select(monthElement);
		monthSelect.selectByVisibleText(month);
		WebElement yearElement = driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']"));
		Select yearSelect = new Select(yearElement);
		yearSelect.selectByVisibleText(year);
		String dayXpath = "//div[contains(@class,'react-datepicker__day') and text()='<day>']";
		dayXpath = dayXpath.replace("<day>", day);
		driver.findElement(By.xpath(dayXpath)).click();
		String dateInputValue = inputDate.getAttribute("value");
		Reporter.log("Date value after entering -> " + dateInputValue, true);
		
		LocalDateTime now = LocalDateTime.now().plusDays(60);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formatTime = now.format(format);
		inputDate.clear();
		inputDate.sendKeys(formatTime);
		String dateInputValue1 = inputDate.getAttribute("value");
		Reporter.log("Date value after entering -> " + dateInputValue1, true);
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dateAndTimePickerInput")));
		WebElement secondInputDateField = driver.findElement(By.id("dateAndTimePickerInput"));
		secondInputDateField.click();
		driver.findElement(By.xpath("//div[@class='react-datepicker__month-read-view']")).click();
		String monthXpath = "//div[@class='react-datepicker__month-option' and text()='<Month>']";
		monthXpath = monthXpath.replace("<Month>", month);
		Thread.sleep(3000);
		driver.findElement(By.xpath(monthXpath)).click();
		driver.findElement(By.xpath("//div[@class='react-datepicker__year-read-view']")).click();
		String yearXpath = "//div[@class='react-datepicker__year-option' and text()='<Year>']";
		yearXpath = yearXpath.replace("<Year>", year);
		Thread.sleep(3000);
		driver.findElement(By.xpath(yearXpath)).click();
		String dayXpath2 = "//div[contains(@class,'react-datepicker') and text()='<Day>']";
		dayXpath2 = dayXpath2.replace("<Day>", day);
		Thread.sleep(3000);
		driver.findElement(By.xpath(dayXpath2)).click();
		String timeXpath = "//ul[@class='react-datepicker__time-list']//li[text()='<Time>']";
		timeXpath = timeXpath.replace("<Time>", timeValue);
		WebElement elementTime = driver.findElement(By.xpath(timeXpath));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", elementTime);
		Thread.sleep(3000);
		elementTime.click();
		String secondDateFieldInputValue = driver.findElement(By.id("dateAndTimePickerInput")).getAttribute("value");
		Reporter.log("Value in the 2nd date input field is -> " + secondDateFieldInputValue, true);
	}

}
