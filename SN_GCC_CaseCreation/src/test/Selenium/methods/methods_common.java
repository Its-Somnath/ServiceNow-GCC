package methods;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import org.apache.poi.hssf.record.chart.UnitsRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


import com.google.inject.Key;
import com.google.inject.internal.BytecodeGen.Visibility;

import pageObjects.*;
import driver.*;

public class methods_common {

	GenericMethods gm = new GenericMethods();
	public static String Homewindow = null; 
	//	public static String requestNumber="";
	//	public static String requestItem_number="";


	
	
	public void enterValueInLookupField(WebDriver driver, By locator, String value) throws InterruptedException
	{
		gm.waitForObject(driver,locator);
		driver.findElement(locator).click();
		driver.findElement(locator).sendKeys(value+Keys.TAB);
		Thread.sleep(4000);	
	}

	

	public void selectValueFromDropdown(WebDriver driver, By locator, String value) throws InterruptedException
	{
		Select select = new Select(driver.findElement(locator));
		select.selectByVisibleText(value);
		
	}

	
	/**
	 * This method will check the fields label of the form
	 * */
	
	
	public void switchToNewWindow(WebDriver driver) throws InterruptedException
	{
		System.out.println(Homewindow);
		Homewindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> iterate = windows.iterator();
		while (iterate.hasNext())
		{

			String CurrentWindow = iterate.next().toString();
			if (!CurrentWindow.equals(Homewindow))
			{
				driver.switchTo().window(CurrentWindow);
				System.out.println(Homewindow);
				System.out.println(CurrentWindow);
				/*
				 * Select select = new Select(
				 * CrossBrowser.driver.findElement(By.xpath(TestBase.SPW.
				 * getProperty(field))));
				 * select.selectByVisibleText(selectValue);
				 */

			}

		}
	}

	public void switchBackToPreviousWindow(WebDriver driver)throws InterruptedException
	{

		//CrossBrowser.driver.close();
		driver.switchTo().window(Homewindow);
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> iterate = windows.iterator();
		System.out.println(Homewindow);
	}

		
	public boolean isAlertPresent(WebDriver driver) {
	    try {
	        driver.switchTo().alert();
	        return true;
	    } // try
	    catch (Exception e) {
	        return false;
	    } // catch
	}

}
