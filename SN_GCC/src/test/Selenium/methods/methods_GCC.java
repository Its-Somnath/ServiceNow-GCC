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

public class methods_GCC {

	GenericMethods gm = new GenericMethods();
	Methods_WQM mwqm=new Methods_WQM();
	methods_common mcmn=new methods_common();

	//	public static String requestNumber="";
	//	public static String requestItem_number="";
	public void verifyGCCIncident(WebDriver driver, String Flag, String Client_Name,
			String ContractName,String SDL, String Offering,String SubOfferingList,
			String SubOffering,String IncidentTypeList) throws InterruptedException
	{
		driver.findElement(GCCIncidentPage.textbox_client).sendKeys(Client_Name);
		Thread.sleep(2000);
		String mainWindow=driver.getWindowHandle();
		driver.findElement(GCCIncidentPage.button_lookupClient).click();
		mcmn.switchToNewWindow(driver);
		Thread.sleep(4000);
		driver.findElement(GCCIncidentPage.link_clientName(SDL,ContractName, Client_Name)).click();
		mcmn.switchBackToPreviousWindow(driver);
		driver.switchTo().frame("gsft_main");

		//driver.switchTo().defaultContent();
		Thread.sleep(1000);
		String offering1 = driver.findElement(GCCIncidentPage.dropdown_offering).getText();
		if(offering1.equalsIgnoreCase(Offering))
		{
			gm.setLogMsg("Pass", "The offering is mapped correctly");
		}
		else
		{
			gm.logScreenshot("Fail", "The offering is not as expected. Expected : <<"+Offering+">> but found <<"+offering1+">>", driver);
		}
		//System.out.println("Selected Offering : "+offer);
		mcmn.verifyDropdownValues(driver, GCCIncidentPage.dropdown_subOffering, SubOfferingList, ",");
		mcmn.selectValueFromDropdown(driver, GCCIncidentPage.dropdown_subOffering, SubOffering);
		Thread.sleep(3000);
		mcmn.verifyDropdownValues(driver, GCCIncidentPage.dropdown_incidentType, IncidentTypeList, ">");
	}

