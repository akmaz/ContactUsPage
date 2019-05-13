package methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class consists of two methods dealing with the Excel spreadsheet
 * It allows to handle the document and update the data
 *
 */

public class updateFile {
	
	static final String fileName = "TestData.xlsx";
	static final String sheetName = "Test_cases";
	
	/**
	 * This method updates the status of individual steps performed in the Excel spreadsheet document
	 * 
	 * @param status this parameter takes a String which is a status of the test - either 'PASS' or 'FAIL', or other
	 * @param row this parameter is an int defining the number of the row in the Excel spreadsheet currently being tested
	 * @return nothing
	 */
	
	public static void updateStatus(String status, int row) {
		
		File file = new File(fileName);
		FileInputStream inputStream=null;
		
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	    Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    Sheet sheet = workbook.getSheet(sheetName);
		Cell cell = sheet.getRow(row).getCell(9);
		cell.setCellValue(status);
		
		try {
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			outputStream.close();
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		
	}
	
	/**
	 * This method clears the Status column in the Excel spreadsheet, before the new test is performed
	 * 
	 * @param nothing
	 * @return nothing
	 */
	
	public static void clearAllStatusFields() {
		File file = new File(fileName);
		FileInputStream inputStream=null;
		
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	    Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    Sheet sheet = workbook.getSheet(sheetName);
	    for(int i = 2; i <= 24; i++) {
	    	Cell cell = sheet.getRow(i).getCell(9);
	    	cell.setBlank();
	    }
	    
		try {
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			outputStream.close();
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		
	}
}
