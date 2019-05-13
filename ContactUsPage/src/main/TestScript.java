package main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import methods.updateFile;
import testcases.*;

/**
 * Script is designed to automate the testing of the Contact Us subpage
 * Url address to the main page: http://automationpractice.com/index.php
 * 
 * Test scenario: Verify that the Contact Us subpage works as expected
 * The test consists of 3 test cases
 * 
 * It writes the results into an Excel file
 * 
 * @author aleks
 * @since 2019-05-01
 *
 */

public class TestScript extends updateFile {
	
	static final String fileName = "TestData.xlsx";
	static final String sheetName = "Test_cases";
	
	public static WebDriver driver;
	
	/**
	 * This method opens the Firefox browser, runs the individual test cases and closes the browser
	 * It also handles the method that clears out the Status column in the Excel spreadsheet before
	 * the test is performed
	 * 
	 * @param no args
	 * @return nothing
	 */

	public static void main(String[] args) {
		
		clearAllStatusFields();
		
		System.setProperty("webdriver.gecko.driver","libs/geckodriver.exe");
		driver = new FirefoxDriver();
		
		TestCase1.test(driver);
		TestCase2.test(driver);
		TestCase3.test(driver);
		
		driver.close();
		
		System.out.println("Automated test finished running.");
		
	}

}
