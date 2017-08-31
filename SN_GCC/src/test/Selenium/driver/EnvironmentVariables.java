package driver;

public class EnvironmentVariables {

	// Update the Work Book Name 
	public final static String workbookName = "Selenium_DataSheet.xlsm";
//	public final static String sheetName = "WQM_config_script";
	
	//CHROME DETAILS
//	public final static String driverPath = "C:\\Users\\a.f.srivastava\\softwares\\chromedriver_win32\\chromedriver.exe";
	public final static String driverPath = "C:\\Users\\somnath.bhunia\\Downloads\\Setup required\\chromedriver_win32\\chromedriver.exe";
	public final static String driverType = "webdriver.chrome.driver";
	
	//IE DETAILS
						//64bit
	//public final static String driverPath = "C:\\Users\\a.f.srivastava\\softwares\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe";

						//32bit
//	public final static String driverPath = "C:\\Users\\a.f.srivastava\\softwares\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe";
//	public final static String driverType = "webdriver.ie.driver";

	
	public final static String dataPoolPath = System.getProperty("user.dir")+"\\src\\test\\Selenium\\resources\\"+workbookName;
	

	
}
