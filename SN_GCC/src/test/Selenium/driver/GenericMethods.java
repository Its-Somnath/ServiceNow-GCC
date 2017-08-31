
package driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class GenericMethods {
	static Sheet sheet = null; 

	public void logScreenshot(String condition,String msg, WebDriver driver)
	{	
		String userDirector = System.getProperty("user.dir") + "/"; 

		String s1 = null,s2 ="";
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		if(true)
		{
			try {

				String failureImageFileName =  new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime())+ ".png"; 
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("Screenshot\\"+failureImageFileName)); 
				s2 = "<a href=\""+ userDirector +"\\Screenshot\\" + failureImageFileName +"\"><img src=\"file:///" + userDirector +"\\Screenshot\\" + failureImageFileName + "\" alt=\"\""+ "height='300' width='300' border =1/> "+"<br />";


			} catch (IOException e1) {
				e1.printStackTrace();
			}


			if (condition.equalsIgnoreCase("info"))	
			{
				s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>Info</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"

					+ "<tr>"
					+ "<td colspan=2>" 									
					+ "<table width= 100% ; rules =rows >"

												+ "<tbody>"

													+ "<tr>"
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+"Screenshot"+"</strong></td>" 
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+s2+"</strong></td>"

													+ "</tr>"
													+"</tbody>"
													+"</table>"

								+ "</td>"	
								+"</tr>"

				+ "</tbody>"
				+"</table>";

			}

			if (condition.equalsIgnoreCase("Pass"))	
			{
				s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:green;font-size:12px;font-family:verdana;\\><strong>Pass</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"

					+ "<tr>"
					+ "<td colspan=2>" 
					+ "<table width= 100% ;rules =rows >"

												+ "<tbody>"

													+ "<tr>"
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+"Screenshot"+"</strong></td>" 
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+s2+"</strong></td>"

													+ "</tr>"
													+"</tbody>"
													+"</table>"

								+ "</td>"	
								+"</tr>"

				+ "</tbody>"
				+"</table>";

			}
			if (condition.equalsIgnoreCase("Fail"))	
			{
				s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:red;font-size:12px;font-family:verdana;\\><strong>Fail</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"

					+ "<tr>"
					+ "<td colspan=2>" 
					+ "<table width= 100% ; rules =rows >"

												+ "<tbody>"

													+ "<tr>"
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+"Screenshot"+"</strong></td>" 
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+s2+"</strong></td>"

													+ "</tr>"
													+"</tbody>"
													+"</table>"
													+ "</td>"	
													+"</tr>"

				+ "</tbody>"
				+"</table>";

			}
			Reporter.log(s1);
		}
	}
	
	
	public void setLogMsg(String condition, String msg)
	{
		String s1 ="";

		if (condition.equalsIgnoreCase("info"))	
		{
			s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>Info</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"									
					+ "</tbody>"
					+"</table>";

		}

		if (condition.equalsIgnoreCase("Pass"))	
		{
			s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:Green;font-size:12px;font-family:verdana;\\><strong>Pass</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"									
					+ "</tbody>"
					+"</table>";

		}
		if (condition.equalsIgnoreCase("fail"))	
		{
			s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:red;font-size:12px;font-family:verdana;\\><strong>Fail</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"									
					+ "</tbody>"
					+"</table>";
		}
		Reporter.log(s1);
	}


	public WebElement waitForObject(WebDriver driver,By object)
	{
		WebDriverWait wait = new WebDriverWait(driver,60);
		WebElement ele= wait.until(ExpectedConditions.presenceOfElementLocated(object));
		return ele;
	}


	public String[][] getExcelData(String fileName, String sheetName) 
	{
		String[][] arrayExcelData = null;
		System.out.println(sheetName + "\t"+fileName);
		org.apache.poi.ss.usermodel.Workbook tempWB;

		try {

			if(fileName.contains(".xlsm")||fileName.contains(".xlsx")){
				tempWB = new XSSFWorkbook(fileName);
			}
			else{				
				InputStream inp = new FileInputStream(fileName);
				tempWB = (org.apache.poi.ss.usermodel.Workbook) new HSSFWorkbook(new POIFSFileSystem(inp));					
			}

			//org.apache.poi.ss.usermodel.Sheet 
			sheet = tempWB.getSheet(sheetName);
			
			// Total rows counts the top heading row
			int totalNoOfRows = sheet.getLastRowNum();
			Row row = sheet.getRow(0);
			int totalNoOfCols = row.getLastCellNum();

			arrayExcelData = new String[totalNoOfRows][totalNoOfCols];

			try {
				System.out.println("Proceeding to read all rows of data in the current worksheet...");
				for (int i= 1 ; i < totalNoOfRows+1; i++) {

					for (int j=1; j < totalNoOfCols+1; j++) 
					{
						row = sheet.getRow(i);
				//		System.out.println(row.getCell(j-1).toString().trim());
						try{
						arrayExcelData[i-1][j-1] = row.getCell(j-1).toString().trim();
						}catch(Exception ee){arrayExcelData[i-1][j-1] = "";}
						//System.out.println("Coming here");
					}
					System.out.println("Row # "+i);
				}
				System.out.println("Completed reading all data from the current worksheet");
				System.out.println("Rows :"+totalNoOfRows+"\nColumns : "+totalNoOfCols);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		return arrayExcelData;
	}
	
	public int findCol(Sheet sheet,String ColName)
	{
		Row row = null;
		int colCount = 0;

		row = sheet.getRow(0);
		if (!(row==null))
		{
			colCount = row.getLastCellNum();
		}
		else
			colCount = 0;

		for (int j=0;j<colCount;j++)
		{
			if(!( row.getCell(j)==null)){
				if (row.getCell(j).toString().trim().equalsIgnoreCase(ColName)|| row.getCell(j).toString().trim().equalsIgnoreCase((ColName+"[][String]"))){
					return j;
				}
			}
		}
		return -1;
	}

	public String getValueFromDatasheet(String SheetName,String colName,int rowNo)
	{
		try
		{
			Workbook tempWB;
			String value ="";
			if (EnvironmentVariables.dataPoolPath.contains(".xlsx"))
				tempWB = new XSSFWorkbook(EnvironmentVariables.dataPoolPath);

			else
			{
				FileInputStream inp = new FileInputStream(EnvironmentVariables.dataPoolPath);
				tempWB = (Workbook) new HSSFWorkbook(new POIFSFileSystem(inp));	
			}

			Sheet sheet = tempWB.getSheet(SheetName);
			Row row = sheet.getRow(rowNo);

			if(row == null){
				return null;
			}
			try{
				value = row.getCell(findCol(sheet, colName)).toString().trim();
				return value;
			}
			finally {}
		}
			catch(FileNotFoundException e)
			{
				setLogMsg("Fail", "File not found in the path : "+ EnvironmentVariables.dataPoolPath);
			}
			catch(IOException e)
			{
				setLogMsg("Fail", "Problem in reading the File");
			}
			return null;
		}
	
	/*public void saveVariable(String colName, String value)
	{
		try
		{
			if(storeValue(EnvironmentVariables.dataPoolPath, 
					EnvironmentVariables.sheetName, findCol(sheet, colName), 1, value))
				System.out.println("efdds");
		}
		catch(Exception e)
		{
			System.out.println("ededs");
		}
	}*/
	
	
	public boolean storeValue(String dpPath, String sheetName, int colNumber, int rowNumber, String Value) throws Exception, IOException {
		boolean pass = true;
		try
		{
			if(dpPath.contains(".xlsx"))
			{
				setLogMsg("Info", "Excel files in \".xlsx\" format can not be written, Please convert it to \".xls\" format");
				pass =false;
			}
			else
			{
				FileInputStream inp = new FileInputStream(dpPath);
				HSSFWorkbook wb = new HSSFWorkbook(inp); 
				HSSFSheet sheet = wb.getSheet(sheetName);
				Cell cell = null;
			    Row row = sheet.getRow(rowNumber);
			    if(row==null)
			    	row = sheet.createRow(rowNumber);
			    
			    row.getCell(colNumber);
			    if (cell == null) { 
			        cell = row.createCell(colNumber);  
			    	
			    }
			    cell.setCellValue(Value);  
			    inp.close();
			    
			    // Write the output to a file
			    String s =  dpPath.substring(0, dpPath.lastIndexOf("\\"));
			    FileOutputStream fileOut = new FileOutputStream(new File(s + "/test.xls"));  
			    wb.write(fileOut);  
			    fileOut.close();
			    File fp = new File(dpPath);
			    File fout = new File(s + "/test.xls");
			    fp.delete();
			    fout.renameTo(fp);
			    
			}
		} catch (FileNotFoundException e) {
            e.printStackTrace();
            pass = false;
        } catch (IOException e) {
            e.printStackTrace();
            pass = false;
        }

	 return pass;	
	}
	
	//Displays the message in dialog box
		public void displayMessage(String message)
		{
			JOptionPane.showMessageDialog(null, message);	
		}
}