	public String createGCCIncident(WebDriver driver, String Client_Name,String ContractName, 
			String SDL,	String SDO,String SubOffering, String IncidentType, String IncidentDescription,
			String Priority, String DateOfIncident, String TargetClosureDate) throws InterruptedException
	{
		gm.waitForObject(driver, GCCIncidentPage.textbox_incidentNumber);
		String incidentNumber = driver.findElement(GCCIncidentPage.textbox_incidentNumber).getAttribute("value").toString();
		System.out.println(incidentNumber);

		gm.setLogMsg("Info", "The INCIDENT number is = "+incidentNumber);
		driver.findElement(GCCIncidentPage.textbox_client).sendKeys(Client_Name);
		Thread.sleep(2000);
		String mainWindow=driver.getWindowHandle();
		driver.findElement(GCCIncidentPage.button_lookupClient).click();
		mcmn.switchToNewWindow(driver);
		Thread.sleep(4000);
		driver.findElement(GCCIncidentPage.link_clientName(SDL,ContractName, Client_Name)).click();
		mcmn.switchBackToPreviousWindow(driver);
		driver.switchTo().frame("gsft_main");

		//driver.switchTo().defaultContent();
		Thread.sleep(1000);
		//String offer = driver.findElement(GCCIncidentPage.dropdown_offering).getText();
		//System.out.println("Selected Offering : "+offer);
		mcmn.selectValueFromDropdown(driver, GCCIncidentPage.dropdown_subOffering, SubOffering);
		Thread.sleep(2000);
		if(SubOffering.equalsIgnoreCase("Other"))
		{
			driver.findElement(GCCIncidentPage.textbox_other_subOffering).sendKeys("TEST_Sub Offering");
		}
		mcmn.selectValueFromDropdown(driver, GCCIncidentPage.dropdown_incidentType, IncidentType);
		Thread.sleep(2000);
		if(IncidentType.equalsIgnoreCase("Other"))
		{
			driver.findElement(GCCIncidentPage.textbox_other_incidentType).sendKeys("TEST_Incident Type");
		}		
		driver.findElement(GCCIncidentPage.textbox_description).sendKeys(IncidentDescription);
		Thread.sleep(3000);
		String SDOText = driver.findElement(GCCIncidentPage.textbox_SDO).getText();
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		System.out.println("SDO:"+SDOText);
		if(SDOText.equals(""))
		{
			driver.findElement(GCCIncidentPage.textbox_SDO).sendKeys(selectAll);
			driver.findElement(GCCIncidentPage.textbox_SDO).sendKeys(SDO);
		}
		String TargetClosureText = driver.findElement(GCCIncidentPage.textbox_closureDate).getText();
		System.out.println("Target Closure Date : "+TargetClosureText);
		if(TargetClosureText.equals(""))
		{
			//driver.findElement(GCCIncidentPage.textbox_closureDate).sendKeys(Keys.CONTROL + Keys.TargetClosureDate);
			
			driver.findElement(GCCIncidentPage.textbox_closureDate).sendKeys(selectAll);
			driver.findElement(GCCIncidentPage.textbox_closureDate).sendKeys(TargetClosureDate);
		}
		mcmn.selectValueFromDropdown(driver, GCCIncidentPage.dropdown_priority, Priority);
		driver.findElement(GCCIncidentPage.textbox_incidentDate).sendKeys(DateOfIncident);
		driver.findElement(GCCIncidentPage.button_saveIncident).click();
		Alert alert1 = driver.switchTo().alert();
		if(alert1.getText().contains("Client information"))
		{
			alert1.accept();
			if(Priority.contains("Critical")||Priority.contains("High"))
			{
				Alert alert2 = driver.switchTo().alert();
				if((alert2.getText().contains("Critical Incident"))||alert2.getText().contains("High Incident"))
				{
					alert2.accept();
				}
			}
			System.out.println("Incident " +incidentNumber+" has been created");
			gm.setLogMsg("Pass", "Incident " +incidentNumber+" has been created for client - "+Client_Name);
		}
		else
		{
			System.out.println("The Incident could not be created. Check for errors.");
			gm.logScreenshot("Fail", "The Incident could not be created for the client - "+Client_Name+". Check the error message.", driver);
		}
		
		Thread.sleep(8000);
		return incidentNumber;
	}

	public void searchIncident(WebDriver driver, String incidentNumber) throws InterruptedException
	{
		mwqm.navigation(driver, "search");
		Actions action = new Actions(driver);  //set actions

		WebDriverWait wait = new WebDriverWait(driver, 60);  
		action.sendKeys(incidentNumber+Keys.ENTER).perform();  //enter the request id to search
		Thread.sleep(4000);

		if(!gm.waitForObject(driver, By.xpath("//a[text()='"+incidentNumber+"']")).equals(null))
		{
			gm.logScreenshot("Pass", "The searched incident = "+incidentNumber+" is found !", driver);
			driver.findElement(By.xpath("//a[text()='"+incidentNumber+"']")).click();
			Thread.sleep(4000);
		}
		else
		{
			System.out.println("Failed...Incident not found");
		}

	}

