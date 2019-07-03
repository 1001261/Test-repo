package androidNativeApps;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParallelExecution {
	public WebDriver driver;
	
	
	@BeforeMethod
	public void setUp() throws InterruptedException, IOException
	{
		System.out.println("BeforeMethod");
		
	}
	@Test(dataProvider="EnvironmentDetails")
	public void Testcase(String device, String os_version, String browserName) throws InterruptedException, IOException {
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.firefox.driver", "D:\\drivers\\geckodriver.exe");
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("browserName", browserName);
		capability.setCapability("device", device); 
		capability.setCapability("os_version", os_version);

		capability.setCapability("realMobile", "true");
		capability.setCapability("browserstack.debug", "true");
		capability.setCapability("browserstack.networkLogs", "true");
		//capability.setCapability("browserstack.console", "true");	

		URL browserStackUrl = new URL("");

		driver = new RemoteWebDriver (browserStackUrl, capability);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("https://dev.moneymartdirect.com/");
		System.out.println("Parallel execution on mobile devices");

		String actualURL = driver.getCurrentUrl();
		System.out.println("URL is "+actualURL);

		String expectedURL = "https://dev.moneymartdirect.com/";
		Assert.assertEquals(actualURL, expectedURL,"Failed To Open");
	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

	// passing parallel = true to run the test scripts in parallel
	// DataProvider supports Object return type 
	// Passing values such as MAC with Chrome 62, Windows 8 with Chrome 62, and Windows 7 with firefox 57
	@DataProvider(name="EnvironmentDetails", parallel=true)
	public Object[][] getData(){

		Object[][] testData = new Object[][]{
			{"iPhoneXR", "12", "Safari"},
			{"Samsung Galaxy Note 9", "8.1", "chrome"},

		};

		return testData; 
	}
}