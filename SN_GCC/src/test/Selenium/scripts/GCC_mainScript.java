package scripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import methods.Methods_WQM;
import methods.methods_Cadence;
import methods.methods_GCC;

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

import pageObjects.*;
import driver.EnvironmentVariables;
import driver.GenericMethods;

public class GCC_mainScript 
{
	WebDriver driver = null;
	GenericMethods gm=new GenericMethods();
	Methods_WQM m = new Methods_WQM();
	createGCC ci=new createGCC();
	verifyGCC vg = new verifyGCC();
	methods_GCC m2 = new methods_GCC();
	String sheetName = "";
	

	/*	@Before
	public void setup()
	{
		DesiredCapabilities c=new DesiredCapabilities();
		c.setBrowserName(System.getenv("SELENIUM_BROWSER"));
		c.setVersion(System.getenv("SELENIUM_VERSION"));
		c.setCapability(CapabilityType.PLATFORM,System.getenv("SELENIUM_PLATFORM"));
	//	c.setCapability("name", "atlasLogin");

	}
	 */


	/*
	 * selectEmailTemplate = yes or no
	 * email_templateName = Paid invoice-requesting remittance
							Requesting missing invoice
							Invoice blocked for Goods receipting
							Invoice blocked due to price
							Invoice has already been paid
							Assigned to Exceptions team
		users_watchList = csv list of user email ids.    eg- saket.parekh@accenture.com,a.f.srivastava@accenture.com
	 */