	public void modifyIncident(WebDriver driver, String state, String assignedTo,
			String resolution, String closeCode, String button) throws InterruptedException
	{
		if(!state.equals(null))
		{
			gm.waitForObject(driver, GCCIncidentPage.dropdown_incidentState);
			driver.findElement(GCCIncidentPage.dropdown_incidentState).sendKeys(state);
			gm.setLogMsg("Info", "The incident state is now changed to "+state);
		}

		if(!assignedTo.equals(null))
		{
			gm.waitForObject(driver, GCCIncidentPage.textbox_deliveryLocation);
			driver.findElement(GCCIncidentPage.textbox_deliveryLocation).sendKeys(assignedTo);
			gm.setLogMsg("Info", "The assigned to field is filled with value - "+assignedTo);
		}

		if(state.equals("Resolved"))
		{
			driver.findElement(GCCIncidentPage.tabbed_closureInformation_link).click();Thread.sleep(2000);


			driver.findElement(GCCIncidentPage.textbox_resolution).sendKeys(resolution);
			driver.findElement(GCCIncidentPage.dropdown_closeCode).sendKeys(closeCode);


			driver.findElement(GCCIncidentPage.textbox_closeNotes).sendKeys("closed");
			driver.findElement(GCCIncidentPage.tab_notes).click();
			driver.findElement(GCCIncidentPage.textbox_additionalComments).sendKeys("Test_SQE");
		}

		if(state.equals("Closed"))
		{
			driver.findElement(GCCIncidentPage.tabbed_closureInformation_link).click();Thread.sleep(2000);

			WebElement elm = driver.findElement(GCCIncidentPage.checkbox_closedOnInitial);
			driver.findElement(GCCIncidentPage.textbox_resolution).sendKeys(resolution);
			driver.findElement(GCCIncidentPage.dropdown_closeCode).sendKeys(closeCode);			
			driver.findElement(GCCIncidentPage.textbox_closeNotes).sendKeys("closed");
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", elm);
			Thread.sleep(2000);


		}

		if(button.equals("Update"))
		{
			driver.findElement(GCCIncidentPage.button_updateIncident).click();
			Thread.sleep(4000);
		}
		else
		{
			gm.waitForObject(driver, HomePage.requestItem_header);
			WebElement element =driver.findElement(HomePage.requestItem_header);
			Actions action = new Actions(driver).contextClick(element);
			action.build().perform();  //right click and try to click on available options 
			Thread.sleep(2000);
			driver.findElement(GCCIncidentPage.saveIncident).click();	//click Save link
			Thread.sleep(6000);
		}
	}

	public void selectOptionsAtRightClickHamburger(WebDriver driver, String option) throws InterruptedException
	{
		gm.waitForObject(driver, HomePage.requestItem_header);
		WebElement element =driver.findElement(HomePage.requestItem_header);
		Actions action = new Actions(driver).contextClick(element);
		action.build().perform();  //right click and try to click on available options 
		Thread.sleep(2000);

		if(option.equalsIgnoreCase("escalate incident"))
		{
			driver.findElement(GCCIncidentPage.escalateIncident).click();	//click escalate incident link 
			Thread.sleep(4000);
			driver.findElement(GCCIncidentPage.tabbed_stats_link).click();
			String s= driver.findElement(GCCIncidentPage.textbox_escalatedIncidentCount).getAttribute("value").toString();
			gm.setLogMsg("Info", "The escalated count of Incident is "+s);
		}

		if(option.equalsIgnoreCase("create problem"))
		{
			driver.findElement(GCCIncidentPage.createProblem).click(); //click create problem link
			Thread.sleep(4000);
			gm.waitForObject(driver, GCCIncidentPage.textbox_assignmentGroup_problem);
			Thread.sleep(2000);
		}

		if(option.equalsIgnoreCase("create change"))
		{
			driver.findElement(GCCIncidentPage.createChange).click(); //click create Normal Change link
			Thread.sleep(4000);
			gm.waitForObject(driver, GCCIncidentPage.textbox_categorization_change);
		}

	}

	public void checkReopenCount(WebDriver driver) throws InterruptedException
	{
		gm.waitForObject(driver, GCCIncidentPage.tabbed_stats_link);
		driver.findElement(GCCIncidentPage.tabbed_stats_link).click();
		Thread.sleep(2000);

		String s=driver.findElement(GCCIncidentPage.textbox_incidentReopenCount).getAttribute("value").toString();
		if(s.equals("1"))
			gm.logScreenshot("Pass", "The Reopen count of incident has increased", driver);
		else gm.logScreenshot("Fail", "The Reopen count of incident has not increased", driver);
	}

