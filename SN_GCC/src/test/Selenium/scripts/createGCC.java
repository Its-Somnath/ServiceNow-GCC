package scripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import methods.Methods_WQM;
import methods.methods_Cadence;
import methods.methods_GCC;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import pageObjects.HomePage;
import pageObjects.GCCIncidentPage;
import pageObjects.LoginPage;
import driver.EnvironmentVariables;
import driver.GenericMethods;

public class createGCC
{
	GenericMethods gm = new GenericMethods();
	Methods_WQM m = new Methods_WQM();
	methods_Cadence mc=new methods_Cadence();
	methods_GCC ma=new methods_GCC();
	
	
	public void main(WebDriver driver, String Client_Name,String ContractName,String SDL,String SDO, String SubOffering, String IncidentType,
			String IncidentDescription,String Priority, String DateOfIncident, String TargetClosureDate) throws IOException, Exception
	{
		
		m.navigation(driver, "create"); //navigate to create new incident page
		String incidentNumber = ma.createGCCIncident(driver, Client_Name,ContractName,SDL, SDO,SubOffering, IncidentType, IncidentDescription,
				 Priority, DateOfIncident, TargetClosureDate); //fill up the values in fields
		Thread.sleep(4000);
		
		/*ma.searchIncident(driver, incidentNumber); //search the created incident
		ma.modifyIncident(driver, state, "", "", "","Update"); //move incident to ACTIVE
		
		
		ma.searchIncident(driver, incidentNumber);
		ma.modifyIncident(driver, "Resolved", assignedTo, "Demo Res Cat", closeCode, "Update"); //move incident to RESOLVED*/
	}	
}