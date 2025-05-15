package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcel(String sheetName, int row, int celNum) throws IOException {
		
		FileInputStream fis= new FileInputStream("./testData/TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetName).getRow(row).getCell(celNum).getStringCellValue();
		wb.close();
		
		return data;
		
	}
	
	public int getRowCount(String sheetName) throws IOException {
		FileInputStream fis= new FileInputStream("./testData/TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowcount=wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowcount;
	}
	
	public void setDataIntoExcel(String sheetName, int row, int celNum, String data) throws IOException {
		
		FileInputStream fis= new FileInputStream("./testData/TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(row).createCell(celNum);
		
		FileOutputStream fos= new FileOutputStream("./testData/TestScriptData.xlsx");
		wb.write(fos);
		wb.close();
	}

}