	public void createIncidentTask(WebDriver driver, String reportedToVendor, String vendorResponded,
			String vendorResolved) throws InterruptedException
	{
		gm.waitForObject(driver, GCCIncidentPage.tabbed_incidentTasks_link);
		driver.findElement(GCCIncidentPage.tabbed_incidentTasks_link).click();

		gm.waitForObject(driver, GCCIncidentPage.button_newIncidentTask);
		driver.findElement(GCCIncidentPage.button_newIncidentTask).click();
		Thread.sleep(4000);

		gm.waitForObject(driver, GCCIncidentPage.textbox_description_incidentTask);
		driver.findElement(GCCIncidentPage.textbox_description_incidentTask).sendKeys("Description for incident task");

		driver.findElement(GCCIncidentPage.textbox_vendorTicket_incidentTask).sendKeys("1260");
		driver.findElement(GCCIncidentPage.textbox_reportedToVendor_incidentTask).sendKeys(reportedToVendor);
		driver.findElement(GCCIncidentPage.textbox_vendorResponded_incidentTask).sendKeys(vendorResponded);
		driver.findElement(GCCIncidentPage.textbox_vendorResolved_incidentTask).sendKeys(vendorResolved);

		driver.findElement(GCCIncidentPage.button_submit_incidentTask).click();
		Thread.sleep(4000);
		if(!gm.waitForObject(driver, GCCIncidentPage.textbox_client).equals(null))
		{
			gm.setLogMsg("Pass", "The incident task has been created");

			WebElement element =driver.findElement(HomePage.requestItem_header);
			Actions action = new Actions(driver).contextClick(element);
			action.build().perform();  //right click and try to click on available options 
			Thread.sleep(2000);
			driver.findElement(GCCIncidentPage.saveIncident).click();	//click Save link
			Thread.sleep(6000);
		}
		else 
			gm.setLogMsg("Fail", "The incident task has not been created");
	}

	public void createProblemFromIncident(WebDriver driver, String assignmentGroup) throws InterruptedException
	{
		selectOptionsAtRightClickHamburger(driver, "create problem");
		gm.logScreenshot("Info", "Problem page of this incident", driver);
		driver.findElement(GCCIncidentPage.textbox_assignmentGroup_problem).sendKeys(assignmentGroup);
		driver.findElement(GCCIncidentPage.textbox_description_problem).sendKeys("problem created from incident");
		Thread.sleep(3000);
		driver.findElement(GCCIncidentPage.button_updateIncident).click();
		gm.waitForObject(driver, GCCIncidentPage.textbox_client);
		gm.setLogMsg("Pass", "The problem has been created for this incident");
	}

	public void createChangeFromIncident(WebDriver driver, String categorization_change,
			String assignmentGroup_change) throws InterruptedException
	{
		selectOptionsAtRightClickHamburger(driver, "create change");
		gm.logScreenshot("Info", "Change page of this incident", driver);

		driver.findElement(GCCIncidentPage.textbox_categorization_change).sendKeys(categorization_change + Keys.TAB); 
		driver.findElement(GCCIncidentPage.textbox_assignmentGroup_change).sendKeys(assignmentGroup_change + Keys.TAB);
		driver.findElement(GCCIncidentPage.textbox_ownerGroup_change).sendKeys(assignmentGroup_change + Keys.TAB);

		driver.findElement(GCCIncidentPage.dropdown_HRI).sendKeys("Problem resolution"); 
		driver.findElement(GCCIncidentPage.dropdown_risk_change).sendKeys("Moderate");
		driver.findElement(GCCIncidentPage.dropdown_offering).sendKeys("Security");
		Thread.sleep(2000);
		driver.findElement(GCCIncidentPage.button_updateIncident).click();
		gm.waitForObject(driver, GCCIncidentPage.textbox_client);
		gm.setLogMsg("Pass", "The Change has been created for this incident");
	}

