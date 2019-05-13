package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import methods.updateFile;

/**
 * Test case TC0002
 * Action: Navigate to the Contact Us page, and then to the main page using the Home button 
 * 
 */

public class TestCase2 extends updateFile{
	
	static final String url = "http://automationpractice.com/index.php";

	/**
	 * This method performs individual steps needed to automate test case TC0002
	 * It uses the browser to navigate to the Contact Us subpage and then navigate
	 * back to the main page
	 * 
	 * @param driver this is an instance of the WebDriver class
	 * @return nothing
	 */
	
	public static void test(WebDriver driver) {
		
		int row = 16;
		/*
		 * Step 1 Open the main page
		 */
		
		driver.get(url);
		if(driver.getCurrentUrl().equals(url)) {
			updateStatus("PASS", row);
		}else {
			updateStatus("FAIL", row);
		}
		
		row++;
		/*
		 * Step 2 Navigate to the Contact Us sub-page
		 */
		
		driver.findElement(By.id("contact-link")).click();
		
		if(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=contact")) {
			updateStatus("PASS", row);
		}else {
			updateStatus("FAIL", row);
		}
		
		row++;
		/*
		 * Step 3 Click on the Home icon
		 */
		
		driver.findElement(By.xpath("//div[@id='columns']//a[@class='home']")).click();
		
		if(driver.getCurrentUrl().equals(url)) {
			updateStatus("PASS", row);
		}else {
			updateStatus("FAIL", row);
		}
		
		
	}
}
