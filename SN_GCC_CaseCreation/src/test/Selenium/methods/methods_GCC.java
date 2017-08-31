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

	
}
