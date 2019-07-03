package androidNativeApps;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports extends AppiumDemoTest {
	public static ExtentReports extent;
	public static ExtentTest test;
	static String destDir;
	static String destDir1;
	static DateFormat dateFormat;

	public static String screenshot()
	{
		destDir=System.getProperty("user.dir")+"\\"+"sc";
		System.out.println("Destination directory is   "+destDir);
		TakesScreenshot scr = (TakesScreenshot) AppiumDemoTest.driver;
		File scrFile = scr.getScreenshotAs(OutputType.FILE);
		dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		new File(destDir).mkdirs();
		// Set file name using current date time.
		String destFile = dateFormat.format(new Date()) + ".png";

		try {
			// Copy paste file at destination folder location
			System.out.println("Images directory "+destDir + "\\" + destFile);
			FileUtils.copyFile(scrFile, new File(destDir + "\\" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//String shot = scr.getScreenshotAs(OutputType.BASE64);
		System.out.println("Satya-------"+scrFile);
		return ""+destDir + "\\" + destFile;
	}
	public static void initializeReport(String htmlReport)
	{
		destDir1 = "ExtentReports";
		//File f = new File(System.getProperty("user.dir")+"//"+destDir1);
		System.out.println(System.getProperty("user.dir")+"\\"+destDir1);
		extent = new ExtentReports(System.getProperty("user.dir")+"\\"+destDir1+"\\"+htmlReport+".html");
		extent.addSystemInfo("Environment","Android Environment");
		extent.addSystemInfo("Device","3iInfotech test Emulator");
		extent.addSystemInfo("App","Test app for Demo");
		extent.loadConfig(new File("extent.xml"));
	}
	public static void startTest(String AppiumTestStart)
	{
		test = extent.startTest(AppiumTestStart);
	}
	public static void endTest()
	{
		extent.endTest(test);
	}
	public static void flushReport()
	{
		extent.flush();
	}

	public static void logEvent(String description, String status)
	{
		status = status.toLowerCase().trim();
		if(status.equalsIgnoreCase("pass"))
		{
			test.log(LogStatus.PASS, description+test.addScreenCapture(screenshot()));
		}
		else if(status.equalsIgnoreCase("fail"))
		{
			test.log(LogStatus.FAIL, description+test.addScreenCapture(screenshot()));
		}

	}


}
