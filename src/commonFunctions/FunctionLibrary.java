package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.PropertyFileUtil;

public class FunctionLibrary {
	
	public static WebDriver driver;
	
	public static WebDriver startBrowser() throws Throwable
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			driver= new FirefoxDriver();
			driver.manage().window().maximize();
		}
		else
		{
			System.out.println("Browser value not matching");
		}
		return driver;
	}
	
	public static void appUrl(WebDriver driver) throws Throwable
	{
		driver.get(PropertyFileUtil.getValueForKey("Url"));
	}

	public static void waitforElement(WebDriver driver, String LocatorType, String LocatorValue,String TestData)
	{
		WebDriverWait myWait=new WebDriverWait(driver, Integer.parseInt(TestData));
		if(LocatorType.equalsIgnoreCase("xpath")) 
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
		}	
		else if(LocatorType.equalsIgnoreCase("id"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
		}
		else if(LocatorType.equalsIgnoreCase("name"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
		}
		else
		{
			System.out.println("Unable to execute wait for element method");
		}
	}
	public static void typeAction(WebDriver driver, String LocatorType, String LocatorValue,String TestData)
	{
		if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).clear();
			driver.findElement(By.name(LocatorValue)).sendKeys(TestData);
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).clear();
			driver.findElement(By.xpath(LocatorValue)).sendKeys(TestData);
		}
		else if(LocatorType.equalsIgnoreCase("id"))
		{
		
			driver.findElement(By.id(LocatorValue)).clear();
			driver.findElement(By.id(LocatorValue)).sendKeys(TestData);
		}
		else
		{
			System.out.println("unable to execute Type Action");
		}
		
	}
	
	public static void clickAction(WebDriver driver,String LocatorType, String LocatorValue)
	{
		if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).click();
		}
		else if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).click();
		}
		else if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);
		}
		else
		{
			System.out.println("unable to execute click action for locator");
		}
	}
	
	public static void validateTitle(WebDriver driver, String ExpectedTitle)
	{
		String ActualTitle = driver.getTitle();
		try
		{
			Assert.assertEquals(ExpectedTitle, ActualTitle,"Title is not matching");// when ever my test case fails it will dispaly other wise it continues testing
			// better to be in try catch block
		} catch (Throwable t) 
		{
			System.out.println(t.getMessage());
			// TODO: handle exception
		}
		
	}
	
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	
	public void add()
	{
		int a =10;
		System.out.println(a);
	}
	
	public void sub()
	{
		int b =10;
		System.out.println(b);
	}
	
}
