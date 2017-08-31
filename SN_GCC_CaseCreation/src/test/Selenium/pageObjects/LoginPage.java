package pageObjects;



import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;



public class LoginPage {

	//ServiceNow
	public static By username = By.xpath("//input[@id='user_name']");
	public static By password = By.xpath("//*[@id='user_password']");
	public static By btnSignIn = By.xpath("//*[@id='sysverb_login']");
	public static By userImpersonate =By.xpath("//*[@id='user_info_dropdown']");
	public static By link_impersonate =By.xpath("//a[text()='Impersonate User']");
	public static By text_anotherUserName=By.xpath("//span[text()='Search for user']");
	public static By text_anotherUserName1=By.xpath("//input[@id='s2id_autogen4_search']");
	//INPUT[@id='user_name']
	//INPUT[@id='user_password']
	
	
	//Cadence
	public static By cad_userName =By.xpath("//*[@id='loginIdTextBox']");
	public static By cad_password =By.xpath("//*[@id='passwordTextBox']");
	public static By cad_signin =By.xpath("//*[@id='loginButton']");
	public static By cad_signout =By.xpath("//*[@id=ctl00_mainHeader_logoutLink']");
	
}
