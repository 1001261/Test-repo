package androidNativeApps;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Web_Demo {
	AndroidDriver driver;

	@Test
	public void Launch_URL() throws InterruptedException {

		driver.get("https://www.google.co.in");
	    WebElement element = driver.findElement(By.name("q"));

	    element.sendKeys("BrowserStack");
	    if(element.getText().equalsIgnoreCase("BrowserStack"))
	    {
	    	System.out.println("In If cond---"+element.getText());
	    	element.submit();
	    }
	    else
	    {
	    	Thread.sleep(6000);
	    }
	    System.out.println("After else cond---"+element.getText());
	    element.submit();

	    System.out.println(driver.getTitle());
	}
	@BeforeMethod
	public void beforeMethod() throws Exception {

		DesiredCapabilities caps = new DesiredCapabilities();
		/*caps.setCapability("deviceName", "3i-Infotech");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "9.0");
		caps.setCapability("udid", "emulator-5554");
		// caps.setCapability("browserName", "Chrome");
		caps.setCapability("appPackage", "com.android.chrome");
		caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");*/

			caps.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.8.1");
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
			caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"Appium");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "3i-Infotech");
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			caps.setCapability("newCommandTimeout", 2000);



		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// System.setProperty("webdriver.chrome.driver", "C:\\Users\\1001261\\Downloads\\chromedriver_win32_1\\chromedriver.exe");
		//driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Quit browser");
		//driver.close();
	}

}
