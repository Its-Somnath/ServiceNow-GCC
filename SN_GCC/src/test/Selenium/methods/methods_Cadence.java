package methods;


	import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import driver.*;



public class methods_Cadence {

	GenericMethods gm = new GenericMethods();
	public static String s="//a[@id='17e0b4990a04bf15013f9590408c419a']";
	public void cadence_login(WebDriver driver,String appUrl,String userName,String password) throws InterruptedException
	{
		gm.waitForObject(driver,LoginPage.cad_userName);
		System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111----------------------");
		/*WebElement elm	 = driver.findElement(LoginPage.cad_signin);
		if (elm.isDisplayed())
		{
			driver.findElement(LoginPage.cad_signin).click();
			gm.waitForObject(driver, LoginPage.cad_signin);
			gm.logScreenshot("Info", "Login page is displayed", driver);	
		}

		else
			Reporter.log("Info, Login page is not displayed");*/
		
		driver.findElement(LoginPage.cad_userName).sendKeys(userName);
		driver.findElement(LoginPage.cad_password).sendKeys(password);
		driver.findElement(LoginPage.cad_signin).click();
		Thread.sleep(10000);
		
	/*	gm.waitForObject(driver,HomePage.cad_signout);
		Thread.sleep(6000);*/
		gm.logScreenshot("Pass", "Successfully navigated to Cadence Home page", driver);
	}
	
	public void navigation(WebDriver driver)throws InterruptedException
	{
		driver.findElement(By.xpath("//A[@id='Reconciliations']")).click();
		//*[@id="menu_tab"]/ul/li[1]/a[2]
		
		driver.findElement(HomePage.cad_mainMenu_Recon).click();
		Thread.sleep(5000);
		gm.waitForObject(driver, HomePage.cad_mainMenu_1_Recon);
		driver.findElement(HomePage.cad_mainMenu_1_Recon).click();
		Thread.sleep(2000);
		driver.findElement(HomePage.cad_subMenu_Recon_ReconDue).click();
	}
	
	
	
	
	
}
