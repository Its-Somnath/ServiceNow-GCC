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


	public void selectValueFromDropdown(WebDriver driver,Object o,
			String value)
	{
		gm.waitForObject(driver, (By) o);
		driver.findElement((By) o).sendKeys(value);
		gm.displayMessage("The value "+value+" has been selected in dropdown");		
	}

	public void verifyDropdownValues(WebDriver driver, By locator, String values, String separator) throws InterruptedException
	{
		gm.waitForObject(driver,  locator);
		WebElement drop_down1 =driver.findElement(locator);

		Select sel1 = new Select(drop_down1);
		drop_down1.click(); Thread.sleep(2500);
		//System.out.println("dffdfdc====="+sel1.getOptions());

		String arr[]=new String[sel1.getOptions().size()];System.out.println(arr.length);
		for(int i=0 ;i<sel1.getOptions().size()-1; i++)
		{
			System.out.println("i="+(i+1)+"-"+sel1.getOptions().get(i+1).getAttribute("text"));
			String itemVal1=sel1.getOptions().get(i+1).getAttribute("text");
			arr[i]=itemVal1;
		}

		String[] val=advancedSplit(values, separator);

		int k=0;
		for(int i=0;i<val.length;i++)
		{	
			k=0;
			for(int j=0;j<arr.length-1;j++)
			{
				if(arr[j].contains(val[i]))
					k=1;
			}
			if(k==1)
				gm.setLogMsg("Pass", "The value ="+val[i]+" is found in the dropdown!");
			else
			{
				gm.setLogMsg("Fail", "The value ="+val[i]+" cannot be found in the dropdown!");
				gm.logScreenshot("Fail", "The value ="+val[i]+" cannot be found in the dropdown!", driver);
			}
		}
	}

	public void verifyField(WebDriver driver, By locator, String fieldName)
	{
		if(gm.waitForObject(driver, locator) != null)
			gm.setLogMsg("Pass", "the Object "+fieldName+" is present");
		else
			gm.setLogMsg("Fail", "the Object "+fieldName+" is not present");
	}

	public void enterValueInLookupField(WebDriver driver, By locator, String value) throws InterruptedException
	{
		gm.waitForObject(driver,locator);
		driver.findElement(locator).click();
		driver.findElement(locator).sendKeys(value+Keys.TAB);
		Thread.sleep(4000);	
	}

	public void selectCheckbox(WebDriver driver, By locator, String fieldName)
	{
		gm.waitForObject(driver,  locator);
		driver.findElement(locator).click();
		gm.setLogMsg("Pass", "The checkbox "+fieldName+" has been checked");
	}

	public void validateDisabledField(WebDriver driver, By locator, String fieldName)
	{
		gm.waitForObject(driver, locator);
		if(driver.findElement(locator).getAttribute("readonly").equalsIgnoreCase("readonly"))
			gm.setLogMsg("Pass", "The field "+fieldName+" is a readOnly field");
		else
			gm.setLogMsg("Fail", "The field "+fieldName+" is not a readOnly field");
	}

	public void hoveringOverGroupIcon(WebDriver driver, By locator) throws InterruptedException
	{
		Actions action = new Actions(driver);  //set actions
		List<WebElement> e=driver.findElements(locator);
		for(int i=0;i<e.size();i++)
		{
			String s= e.get(i).getAttribute("href");
			if(s.contains("sysparm_record_row=1&"))
			{
				action = new Actions(driver);  //set actions
				action.moveToElement(e.get(i)).build().perform();  //hover over i icon.
				Thread.sleep(4000);
				gm.logScreenshot("Info", "The info icon is hovered over", driver);
			}
		}
	}



	public void setText(WebDriver driver, By locator, String text) throws InterruptedException
	{
		gm.waitForObject(driver, locator);
		driver.findElement(locator).sendKeys(text);
		Thread.sleep(2000);
	}

	public void selectValueFromDropdown(WebDriver driver, By locator, String value) throws InterruptedException
	{
		Select select = new Select(driver.findElement(locator));
		select.selectByVisibleText(value);
		/*driver.findElement((By) button).click();

		driver.findElement((By) textbox).sendKeys(value);
		Thread.sleep(1000);

		List<WebElement> list1 = driver.findElements((By)locator);
		for(WebElement ele: list1)
		{
			System.out.println("****** Value ********"+ele.getText());
			if(ele.getText().toString().contains("No matches found"))
			{
				gm.setLogMsg("fail", value+" not found in the list");
				break;
			}
			else
			{
				if(ele.getText().toString().equals(value))
				{
					ele.click();
					break;
				}
			}
		}*/
	}

	public void checkIncidentList(WebDriver driver, Object menuName, String menu)
	{
		if(driver.findElement((By)menuName)!= null)
		{
			gm.setLogMsg("pass", menu+" is Ppesent");
		}
		else
		{
			gm.setLogMsg("fail", menu+" not present");
		}  
	}
	/**
	 * This method will check the fields label of the form
	 * */
	public void verifyExistingChange(WebDriver driver, Object obj, String fields )
	{
		String [] field = fields.split(",");
		driver.switchTo().frame("gsft_main");
		List<WebElement> list = driver.findElements((By) obj);
		System.out.println("List size ###########"+list.size());
		for (int j = 0; j < field.length; j++) {
			boolean flag = false;
			System.out.println("field is "+field[j]);
			for(int i = 0; i < list.size(); i++)
			{
				if(field[j].equals(list.get(i).getText().toString()))
				{
					System.out.println(field[j]+" is displayed");
					gm.setLogMsg("pass", field[j]+" is displayed");
					flag=true;
					break;
				}
			}
			if(!flag)
			{
				System.out.println(field[j]+" not present");
				gm.setLogMsg("fail", field[j]+" not present");
			}
		}
	}

	public String getDate(String days)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));

		if(days.contains("="))
		{
			int day=Integer.parseInt(days.split("=")[1]);
			cal.add(Calendar.DATE, day);
			System.out.println(dateFormat.format(cal.getTime()));
			return dateFormat.format(cal.getTime());
		}
		else
			return dateFormat.format(cal.getTime());
	}
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

	public boolean elementExist(WebDriver driver, By locator){
		boolean exist = false;
		try{
			driver.findElement(locator);
			exist=true;
		}
		catch(Exception e){
			exist=false;
		}
		return exist;
	} 
	public void checkMessage(WebDriver driver, By locator, String message)
	{
		gm.waitForObject(driver, locator);
		elementExist(driver, locator);
	}

	public String[] advancedSplit(String items, String separator)
	{
		return items.split(separator);
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
