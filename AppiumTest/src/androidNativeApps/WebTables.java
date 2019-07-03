package androidNativeApps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;




public class WebTables {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Test
	public void Table1() {
		
		/*List<FileFields> Field = new ArrayList<FileFields>();
		String line = "100122|TUCSON ELECTRIC POWER CO|10-K|2015-02-19|edgar/data/100122/0000100122-15-000003.txt";
		String[] details = line.split("\\|",6);
		String CIK1 = details[0];
		String CompanyName1 = details[1];
		String FormType1 = details[2];
		String DateFiled1 = details[3];
		String Filename1 = details[4];
		System.out.println("Line is: "+line);
		System.out.println("details: "+details);
		System.out.println("CIK: "+CIK1);
		System.out.println("CompanyName: "+CompanyName1);
		System.out.println("FormType: "+FormType1);
		System.out.println("DateFiled: "+DateFiled1);
		System.out.println("Filename: "+Filename1);
*/
		
		
		driver.get("https://www.sec.gov/Archives/edgar/data/1623613/000162361315000029");
		List rows = driver.findElements(By.xpath("//div[@id='main-content']/table/tbody/tr"));
		List Cols = driver.findElements(By.xpath("//div[@id='main-content']/table/tbody/tr[2]/td"));
		//System.out.println("rows count "+rows.size());
		//System.out.println("Cols count "+Cols.size());
		for(int i=2;i<=rows.size();i++)
		{
				
				String Xpath = "//div[@id='main-content']/table/tbody/tr["+i+"]/td[1]";
				String Xpath1 = "//div[@id='main-content']/table/tbody/tr["+i+"]/td[3]";
				String text = driver.findElement(By.xpath(Xpath)).getText();
				if(text.equals("ex99-1.htm"))
				{
					String Value = driver.findElement(By.xpath(Xpath1)).getText();
					System.out.println("Time & Date: "+Value);
				}
				//System.out.println("Texts:"+text);
				int len = text.length();
				//System.out.println("Lenth of Texts: "+len);
				StringBuffer str = new StringBuffer(text);
				if(len>0) {
				if((str.substring(len-4)).equals(".htm")||(str.substring(len-4)).equals(".xml"))
						{
							System.out.println("Satya:------- "+text);
							String Value = driver.findElement(By.xpath(Xpath1)).getText();
							Value = Value.replaceAll("\\s","_");
							Value = Value.replaceAll(":","");
							Value = Value.replaceAll("-","");
							System.out.println("Time & Date: "+Value);
						}
				}
			
		}
		
		
		
		// List<WebElement> links = driver.findElements(By.tagName("a"));
		//div[@id='main-content']/table/tbody/tr[5]/td[3]
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
