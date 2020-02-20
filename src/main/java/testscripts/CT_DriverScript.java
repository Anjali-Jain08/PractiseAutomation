package testscripts;


import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CT_datatable.CT_dataXlsReader;
import jxl.JXLException;
import jxl.read.biff.BiffException;


public class CT_DriverScript {

	public static EventFiringWebDriver driver = null;
	public static CT_dataXlsReader controller = null;
	public static CT_dataXlsReader testdata = null;
	public static WebDriver wbdv = null;
	public static Properties Config = null;
	public static String keyword = null;
	public static String firstSheetName = null;
	//public static String methodresult = null;
	//public static String failtest= "fail";

	@BeforeTest	
	public static void Initialise() throws IOException, BiffException {

		//Instantiate proerties class and load properties file
		Config = new Properties();
		FileInputStream inStream = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\CT_config\\config.properties");
		Config.load(inStream);

		//Instantiate CT_dataXlsReader.java class to set path for Keyword and data sheet
		controller = new CT_dataXlsReader(System.getProperty("user.dir") + "\\src\\main\\java\\CT_config\\CT_controller1.xls");
		System.out.println("Path set for Keyword excel file");

		testdata = new CT_dataXlsReader(System.getProperty("user.dir")+ "\\src\\main\\java\\CT_config\\CT_testdata1.xls");
		System.out.println("Path set for Testdata excel file");


	}

	@Test
	public static void Testapp() throws JXLException, IOException {
		System.out.println("Testapp starts");

		//To get first sheet name from the keyword excel sheet i.e., CT_controller1.xls
		firstSheetName = controller.getFirstSheetname();
		System.out.println("first sheet name is : " +  firstSheetName);

		//Loop to get total row count of the first sheet, to get the cell data of each row
		for(int tcid=1; tcid<controller.getRowCount(firstSheetName); tcid++) {

			//Store cell data of each row accroding to the colummn name
			String currentTest = controller.getCellData(firstSheetName, "TCID", tcid);

			//Check if celldata of column name Runmode has Y in t's rows or not
			if(controller.getCellData(firstSheetName, "Runmode", tcid).equalsIgnoreCase("Y")) {

				//Loop to get the row count of the sheetname stored in currentTest
				for(int tsid=1; tsid<controller.getRowCount(currentTest); tsid++) {

					//Store TestScenario's Keyword
					keyword = controller.getCellData(currentTest, "Keyword", tsid);
					System.out.println("Keyword is : " + keyword);
					//String currentTSID = controller.getCelldata(currentTest, "TSID", tsid);
					//String StepDescription = controller.getCelldata(currentTest, "Description", tsid);
					try {

						//Invoke CT_Keyword.java class by Method class	
						Method method = CT_Keywords.class.getMethod(keyword);
						method.invoke(method);	

					}catch (Exception e) {

						e.printStackTrace();
					}
				}
			}
		}
	}

	@AfterTest
	public static void endtest() throws InterruptedException {

		//To close the window, after the testcases scripts run

		CT_FunctionLibrary.windowClose();
		System.out.println("Run Successfully");

	}

}
