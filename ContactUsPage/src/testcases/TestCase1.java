package testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import methods.updateFile;

/**
 * Test case 'TC0001'
 * Action performed: Navigate to the Contact Us page, fill the fields correctly and send a message
 *
 */


public class TestCase1 extends updateFile {
	
	static final String url = "http://automationpractice.com/index.php";
	
	/**
	 * This method performs individual steps needed to automate test case TC0001
	 * It uses the browser to navigate to the Contact Us subpage and perform
	 * certain steps there
	 * 
	 * @param driver this is an instance of the WebDriver class
	 * @return nothing
	 */

	public static void test(WebDriver driver) {
		int row=2;
		
		/*
		 * Step 1 Open the main page
		 */
		
		driver.get(url);
		if(driver.getCurrentUrl().equals(url)) {
			updateStatus("PASS",row);
		}else {
			updateStatus("FAIL",row);
		}
		
		row++;
		/*
		 * Step 2 Navigate to the Contact Us sub-page
		 */
		
		driver.findElement(By.id("contact-link")).click();
		
		if(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=contact")) {
			updateStatus("PASS",row);
		}else {
			updateStatus("FAIL",row);
		}
		
		row++;
		/*
		 * Step 3 Verify that the title of the subpage is correct
		 */
		
		if(driver.getTitle().equals("Contact us - My Store")) {
			updateStatus("PASS",row);
		}else {
			updateStatus("FAIL",row);
		}
		
		row++;
		/*
		 * Step 4 Check if available options in the Subject Heading drop down are correct
		 */
		
		WebElement subjectHeading = driver.findElement(By.id("id_contact"));
		Select selectSubjectHeading = new Select(subjectHeading);
		
		List<WebElement> list = selectSubjectHeading.getOptions(); 
		int size = list.size();
		String[] options = {"Customer service", "Webmaster"};
		boolean appears=false;
		
		for(int i=0; i<options.length; i++) {
			appears = false;
			for(int j=1; j<size && !appears; j++) {
				if(options[i].equals(list.get(j).getText())) {
					appears = true;
				}
			}
			if(!appears)
				break;
		}
		
		if(appears) {
			updateStatus("PASS",row);
		}else {
			updateStatus("FAIL",row);
		}
		
		row++;
		/*
		 * Step 5 Choose a subject heading
		 */
		
		selectSubjectHeading.selectByVisibleText("Customer service");
		WebElement confirmationMessage = driver.findElement(By.id("desc_contact2"));
		if(confirmationMessage.isDisplayed()) {
			updateStatus("PASS",row);
		}else {
			updateStatus("FAIL",row);
		}
		
		row++;
		/*
		 * Step 6 Type in an incorrect email in the Email Address field
		 */
		
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("invalid.text!#45");
		email.sendKeys(Keys.TAB);
		
		WebElement emailField = driver.findElement(By.xpath("//div[@id='center_column']//div[@class='col-xs-12 col-md-3']/p[4]"));
		String classOfEmailField = emailField.getAttribute("class");
		
		int idx = classOfEmailField.indexOf(' ');
		if((classOfEmailField.substring(idx+1)).equals("form-error")) {
			updateStatus("PASS",row);
		}else {
			updateStatus("FAIL",row);
		}
		
		row++;
		/*
		 * Step 7 Clear the Email Address field
		 */
		
		email.clear();
		if(email.getText().length()==0) {
			updateStatus("PASS",row);
		}else {
			updateStatus("FAIL",row);
		}
		
		row++;
		/*
		 * Step 8 Type in a correct email in the Email Address field
		 */
		
		email.sendKeys("test@mail.com");
		email.sendKeys(Keys.TAB);
		
		classOfEmailField = emailField.getAttribute("class");
		
		if((classOfEmailField.substring(idx+1)).equals("form-ok")) {
			updateStatus("PASS",row);
		}else {
			updateStatus("FAIL",row);
		}
		
		row++;
		/*
		 * Step 9 Type in a reference in the Order Reference field
		 */
		
		WebElement orderReference = driver.findElement(By.id("id_order"));
		try {
			orderReference.sendKeys("REF01478");
			updateStatus("PASS",row);
		}catch(IllegalArgumentException iae) {
			updateStatus("FAIL",row);
		}
		
		row++;
		/*
		 * Step 10 Type in a message in the Message field
		 */
		
		WebElement message = driver.findElement(By.id("message"));
		try {
			message.sendKeys("Hi, I would like to ask...");
			updateStatus("PASS",row);
		}catch(IllegalArgumentException iae) {
			updateStatus("FAIL",row);
		}
		
		row++;
		/*
		 * Step 11 Clear the Message field
		 */
		
		message.clear();
		if(message.getText().length() == 0) {
			updateStatus("PASS",row);
		} else {
			updateStatus("FAIL",row);
		}
		
		row++;
		/*
		 * Step 12 Type in a different message again in the Message field
		 */
		
		try {
			message.sendKeys("Dear Sir/Madam,\n\n"
					+ "I am writing in regards to the product I ordered from your website over 3 weeks ago."
					+ "I still haven't received my package, or any confirmation that it has been send.\n"
					+ "Could you advise what I should do next please.\n\n"
					+ "Kind regards, Alex" + 
					"");
			
			updateStatus("PASS",row);
		}catch(IllegalArgumentException iae) {
			updateStatus("FAIL",row);
		}
		
		row++;
		/*
		 * Step 13 Click on the Send button
		 */
		
		driver.findElement(By.id("submitMessage")).click();
		
		if(driver.findElement(By.xpath("//div[@id='center_column']/p")).isDisplayed()) {
			updateStatus("PASS",row);
		}else {
			updateStatus("FAIL",row);
		}
		
		row++;
		/*
		 * Step 14 Return to the Home page
		 */
		
		driver.findElement(By.xpath("//div[@id='center_column']/ul/li/a")).click();
		
		if(driver.getCurrentUrl().equals(url)) {
			updateStatus("PASS",row);
		}else {
			updateStatus("FAIL",row);
		}
		
		
	}
	
}