	public void runReport(WebDriver driver, String reportTitle)throws InterruptedException
	{
		Thread.sleep(5000);
		gm.waitForObject(driver, By.xpath("//a[text()="+reportTitle+"]"));
		driver.findElement(By.xpath("//a[text()="+reportTitle+"]")).click();
		Thread.sleep(5000);
		gm.displayMessage("click Run button to generate the report");
	}

	public void verifyPreventativeTasks(WebDriver driver, String taskList)
	{
		String tasks[]= taskList.split("#");
		System.out.println("No. of Preventative task : "+tasks.length);
		for(String s:tasks)
		{
			System.out.println("Element : " + s);
			By locator = GCCIncidentPage.chkBox_preventiveTasks(s);
			boolean exists = mcmn.elementExist(driver, locator);
			if(exists)
			{
				System.out.println(exists);
				gm.setLogMsg("Pass", "One of the preventative tasks exists");
			}
			else
			{
				gm.logScreenshot("Fail", "One of the preventative task is not present", driver);
				System.out.println(exists);
			}

		}
	}

	public void verifyCorrectiveTasks(WebDriver driver, String taskList)
	{
		String tasks[]= taskList.split("#");
		System.out.println("No. of Corrective tasks : "+tasks.length);
		for(String s:tasks)
		{
			System.out.println("Element : "+s);
			By locator = GCCIncidentPage.chkBox_correctiveTasks(s);
			boolean exists = mcmn.elementExist(driver, locator);
			if(exists)
			{
				System.out.println(exists);
				gm.setLogMsg("Pass", "One of the corrective tasks exists");
			}
			else
			{
				gm.logScreenshot("Fail", "One of the corrective task is not present", driver);
				System.out.println(exists);
			}

		}

	}
	
	public void completeCorrectiveTasks(WebDriver driver, String taskList)
	{
		String tasks[]= taskList.split("#");
		System.out.println("No. of Corrective tasks : "+tasks.length);
		for(String s:tasks)
		{
			System.out.println("Element : "+s);
			By locator = GCCIncidentPage.chkBox_correctiveTasks(s);
			boolean exists = mcmn.elementExist(driver, locator);
			if(exists)
			{
				System.out.println(exists);
				gm.setLogMsg("Pass", "One of the corrective tasks exists");
			}
			else
			{
				gm.logScreenshot("Fail", "One of the corrective task is not present", driver);
				System.out.println(exists);
			}

		}

	}
	
	public void completePreventativeTasks(WebDriver driver, String taskList)
	{
		String tasks[]= taskList.split("#");
		System.out.println("No. of Corrective tasks : "+tasks.length);
		for(String s:tasks)
		{
			System.out.println("Element : "+s);
			By locator = GCCIncidentPage.chkBox_correctiveTasks(s);
			boolean exists = mcmn.elementExist(driver, locator);
			if(exists)
			{
				System.out.println(exists);
				gm.setLogMsg("Pass", "One of the corrective tasks exists");
			}
			else
			{
				gm.logScreenshot("Fail", "One of the corrective task is not present", driver);
				System.out.println(exists);
			}

		}

	}
	
	public void watchlist(WebDriver driver, String user) throws InterruptedException
	{
		driver.findElement(GCCIncidentPage.button_edit_cause).click();
		Thread.sleep(5000);
		WebElement element = driver.findElement(GCCIncidentPage.button_add_remove_cause);
		JavascriptExecutor jse= (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();", element);
		//driver.findElement(GCCIncidentPage.button_add_remove_cause).click();
		Thread.sleep(2000);
		Alert alert1 = driver.switchTo().alert();
		alert1.accept();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
		Thread.sleep(5000);
		driver.findElement(GCCIncidentPage.textbox_collection).sendKeys(user);
		driver.findElement(GCCIncidentPage.button_Add_cause).click();
		driver.findElement(GCCIncidentPage.button_Save_cause).click();
		Thread.sleep(5000);
		//driver.findElement(GCCIncidentPage.button_moreOptions).click();
		//driver.findElement(GCCIncidentPage.button_email).click();
		driver.findElement(GCCIncidentPage.button_updateIncident).click();
				
	}
}
