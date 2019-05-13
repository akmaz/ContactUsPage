package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import methods.updateFile;

/**
 * Test case TC0003
 * Action: Navigate to the Contact Us page, fill the fields incorrectly and try to send a message 
 * 
 */

public class TestCase3 extends updateFile {

	static final String url = "http://automationpractice.com/index.php";
	
	/**
	 * This method performs individual steps needed to automate test case TC0003
	 * It uses the browser to navigate to the Contact Us subpage and perform 'negative'
	 * scenarios there
	 * 
	 * @param driver this is an instance of the WebDriver class
	 * @return nothing
	 */

	public static void test(WebDriver driver) {
		int row = 19;
		
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
		 * Step 2 Navigate to the Contact Us subpage
		 */
		
		driver.findElement(By.id("contact-link")).click();
		
		if(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=contact")) {
			updateStatus("PASS", row);
		}else {
			updateStatus("FAIL", row);
		}
		
		row++;
		/*
		 * Step 3 Without filling in any fields, click on the Send button
		 */
		
		driver.findElement(By.id("submitMessage")).click();
		
		WebElement alertField;
		alertField = driver.findElement(By.xpath("//div[@id='center_column']/div[contains(@class,'alert')]"));
		
		if(alertField.isDisplayed() 
			&& alertField.findElement(By.xpath("p")).getText().equals("There is 1 error")
			&& alertField.findElement(By.xpath("ol/li")).getText().equals("Invalid email address.")) {
			updateStatus("PASS", row);
		}else {
			updateStatus("FAIL", row);
		}
		
		row++;
		/*
		 * Step 4 Fill in the email address field correctly and click on the Send button
		 */
		
		driver.findElement(By.id("email")).sendKeys("tkasia@wp.pl");
		
		driver.findElement(By.id("submitMessage")).click();
		
		alertField = driver.findElement(By.xpath("//div[@id='center_column']/div[contains(@class,'alert')]"));
		
		if(alertField.isDisplayed() 
			&& alertField.findElement(By.xpath("p")).getText().equals("There is 1 error")
			&& alertField.findElement(By.xpath("ol/li")).getText().equals("The message cannot be blank.")) {
			updateStatus("PASS", row);
		}else {
			updateStatus("FAIL", row);
		}
		
		row++;
		/*
		 * Step 5 Fill in the Message field and click on the Send button
		 */
		
		driver.findElement(By.id("message")).sendKeys("test message");

		driver.findElement(By.id("submitMessage")).click();
		
		alertField = driver.findElement(By.xpath("//div[@id='center_column']/div[contains(@class,'alert-danger')]"));
		
		if(alertField.isDisplayed() 
			&& alertField.findElement(By.xpath("p")).getText().equals("There is 1 error")
			&& alertField.findElement(By.xpath(".//li")).getText().equals("Please select a subject from the list provided.")) {
			updateStatus("PASS", row);
		}else {
			updateStatus("FAIL", row);
		}
		
		row++;
		/*
		 * Step 6 Select a subject heading and click on the Send button
		 */
		
		Select selectSubjectHeading = new Select(driver.findElement(By.id("id_contact")));
		selectSubjectHeading.selectByIndex(2);
		
		driver.findElement(By.id("submitMessage")).click();
		
		if(driver.findElement(By.xpath("//div[@id='center_column']/p")).isDisplayed()) {
			updateStatus("PASS",row);
		}else {
			updateStatus("FAIL",row);
		}
		
	}
}