	@Test(priority=1, description = "Main script", dataProvider="WQM_config")
	@Parameters("sheetName")
	public void mainScript(String Flag,String browser, String url, String userName, 
			String password, String anotherUserName, String requestedFor,
			String country, String description, String domain,String company,String SDL, 
			String caller, String location, String categorization, String configItem, String contactType, 
			String assignmentGroup, String ownerGroup, String shortDesc, String desc,
			String state, String assignedTo, String closeCode,
			String reportTitle, String data, String type, String groupby, String resolution) throws IOException, Exception
			{
		if(Flag.equalsIgnoreCase("y"))
		{

			try
			{
				System.out.println("####\t"+sheetName);
				if(browser.equalsIgnoreCase("IE"))
				{
					System.setProperty(EnvironmentVariables.driverType, EnvironmentVariables.driverPath);
					driver= new InternetExplorerDriver();
					gm.setLogMsg("Info", "Successfully launched the Internet Explorer 11 browser");
				}
				else if(browser.equalsIgnoreCase("Chrome"))
				{
					System.setProperty(EnvironmentVariables.driverType, EnvironmentVariables.driverPath);
					driver = new ChromeDriver();
					gm.setLogMsg("Info", "Successfully launched the Google Chrome browser");
				}
				/*else if(browser.equalsIgnoreCase("Firefox"))
		{
			driver = new FirefoxDriver();
			gm.setLogMsg("Info", "Successfully launched the Mozilla Firefox browser");
		}*/

				System.out.println("WQM_config_script");	

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				System.out.println(driver.getWindowHandle());

				driver.navigate().to(url);	
				m.wqm_login(driver, url, userName, password, anotherUserName); // login to application
			}


			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
			}

	@Test(priority=2, description = "Client data mappings verification", dataProvider="DataMapping")
	@Parameters("sheetName")
	public void clientDataConfiguration(String Flag, String Client_Name,
			String ContractName,String SDL, String Offering,String SubOfferingList,
			String SubOffering,String IncidentTypeList)
	{
		try
		{
			if(Flag.equalsIgnoreCase("y"))
			{
				
				if(Offering.equalsIgnoreCase("HR Operations"))
				{
					vg.main(driver, Flag, Client_Name, ContractName, SDL, 
							Offering,SubOfferingList, SubOffering, IncidentTypeList);
					
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	@Test(priority=3, description = "Client data creation", dataProvider="GCC_ClientData")
	@Parameters("sheetName")
	public void clientData(String Flag, String Client_Name,String MasterContractNumber, String GEO_Origination,
			String ContractName, String Offering,String GeoRegion, String DeliveryLocation,
			String SDL, String AccountLead,String PrimeMD, String Global_SDL,String ContractStartDate,
			String ContractEndDate,String HRI_List,String SDO, String SubOfferingList, String SubOffering,
			String IncidentTypeList,String IncidentType, String IncidentDescription,String Priority,
			String DateOfIncident,String TargetClosureDate)
	{
		try
		{

			if(Flag.equalsIgnoreCase("y"))
			{
				System.out.println(Client_Name);
				System.out.println(SDL);
				System.out.println(ContractName);
				System.out.println("I have read data successfully!!");
				System.out.println("**********************************************************************");
				ci.main(driver, Client_Name,ContractName,SDL,SDO, SubOffering, IncidentType, IncidentDescription,
						Priority, DateOfIncident, TargetClosureDate);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test(priority=4, description = "Data_mappings", dataProvider="HRL_Mapping")
	@Parameters("sheetName")
	public void mappingForHRL(String Flag, String Offering,String SubOffering, String Incident,
			String IncidentDescription, String Origin,String CorrectiveTasks, String PreventativeTasks,
			String Threshold, String Priority,String ExpectedResolutionTime)
	{
		try
		{

			if(Flag.equalsIgnoreCase("y"))
			{
				System.out.println(Offering);
				System.out.println(SubOffering);
				m2.watchlist(driver, "Demo Agent");
				m2.verifyPreventativeTasks(driver, PreventativeTasks);
				m2.verifyCorrectiveTasks(driver, CorrectiveTasks);
				
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	
	
	@Test(priority=5, description = "Watchlist", dataProvider="FA_Mapping")
	@Parameters("sheetName")
	public void mappingForFA(String Flag, String Offering,String SubOffering, String Incident,
			String IncidentDescription, String Origin,String CorrectiveTasks, String PreventativeTasks,
			String Threshold, String Priority,String ExpectedResolutionTime)
	{
		try
		{

			if(Flag.equalsIgnoreCase("y"))
			{
				System.out.println(Offering);
				System.out.println(SubOffering);
				m2.watchlist(driver, "Demo Agent");
				m2.verifyPreventativeTasks(driver, PreventativeTasks);
				m2.verifyCorrectiveTasks(driver, CorrectiveTasks);
				
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}


	@DataProvider(name = "WQM_config")
	public Object[][] values(ITestContext context)
	{

		String sheetName = "WQM_config_script";
		System.out.println(sheetName);
		Object[][] c1 = gm.getExcelData(EnvironmentVariables.dataPoolPath, sheetName);
		return c1;

	}
	@DataProvider(name = "GCC_ClientData")
	public Object[][] values2(ITestContext context)
	{

		String sheetName = "GCC_ClientData";
		System.out.println(sheetName);
		Object[][] c1 = gm.getExcelData(EnvironmentVariables.dataPoolPath, sheetName);
		return c1;

	}
	@DataProvider(name = "DataMapping")
	public Object[][] values5(ITestContext context)
	{

		String sheetName = "DataMapping";
		System.out.println(sheetName);
		Object[][] c1 = gm.getExcelData(EnvironmentVariables.dataPoolPath, sheetName);
		return c1;

	}
	@DataProvider(name = "HRL_Mapping")
	public Object[][] values3(ITestContext context)
	{

		String sheetName = "HRL";
		System.out.println(sheetName);
		Object[][] c1 = gm.getExcelData(EnvironmentVariables.dataPoolPath, sheetName);
		return c1;

	}
	@DataProvider(name = "FA_Mapping")
	public Object[][] values4(ITestContext context)
	{

		String sheetName = "FA";
		System.out.println(sheetName);
		Object[][] c1 = gm.getExcelData(EnvironmentVariables.dataPoolPath, sheetName);
		return c1;

	}

	/*
	@AfterTest
	public void closeTheAction()
	{
		gm.setLogMsg("info", "Successfully closing the Driver");
		driver.close();
		driver.quit();

	}*/
}