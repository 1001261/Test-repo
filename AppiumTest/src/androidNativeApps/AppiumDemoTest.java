package androidNativeApps;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;

public class AppiumDemoTest {
 static AndroidDriver<WebElement> driver;
 Dimension size;
 String destDir;
 DateFormat dateFormat;
 //	Scroll sc = new Scroll();
	@Test
	public void Launch_URL() throws Exception {

		Thread.sleep(1500);
		org.openqa.selenium.ScreenOrientation so = driver.getOrientation();
		driver.rotate(so.LANDSCAPE);
		Reports.logEvent("App in LandScape view", "pass");
		driver.rotate(so.PORTRAIT);
		for(int i=0;i<3;i++)
		{
			if(i==0)

			{
				List<WebElement> links = driver.findElementsByClassName("android.widget.RelativeLayout");
				links.get(i).click();
				Thread.sleep(3500);
				int j=i+1;
				Reports.logEvent("Link "+j+" clicked", "pass");
				takeScreenShot();
				
				
				List<WebElement> back = driver.findElementsByClassName("android.widget.ImageButton");
				back.get(0).click();
				Reports.logEvent("Navigated back to home page", "pass");
				Thread.sleep(1500);
			}
				else if(i==3)
			{
				Thread.sleep(500);
			}
			else
			{
				List<WebElement> links = driver.findElementsByClassName("android.widget.RelativeLayout");
				links.get(i).click();
				Thread.sleep(3000);
				int j=i+1;
				Reports.logEvent("Link "+j+" clicked", "pass");
				takeScreenShot();
				
				
				List<WebElement> back = driver.findElementsByClassName("android.widget.ImageButton");
				back.get(0).click();
				Reports.logEvent("Navigated back to home page", "pass");
				Thread.sleep(1500);
			}
		
		}
		
		try
		{
			int a = 0/0;
			System.out.println(a);
		}
		catch(Exception e)
		{
			Reports.logEvent("Try catch failed try", "fail");
		}
	}
	@BeforeMethod
	public void beforeMethod() throws Exception {
		//AppiumDemoTest
		Reports.initializeReport("AppiumDemoTest");
		Reports.startTest("DemoTest");
		openEmulator();
		DesiredCapabilities caps = new DesiredCapabilities();
		//  caps.setCapability("app", app.getAbsolutePath());
		caps.setCapability("deviceName", "Satya");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "7.1.1");
		caps.setCapability("appPackage", "in.ajaykhatri.pythontutorial");
		caps.setCapability("appActivity", "in.ajaykhatri.pythontutorial.MainActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
		Reports.logEvent("Demo App opened", "pass");


	}

	@AfterMethod
	public void afterMethod() throws IOException {
		driver.closeApp();
		Reports.endTest();
		Reports.flushReport();
		closerEmulator();
	}
	public void takeScreenShot() {
		  // Set folder name to store screenshots.
		  destDir = "screenshots";
		  // Capture screenshot.
		  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  // Set date format to set It as screenshot file name.
		  dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		  // Create folder under project with name "screenshots" provided to destDir.
		  new File(destDir).mkdirs();
		  // Set file name using current date time.
		  String destFile = dateFormat.format(new Date()) + ".png";

		  try {
		   // Copy paste file at destination folder location
		   FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
	
}
	public void openEmulator() throws IOException
	{
		String EmuPath = "cd Users\\1001261\\AppData\\Local\\Android\\sdk\\tools";
		String command = "emulator -avd Satya";
		System.out.println("cmd /c start cmd.exe /K \"C:  && "+EmuPath+ " && "+ command+ "\"");
		Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"C:  && "+EmuPath+ " && "+ command+ "\"");
	}
	public void closerEmulator() throws IOException
	{
		String EmuPath = "cd Users\\1001261\\AppData\\Local\\Android\\sdk\\tools";
		String command = "adb -e emu kill";
		System.out.println("cmd /c start cmd.exe /K \"C:  && "+EmuPath+ " && "+ command+ "\"");
		Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"C:  && "+EmuPath+ " && "+ command+ "\"");
	}
}