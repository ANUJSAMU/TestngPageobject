package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
		 
		 public static String[][] getExcelData(String fileName, String sheetName) throws IOException {
		        String[][] data = null;
		        try {
		             
		            FileInputStream fis = new FileInputStream(fileName);
		            XSSFWorkbook workbook = new XSSFWorkbook(fis);
		            XSSFSheet sheet = workbook.getSheet(sheetName);
		            workbook.close();
		            XSSFRow row = sheet.getRow(0);
		            int noOfRows = sheet.getPhysicalNumberOfRows();
		            int noOfCols = row.getLastCellNum();
		            Cell cell;
		            data = new String[noOfRows - 1][noOfCols];
		 
		            for (int i = 1; i < noOfRows; i++) {
		                for (int j = 0; j < noOfCols; j++) {
		                    row = sheet.getRow(i);
		                    cell = row.getCell(j);
		                    data[i - 1][j] = cell.getStringCellValue();
		                }
		            }
		        } catch (Exception e) {
		            System.out.println("The exception is: " + e.getMessage());
		        }
		        return data;
		    }
		 
		 
		 public static Object[][] getExcelDataHash(String fileName,String sheetName) throws IOException {
			FileInputStream fis = new FileInputStream(fileName);
	        XSSFWorkbook workbook = new XSSFWorkbook(fis);
	        XSSFSheet sheet = workbook.getSheet(sheetName);
	        workbook.close();
	        
	        int rowCount = sheet.getLastRowNum();
	        int colCount = sheet.getRow(0).getLastCellNum();
	        
	        Object[][] obj =  new Object[rowCount][1];
	        for(int i = 0; i<rowCount; i++) {
	        	Map<Object, Object> datamap = new HashMap<Object, Object>();
	        	for(int j = 0; j<colCount; j++) {
	        		datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
	        	}
	        	obj[i][0] = datamap;
	        }
			 
	        return obj;
		 }
		    

}
