package CT_datatable;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jxl.Cell;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import testscripts.CT_DriverScript;

//This class contains all the XLS related actions
public class CT_dataXlsReader extends CT_DriverScript {
	
	public String path = null;
	
	//Created constructor
	public CT_dataXlsReader(String path) throws IOException {
		
		this.path=path;
		FileInputStream fin = new FileInputStream(path);
		fin.close();
		
	}

//This method will return the total count of the row for a sheet.	
	public int getRowCount(String SheetName) throws IOException, BiffException, JXLException {
		
		File file = new File(path);
		//FileInputStream fin = new FileInputStream(file);
		Workbook w;
		w = Workbook.getWorkbook(file);
		Sheet sheet = w.getSheet(SheetName);
		int row_count = 0;
		int i = 0;
		for(i=0; i<sheet.getRows(); i++) {
			
			row_count++;
		}
		
		return row_count;
		
	}

//This method will return the name of the sheet according to the index number provided to it.	
	public String getFirstSheetname() throws IOException, BiffException, JXLException{
		File file = new File(path);
		//FileInputStream fin = new FileInputStream(file);
		Workbook w;
		w = Workbook.getWorkbook(file);
		String sheet = w.getSheet(0).getName();
		return sheet;
		
	}
		
//This method will retrun the data available ine the cell	
	public String getCellData(String SheetName, String colName, int rowNum) throws IOException, BiffException, JXLException{
		
		File file = new File(path);
		Workbook w;
		w= Workbook.getWorkbook(file);
		Sheet sheet = w.getSheet(SheetName);
		int colNum = 0;
		int i;
		for(i=0; i<sheet.getColumns(); i++) {
			
			if(sheet.getCell(i, 0).getContents().equalsIgnoreCase(colName)) {
				break;
			}
		}
		
		colNum=i;
		Cell cell = sheet.getCell(colNum, rowNum);
		return cell.getContents();
		
	}

	}