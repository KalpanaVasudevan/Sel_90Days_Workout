package week1.selenium.workout;

import java.io.File;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Azure {

	
	public static void verifyDownloadFile(String fileName){

	    File file = new File("C:\\Users\\kirub\\Downloads\\"+fileName);
	    
	    if (file.exists()) {
			System.out.println("File is downloaded successfully");
	    	file.delete();
	    }
	    else
	    	System.out.println("File is not downloaded ");
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		//Setting up driver and browser property
			
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		//create an object for Chrome driver
			
			ChromeDriver driver = new ChromeDriver();
		
			WebDriverWait wait = new WebDriverWait(driver,30);//Explicit wait
		
		//Launch URL
			
			driver.get("https://azure.microsoft.com/en-in/");
		
			driver.manage().window().maximize(); //maximize the window
			
		//Click on Pricing 
			
			driver.findElementByXPath("//a[text()='Pricing']").click();
		
		//Click on Pricing Calculator 
			
			driver.findElementByXPath("//a[contains(text(),'Pricing calculator')]").click();
			Thread.sleep(2000);
		
		//Click on Containers
			
			driver.findElementByXPath("//button[text()='Containers']").click();
		
		//Select Container Instances
			
			driver.findElementByXPath("(//span[text()='Container Instances'])[3]").click();
			
			Thread.sleep(2000);
		
			//Click on Container Instance Added View 
			driver.findElementByXPath("//button[text()='View']").click();
		
		//Select Region as "South India"
			
			WebElement region = driver.findElementByXPath("(//select[@class='select' and @name='region'])[1]");
		
			Select option = new Select(region);	
		
			option.selectByVisibleText("South India");
			Thread.sleep(2000);
		
		//Set the Duration as 180000 seconds 
			
			WebElement duration = driver.findElementByXPath("(//input[@name='seconds'])[1]");
		
			duration.clear();//clear the default value
			
			duration.sendKeys(Keys.RIGHT);
			
			duration.sendKeys("80000");
		
		//Select the Memory as 4GB
			
			WebElement memory = driver.findElementByXPath("(//select[@name='memory'])[1]");
			
			Select memoryOption = new Select(memory);	
			
			memoryOption.selectByVisibleText("4 GB");
			Thread.sleep(2000);
			
		//Enable SHOW DEV/TEST PRICING
			
			driver.findElementByXPath("//button[@name='devTestSelected']").click();
			
		//Select Indian Rupee  as currency
			
			WebElement currency = driver.findElementByXPath("//select[@class='select currency-dropdown']");
			
			Select currencyOption = new Select(currency);	
			
			currencyOption.selectByValue("INR");
			Thread.sleep(2000);
		
		//Print the Estimated monthly price
			
			String estimatedMonthlyCost = driver.findElementByXPath("(//div[@class='column large-3 text-right total']//span)[3]").getText();
			
			System.out.println("Estimated Monthly Cost: "+estimatedMonthlyCost);
			
		//Click on Export to download the estimate as excel 
			
			driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']").click();
			Thread.sleep(3000);
		
		//Verify the downloded file in the local folder
			verifyDownloadFile("ExportedEstimate.xlsx");
			
		//Navigate to Example Scenarios and Select CI/CD for Containers 
			
			Actions builder = new Actions(driver);
			
			builder.moveToElement(driver.findElementByXPath("//a[text()='Example Scenarios']")).perform();
			
			driver.findElementByXPath("//a[text()='Example Scenarios']").click();
			
			driver.findElementByXPath("//span[text()='CI/CD for Containers']").click();
			
		//Click Add to Estimate
		
		builder.moveToElement(driver.findElementByXPath("//a[text()='Your Estimate']")).perform();
		
			
		driver.findElementByXPath("//button[text()='Add to estimate']").click();
		
		Thread.sleep(4000);	
		
		//Change the Currency as Indian Rupee 
		
		WebElement dropdown = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		
		Select CurrencyOption = new Select(dropdown);
		
		CurrencyOption.selectByValue("INR");
		Thread.sleep(2000);
		
		
	   //Enable SHOW DEV/TEST PRICING 
		driver.findElementByXPath("//button[@name='devTestSelected']").click();
		Thread.sleep(3000);
		
		//Export the Estimate 
		driver.findElementByXPath("//button[text()='Export']").click();
		Thread.sleep(3000);
		
		//Verify the downloded file in the local folder
		verifyDownloadFile("ExportedEstimate.xlsx");
	    
		//close Browser
		driver.close();
	}

}
