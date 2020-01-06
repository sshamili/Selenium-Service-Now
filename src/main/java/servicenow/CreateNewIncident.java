package servicenow;

import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateNewIncident {
	public ChromeDriver driver;
	public void LaunchURL(String url) {
		// Launch browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		// Open URL
		driver.get(url);
	}
	
	public void enterUsername(String uName) {
		// Enter UserName
		driver.findElementById("user_name").sendKeys(uName);
	}
	
	public void enterPassword(String password) {
		//Enter Password
		driver.findElementById("user_password").sendKeys(password);

	}
	public void clickLogin() {
		// Click Login Button
		driver.findElementById("sysverb_login").click();
	}
	
	
	public void createIncident() throws InterruptedException {
		
		Thread.sleep(50000);
		// Click "Incident" -> "Create New Incident" from left side Menu
		driver.findElementByXPath("//span[text()='Incident']").click();
		driver.findElementByXPath("(//div[text()='Create New'])[1]").click();
		Thread.sleep(30000);
		
		// Switch to Frame
		driver.switchTo().frame(0);
		
		// Fill all Mandatory Fields in "Create incident page"
		String incidentNumber = driver.findElementById("incident.number").getAttribute("value");
		driver.findElementById("sys_display.incident.caller_id").sendKeys("Abrah",Keys.TAB);
		Thread.sleep(4000);
		driver.findElementById("incident.short_description").sendKeys("New Incident Creation");
		driver.findElementByXPath("(//button[text()='Submit'])[2]").click();
		Thread.sleep(30000);
		
		// Switch to Frame
		driver.switchTo().frame(0);
		
		// Verify the incident is created or not using "Incident ID"
		driver.findElementById("incident_table_header_search_control").sendKeys(incidentNumber,Keys.ENTER);
		String id = driver.findElementByXPath("//tbody[@class='list2_body']//tr/td[3]").getText();
		
		if(incidentNumber.equals(id)) {
			System.out.println("Incident created successfully");
		} else
		{
			System.out.println("Incident not created successfully, Create a New Incident");
		}
	}
	

	public static void main(String[] args) throws InterruptedException {
		CreateNewIncident c = new CreateNewIncident();
		c.LaunchURL("https://dev69210.service-now.com/");
		c.enterUsername("admin");
		c.enterPassword("India@1234");
		c.clickLogin();
		c.createIncident();
		
	}

}
