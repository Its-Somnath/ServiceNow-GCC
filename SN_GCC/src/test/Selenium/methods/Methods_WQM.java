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

	public void createRequest(WebDriver driver, String requestedFor,
			String country, String description) throws InterruptedException
	{
		try{

			System.out.println("service catalog");
		//	switchToLeftFrame(driver);   //move reference to left of frame
			gm.waitForObject(driver,HomePage.link_serviceCatalog);
			driver.findElement(HomePage.link_serviceCatalog).click(); 
			Thread.sleep(4000);driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			gm.setLogMsg("Pass", "The link Service Catalog is clicked");
			switchToMainFrame(driver);

										// **********    this block may be required to change as catalog names will change ************************
			gm.waitForObject(driver, HomePage.table_catalog);
			driver.findElement(HomePage.table_catalog).click();
			Thread.sleep(4000);driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			driver.findElement(HomePage.SIQ).click();
			gm.setLogMsg("Pass", "The link for Standard Invoice Query catalog is clicked");
			Thread.sleep(8000); 	

			/*	System.out.println("menu clicked");
		Thread.sleep(18000);
		System.out.println("menu clicked");
		driver.findElement(By.xpath("//*[text()='Finance and Accounting Services']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[text()='Standard Invoice Query']")).click();
		Thread.sleep(8000);*/
			System.out.println("%%%%%%%%%%%%%");
			driver.findElement(HomePage.requestedFor).sendKeys(requestedFor);
			driver.findElement(HomePage.country).sendKeys(country);
			driver.findElement(HomePage.description).sendKeys(description);
			gm.logScreenshot("Pass", "Filled in values for Requested For, Country and Short description fields", driver);

			driver.findElement(HomePage.submitRequest).click();
			Thread.sleep(26000);driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

			String s=driver.findElement(HomePage.requestConfirmationMessage).getText();
			System.out.println("confirmation message ="+s);
			gm.setLogMsg("Pass", "The REQUEST is created and the following message is displayed - "+ s);

			s=driver.findElement(HomePage.requestId).getText();
			System.out.println("request id = "+s); requestNumber=s;
			gm.setLogMsg("Info", "The Request Id number is ="+s);

		/*	gm.waitForObject(driver, HomePage.link_bookmark);
			driver.findElement(HomePage.link_bookmark).click();
			Thread.sleep(4000); 
			gm.setLogMsg("Info", "The request has been bookmarked"); gm.displayMessage("Manually drag the request to left most vertical pane to check if bookmark is working.");
*/
		}
		catch(Exception e)
		{
			//		driver.findElement(By.xpath("button[@text='OK']")).click();
			driver.findElement(HomePage.submitRequest).click();
			Thread.sleep(26000);driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

			String s=driver.findElement(HomePage.requestConfirmationMessage).getText();
			System.out.println("confirmation message ="+s);
			gm.setLogMsg("Pass", "The REQUEST is created and the following message is displayed - "+ s);
			gm.logScreenshot("Pass", "The REQUEST is created and the following message is displayed - "+ s, driver);

			s=driver.findElement(HomePage.requestId).getText();
			System.out.println("request id = "+s); requestNumber=s;
			gm.setLogMsg("Info", "The Request Id number is ="+s);

/*			gm.waitForObject(driver, HomePage.link_bookmark);
			driver.findElement(HomePage.link_bookmark).click();
			Thread.sleep(4000); 
			gm.setLogMsg("Info", "The request has been bookmarked");
			System.out.println("The request has been bookmarked");
			gm.logScreenshot("Pass", "The bookmarked request", driver);
*/		}
	}

	public void editRequestAndGenerateRequestItem(WebDriver driver,
			String fullDescription, String requestItem_status, String requestItem_assignmentGroup,
			String requestItem_category, String requestItem_type, String requestItem_assignedTo,
			String notes_description, String users_watchList)throws InterruptedException
	{

		driver.findElement(HomePage.requestId).click();
		Thread.sleep(6000);

		gm.waitForObject(driver, HomePage.full_description);
		gm.logScreenshot("Info", "The full description page opens up for created Request", driver);
		driver.findElement(HomePage.full_description).sendKeys(fullDescription);
		gm.setLogMsg("Pass", "Entered the full description value ="+fullDescription);
		driver.findElement(HomePage.button_update).click();
		Thread.sleep(6000);

		gm.waitForObject(driver, HomePage.requestId);
		driver.findElement(HomePage.requestId).click();
		Thread.sleep(6000);

		String s=driver.findElement(HomePage.link_requestItem).getText();
		System.out.println("The request item number is = "+s); requestItem_number=s;
		gm.logScreenshot("Pass", "The request item number is = "+s, driver);

		gm.waitForObject(driver, HomePage.link_requestItem);
		driver.findElement(HomePage.link_requestItem).click();
		Thread.sleep(8000);

		gm.waitForObject(driver, HomePage.requestItem_status);
		gm.logScreenshot("Info", "The request item description page opens up", driver);
		System.out.println("The request item description page opens up");
		driver.findElement(HomePage.requestItem_status).sendKeys(requestItem_status);
		Thread.sleep(2000);
		driver.findElement(HomePage.requestItem_assignmentGroup).clear();
		driver.findElement(HomePage.requestItem_assignmentGroup).sendKeys(requestItem_assignmentGroup);
		Thread.sleep(2000);
		driver.findElement(HomePage.requestItem_assignedTo).sendKeys(requestItem_assignedTo);
		Thread.sleep(2000);
		driver.findElement(HomePage.requestItem_category).click();
		driver.findElement(HomePage.requestItem_category).sendKeys(requestItem_category); Thread.sleep(4000);

		driver.findElement(HomePage.requestItem_type).click();Thread.sleep(5000);
		driver.findElement(HomePage.requestItem_type).sendKeys(requestItem_type);
		gm.setLogMsg("Info", "Entered the values of Status, Assignment Group, Assigned To, Category and Type fields in request item page");

		try
		{
			gm.waitForObject(driver, HomePage.tabbed_notes_link);
			System.out.println("tabbed notes found");
			driver.findElement(HomePage.tabbed_notes_link).click();
			System.out.println("tabbed notes clicked");
			Thread.sleep(2000);

		}
		catch(Exception e)
		{
			gm.setLogMsg("Info", "The tabbed mode was not set initially, now setting it to tabbed mode");
			System.out.println("The tabbed mode was not set initially, now setting it to tabbed mode");

			gm.waitForObject(driver, HomePage.Forms_tab);
			driver.findElement(HomePage.Forms_tab).click();
			gm.waitForObject(driver, HomePage.tabbedForms);
			driver.findElement(HomePage.tabbedForms).click();
			gm.setLogMsg("Info", "The tabbed forms is changed");gm.logScreenshot("Info", "The tabbed forms is changed", driver);

			driver.findElement(HomePage.closePopup).click();


			driver.findElement(HomePage.tabbed_notes_link).click(); 
			Thread.sleep(2000);
		}


		gm.waitForObject(driver, HomePage.requestItem_notes_description);
		driver.findElement(HomePage.requestItem_notes_description).sendKeys(notes_description);
		gm.setLogMsg("Info", "Entered the notes description also");

		driver.findElement(HomePage.requestItem_notes_editWatchList).click();Thread.sleep(2000);
		String[] sl=users_watchList.split(",");
		for(int i=0;i<sl.length;i++)
		{
			driver.findElement(HomePage.requestItem_notes_addEmail).sendKeys(sl[i]+Keys.ENTER);
		}

		driver.findElement(HomePage.requestItem_notes_lock).click();
		gm.logScreenshot("Info", "The user watch list is added and locked to the notes section", driver);


		driver.findElement(HomePage.button_update).click();
		Thread.sleep(6000);

		System.out.println("requestNumber= "+requestNumber);gm.setLogMsg("Info", "requestNumber= "+requestNumber);
		System.out.println("requestItem_number= "+requestItem_number);gm.setLogMsg("Info", "requestItem_number= "+requestItem_number);
		gm.setLogMsg("Info", "The request item page is filled up");
	}


	public void searchRequestNumber(WebDriver driver, String request_Number) throws InterruptedException
	{
	//	switchToLeftFrame(driver);   //move reference to left of frame
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("Requests"+Keys.ENTER);Thread.sleep(4000);   //click on filter textbox	

		driver.findElement(By.xpath("//a[@id='17e0b4990a04bf15013f9590408c419a']")).click(); //click on Requests link
		Thread.sleep(12000);

		switchToMainFrame(driver);  //move reference to main frame

		Actions action = new Actions(driver);  //set actions

		WebDriverWait wait = new WebDriverWait(driver, 60);  

		//***** LOGICS ****
		//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='385f673d371752409d752ea843990e4a_text']")));      //logic to find search text box
		//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Search']/following-sibling::Section/INPUT[1]"))); // logic to find next sibling

		/*	WebElement element= driver.findElement(By.xpath("//label[text()='Search']/following-sibling::Section/INPUT[1]"));
		action.moveToElement(element).sendKeys(request_Number+Keys.ENTER).perform();*/


		action.sendKeys(request_Number+Keys.ENTER).perform();  //enter the request id to search
		Thread.sleep(4000);
		gm.setLogMsg("Info", "Entered the request number in search textbox "+request_Number);

		try{
			wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+request_Number+"\']")));  //verify the presence of request
			gm.logScreenshot("Pass", "The request number ="+request_Number+" is found !", driver);

			List<WebElement> e=driver.findElements(By.xpath("//a[@class='btn btn-icon table-btn-lg icon-info list_popup']"));
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
		catch (Exception e)
		{
			gm.setLogMsg("Fail", "The request number ="+request_Number+" cannot be found !");
			gm.logScreenshot("Fail", "The request number ="+request_Number+" cannot be found !", driver);
		}


	}

	public void sendEmail(WebDriver driver, String request_Number,
			String email_From, String email_subject, String email_message,
			String selectEmailTemplate, String email_templateName) throws InterruptedException
	{
		Actions action = new Actions(driver);  //set actions

		WebElement element= driver.findElement(By.xpath("//a[text()='"+request_Number+"\']"));
		action.moveToElement(element).click().perform(); 
		Thread.sleep(16000);


		gm.waitForObject(driver, HomePage.link_requestItem);
		driver.findElement(HomePage.link_requestItem).click();
		Thread.sleep(8000);

		element =driver.findElement(HomePage.requestItem_header);
		action = new Actions(driver).contextClick(element);
		action.build().perform();  //right click and try to click on send custom email link 

		Thread.sleep(2000);
		driver.findElement(HomePage.sendEmail).click();	//send custom email link 
		Thread.sleep(4000);

		WebDriverWait wait = new WebDriverWait(driver, 60);  
		wait.until(ExpectedConditions.visibilityOfElementLocated(HomePage.email_From));  //verify the presence of From field

		gm.logScreenshot("Info", "The custom email page is opened", driver);
		element= driver.findElement(By.xpath("//input[@id='sys_display.u_email_client.u_item']"));
		String itemNumber = element.getAttribute("value");

		driver.findElement(HomePage.email_From).sendKeys(email_From); Thread.sleep(2000); //email from field

		// ****** insert code for To field ********
		gm.waitForObject(driver, HomePage.email_To);
		driver.findElement(HomePage.email_To).click();
				
		gm.waitForObject(driver, HomePage.email_input);
		driver.findElement(HomePage.email_input).click();
		driver.findElement(HomePage.email_input).sendKeys("a.f.srivastava"+Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(HomePage.email_To).click();
			
		
		
		if(selectEmailTemplate.equalsIgnoreCase("yes"))
			driver.findElement(HomePage.email_template).sendKeys(email_templateName);  //template field
		Thread.sleep(4000);

		if( ! driver.findElement(HomePage.email_ccWatchList).isSelected())
		{
			driver.findElement(HomePage.email_ccWatchList).click(); 	//click on watch list checkbox
			gm.displayMessage("Click the watch List checkbox if missed");
		}

		driver.findElement(HomePage.email_subject).click(); //email subject field
		driver.findElement(HomePage.email_subject).sendKeys(itemNumber+"  "+email_subject+Keys.TAB); Thread.sleep(2000);

		if(selectEmailTemplate.equalsIgnoreCase("no"))
			action.sendKeys(itemNumber+" "+email_message).perform();
		gm.logScreenshot("Info", "The user has entered details in the email page", driver);



		System.out.println("entered message");
		try {

			System.out.println("about to click send button first time");
			driver.findElement(HomePage.email_sendButton).click();
			Thread.sleep(6000);
			System.out.println("clicked send button first time");

			gm.setLogMsg("Info", "The user has sent the custom message");
			gm.waitForObject(driver, HomePage.requestItem_assignmentGroup);
			wait.until(ExpectedConditions.visibilityOfElementLocated(HomePage.requestItem_assignmentGroup));  

			//again checking if UI is in tabbed mode, then go to Notes >> activities
			try
			{
				gm.waitForObject(driver, HomePage.tabbed_notes_link);
				System.out.println("tabbed notes found");
				driver.findElement(HomePage.tabbed_notes_link).click();
				System.out.println("tabbed notes clicked");
				Thread.sleep(2000);

			}
			catch(Exception e)
			{
				gm.setLogMsg("Info", "The tabbed mode was not set initially, now setting it to tabbed mode");
				System.out.println("The tabbed mode was not set initially, now setting it to tabbed mode");

				gm.waitForObject(driver, HomePage.Forms_tab);
				driver.findElement(HomePage.Forms_tab).click();
				gm.waitForObject(driver, HomePage.tabbedForms);
				driver.findElement(HomePage.tabbedForms).click();
				gm.setLogMsg("Info", "The tabbed forms is changed");gm.logScreenshot("Info", "The tabbed forms is changed", driver);

				driver.findElement(HomePage.closePopup).click();


				driver.findElement(HomePage.tabbed_notes_link).click(); 
				Thread.sleep(2000);
			}

			String s=driver.findElement(By.xpath("//*[@name='activity_0_div']/div[1]")).getText();
			System.out.println(s);
			if(s.contains(email_subject))
				gm.logScreenshot("Pass", "The sent custom email is updated in the activities section", driver);
			else
				gm.setLogMsg("Fail", "The sent custom email is not updated in the activities section");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("exception raised");
			gm.logScreenshot("Fail", "Email cannot be sent, please recheck for missing fields", driver);
			driver.findElement(HomePage.email_sendButton).click();
			System.out.println("button reclicked");
		}
	}


	public void searchRequestItem(WebDriver driver, String requestItem_number) throws InterruptedException
	{

	//	switchToLeftFrame(driver);   //move reference to left of frame
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("Items"+Keys.ENTER);Thread.sleep(4000);   //click on filter textbox

		driver.findElement(HomePage.link_items).click(); //click on Items link
		Thread.sleep(12000);	
		switchToMainFrame(driver);  //move reference to main frame

		Actions action = new Actions(driver);  //set actions

		WebDriverWait wait = new WebDriverWait(driver, 60);  

		action.sendKeys(requestItem_number+Keys.ENTER).perform();  //enter the request id to search
		Thread.sleep(4000);
		gm.setLogMsg("Info", "Entered the request-item number in search textbox "+requestItem_number);

		try
		{
			wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+requestItem_number+"\']")));  //verify the presence of request-item
			gm.logScreenshot("Pass", "The request-item number ="+requestItem_number+" is found !", driver);

		}
		catch(Exception e)
		{
			gm.setLogMsg("Fail", "The request-item number ="+requestItem_number+" cannot be found !");
			gm.logScreenshot("Fail", "The request-item number ="+requestItem_number+" cannot be found !", driver);
		}
	
		gm.displayMessage("Click on the 'Requested For' link to check if its working and taking us to correct user's page");
		gm.displayMessage("Go back to previous page and verify the filter functionality > check if any of the filters is working.");
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
		
		if(action.equalsIgnoreCase("search"))
		{
			System.out.println("Search is in progress...");
			driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("GCC"+Keys.ENTER);Thread.sleep(4000);  
			WebElement ele=gm.waitForObject(driver, GCCIncidentPage.link_listOfGCC );
			ele.click();
		}
		if(action.equalsIgnoreCase("create report"))
		{
			System.out.println("Report creation in progress...");
			driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("Report"+Keys.ENTER);Thread.sleep(4000);  
			WebElement ele=gm.waitForObject(driver, GCCIncidentPage.link_createNewReport );
			ele.click();
		}
		if(action.equalsIgnoreCase("run report"))
		{
			System.out.println("Running the report...");
			driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("Report"+Keys.ENTER);Thread.sleep(4000);  
			WebElement ele=gm.waitForObject(driver, GCCIncidentPage.link_runReport );
			ele.click();
		}
	//	navigateToElement(driver, ele);
		switchToMainFrame(driver);
		Thread.sleep(4000);
	}
}
