package methods;


import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import org.apache.poi.hssf.record.chart.UnitsRecord;
import org.openqa.selenium.Alert;
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

public class Methods_WQM {

	GenericMethods gm = new GenericMethods();
	methods_common mcmn = new methods_common();
	public static String requestNumber="";
	public static String requestItem_number="";

	public void wqm_login(WebDriver driver,String appUrl,String userName,String password,
			String anotherUserName) throws InterruptedException
	{
		System.out.println("####################");
		switchToMainFrame(driver);   //move reference to main frame
		gm.waitForObject(driver,LoginPage.username);

		driver.findElement(LoginPage.username).sendKeys(userName);
		driver.findElement(LoginPage.password).sendKeys(password);
		driver.findElement(LoginPage.btnSignIn).click();

		Thread.sleep(6000);
		gm.logScreenshot("Pass", "Successfully navigated to ServiceNow Home page", driver);	
		if(anotherUserName!="")
			impersonateUser(driver, anotherUserName);
	//	gm.displayMessage("verify favorite items functonality");
		Thread.sleep(10000);
	}

	public void impersonateUser(WebDriver driver, String anotherUserName) throws InterruptedException
	{
		System.out.println("inside impersonate method");
		gm.waitForObject(driver, LoginPage.userImpersonate);
		driver.findElement(LoginPage.userImpersonate).click();
		driver.findElement(LoginPage.link_impersonate).click();
		
		gm.waitForObject(driver, LoginPage.text_anotherUserName);
		driver.findElement(LoginPage.text_anotherUserName).click();
		driver.findElement(LoginPage.text_anotherUserName1).sendKeys(anotherUserName+Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(LoginPage.text_anotherUserName1).sendKeys(Keys.ENTER);
		Thread.sleep(6000);
	}
	
	public void changeDomain(WebDriver driver, String domain) throws InterruptedException
	{
		gm.waitForObject(driver,HomePage.changeDomain);
		driver.findElement(HomePage.changeDomain).click();
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		
		driver.findElement(HomePage.domainSelect).click();

		driver.findElement(HomePage.domainSelect).sendKeys(domain);  
		gm.setLogMsg("Info", "The domain is changed to "+domain);
		driver.findElement(HomePage.closeDomain).click();
		System.out.println("%%%%%%%%%%%%%");
		Thread.sleep(18000);
		gm.displayMessage("Close this popup");
	//	driver.findElement(HomePage.closePopup).click();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		
/*
		gm.waitForObject(driver, HomePage.Forms_tab);
		driver.findElement(HomePage.Forms_tab).click();
		gm.waitForObject(driver, HomePage.tabbedForms);
		driver.findElement(HomePage.tabbedForms).click();
		gm.setLogMsg("Info", "The tabbed forms is changed");gm.logScreenshot("Info", "The tabbed forms is changed", driver);

		driver.findElement(HomePage.closePopup).click();
*/	
		//code not working.
		/*Select sel =new Select(driver.findElement(HomePage.domainSelect));
		sel.selectByValue(domain);
		Thread.sleep(2000);*/


	}

	
	/**
	 * Switches to the left nav frame of ServiceNow
	 */
	public void switchToLeftFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
		driver.switchTo().frame("gsft_nav");//nav_west
	}

	/**
	 * Switches to the main frame of ServiceNow
	 */
	public void switchToMainFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
		driver.switchTo().frame("gsft_main"); //edge_center
		
	}

	/**
	 * Switches frames to the left nav, clicks on a given element, then switches back to the main frame
	 * @param element A link element to click on
	 */
	public void navigateToElement(WebDriver driver, WebElement element)
	{
		switchToLeftFrame(driver);
		element.click();
		switchToMainFrame(driver);
	}

	public void navigation(WebDriver driver, String action) throws InterruptedException
	{
		driver.navigate().refresh();
		if(mcmn.isAlertPresent(driver))
		{
			Alert alert = driver.switchTo().alert();
			if(alert.getText().contains("Changes"))
				alert.accept();
		}
	//	switchToLeftFrame(driver);
		gm.waitForObject(driver, By.xpath("//input[@id='filter']"));
		 //click on filter textbox
	//	WebElement ele=gm.waitForObject(driver, HomePage.panel_incident );
		// 	navigateToElement(driver, ele);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		
		if(action.equalsIgnoreCase("create"))
		{
			System.out.println("Creation in progress...");
			driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("GCC"+Keys.ENTER);Thread.sleep(4000);  
			WebElement ele=gm.waitForObject(driver, HomePage.link_createNewGCC );
			ele.click();
		}
		
		
		
	//	navigateToElement(driver, ele);
		switchToMainFrame(driver);
		Thread.sleep(4000);
	}
}
