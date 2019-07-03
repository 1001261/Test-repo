package androidNativeApps;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class BrowserStackSample {

  public static final String USERNAME = "vidhyasagar3";
  public static final String AUTOMATE_KEY = "DGm7x5119gxJ3wvEQoqG";
  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

  public static void main(String[] args) throws Exception {

    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("browserName", "Safari");
    caps.setCapability("device", "iPhoneXR");
    caps.setCapability("realMobile", "true");
    caps.setCapability("os_version", "12");
    caps.setCapability("browserstack.debug", "true");
    caps.setCapability("browserstack.networkLogs", "true");
    caps.setCapability("browserstack.console", "true");
    
    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
    driver.get("https://dev.moneymartdirect.com/");
    /*WebElement element = driver.findElement(By.name("q"));

    element.sendKeys("BrowserStack");
    element.submit();*/

    System.out.println(driver.getTitle());
    driver.quit();

  }
}