package week1.selenium.workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ajio {
	public static String promoCode,discountPrice;
	public static void main(String[] args) throws InterruptedException {
		
			//Setting up driver and browser property
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			
				//To disable notifications
				ChromeOptions options = new ChromeOptions();
			
				options.addArguments("--disable-notifications");
			
			//create an object for chromedriver
			ChromeDriver driver = new ChromeDriver(options);
			
			//Launch URL
				
				driver.get("https://www.ajio.com/shop/sale");
			
				driver.manage().window().maximize(); //maximize the window
					
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);//Implicit wait to load webelements in DOM 
				
				WebDriverWait wait = new WebDriverWait(driver,30); //Explicit Wait
				
				String firstWindow = driver.getWindowHandle(); //get first window reference
				
				
			//Enter Bags in the Search field and Select Bags in Women Handbags
			
				driver.findElementByXPath("//input[@name='searchVal']").sendKeys("Bags");
				
				Actions builder = new Actions(driver);
			
					//move to Bags in WomenHandBags and click on it
					
					WebElement womenHandBags = driver.findElementByXPath("(//span[text()='Women Handbags'])[1]");
					
					builder.moveToElement(womenHandBags).click().perform();
					
			//Click on five grid and 
					
				driver.findElementByXPath("//div[@class='five-grid']").click();
				
				Thread.sleep(2000);		
					
				//Select SORT BY as "What's New"
			
				WebElement dropdown = driver.findElementByXPath("//div[@class='filter-dropdown']/select");
		
				Select option = new Select(dropdown);	
				
				option.selectByVisibleText("What's New");	
				
				wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='price']")));		
				
				
			//Enter Price Range Min as 2500 and Max as 5000 
			
				driver.findElementByXPath("//span[text()='price']").click();
				
				driver.findElementById("minPrice").sendKeys("2500");
				
				driver.findElementById("maxPrice").sendKeys("5000");
				
				driver.findElementByXPath("(//button[@type='submit'])[2]").click();
				Thread.sleep(6000);
			
			//Click on the product "TOMMY HILFIGER Sling Bag with Chain Strap" 							
				
				driver.findElementByXPath("(//div[text()='TOMMY HILFIGER']/following-sibling::div[text()='Sling Bag with Chain Strap'])[1]").click();
				
				Thread.sleep(3000);
				
				Set<String> windowHandles = driver.getWindowHandles();
				
				List<String> list = new ArrayList<String>(windowHandles);
				
				String secondWindow = list.get(1);
				
				//String firstWindow = list.get(0);
				
				driver.switchTo().window(secondWindow);
				
				
			//Verify the Coupon code for the price above 2890 is applicable for your product, if applicable then get the Coupon Code and the discount price for the coupon
				
				String promoDescription = driver.findElementByXPath("(//div[@class='promo-desc-block']/div)[2]").getText();
			
				Thread.sleep(2000);
								
				if (promoDescription.contains("2890 and Above")) {
					
					discountPrice = driver.findElementByXPath("(//div[@class='promo-desc-block']/div)[1]").getText().replaceAll("[^0-9]", "");
					
					System.out.println("Discount Price: "+discountPrice); // print discount price
					
					String promoTitle = driver.findElementByXPath("(//div[@class='promo-title-blck']/div)[1]").getText().replaceAll("Use Code", "");
					
					promoCode = promoTitle.replaceAll("\n", ""); 
					
					System.out.println("Promo Code displayed is: "+promoCode); // Print promo code
					
				}
				
			//Check the availability of the product for pincode 560043
				
				String productOrigionalPrice = driver.findElementByXPath("//div[@class='prod-sp']").getText().replaceAll("[^0-9]", "");
				
				System.out.println("Product Origional Price Displayed is: "+productOrigionalPrice);
				
				driver.findElementByXPath("//span[text()='Enter Pin-code To Know Estimated Delivery Date']").click();
				
				driver.findElementByXPath("//input[@name='pincode']").sendKeys("560043");
				
				driver.findElementByXPath("(//button[@type='submit'])[2]").click();
				
				//print the expected delivery date if it is available
				
				String expectedDeliveryDate = driver.findElementByXPath("(//ul[@class='edd-message-success-details']/li)[1]").getText();
				
				System.out.println(expectedDeliveryDate);
				
			//Click on Other Informations under Product Details and Print the Customer Care address, phone and email 
				
				driver.findElementByXPath("//div[@class='other-info-toggle']").click();
				
				String customerCareAddress = driver.findElementByXPath("(//li[@class='detail-list mandatory-info'])[7]").getText();
				
				System.out.println(customerCareAddress); //Print the Customer Care address, phone and email 
			
			//Click on ADD TO BAG and 
				
				driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
				
				Thread.sleep(9000);
				
				//Then GO TO BAG 
				driver.findElementByXPath("//span[text()='GO TO BAG']").click();
				
				Thread.sleep(3000);
				
		   //Check the Order Total before apply coupon 
				
				String Total = driver.findElementByXPath("//span[@class='price-value bold-font']").getText().replaceAll("[^0-9]", "");
				String orderTotal = Total.replace("0", "");				
				
				if (orderTotal.equals(productOrigionalPrice)) 
					System.out.println("Order total "+orderTotal+" matching with original price");
				else
					System.out.println("Order total "+orderTotal+" not matching with original price");
				
			//Enter Coupon Code and Click Apply
				
				driver.findElementById("couponCodeInput").sendKeys(promoCode);
				
				driver.findElementByXPath("//button[text()='Apply']").click();
				Thread.sleep(8000);
				
		   //Verify the discount price matches with the product price 
								
				String priceDisplayed = driver.findElementByXPath("(//div[@class='priceinfo']/div)[2]").getText().replaceAll("Rs.", "");
				
				String productPriceAfterDiscount = priceDisplayed.replace(",", "");
								
				String[] arrOfStr = productPriceAfterDiscount.split("[, .]+"); 
				
				String productPrice = arrOfStr[1].toString();// first value in product price after applying discount
				
				if (productPrice.equals(discountPrice))
					System.out.println("Discount price matches with the product price "); 
				else
					System.out.println("Discount price not matches with the product price ");
		 
				
			//Click on Delete and Delete the item from Bag 
				driver.findElementByXPath("//div[@class='delete-btn']").click();
				
				if (driver.findElementByXPath("//div[@class='card-delet']").isDisplayed()) {
					driver.findElementByXPath("(//div[@class='delete-btn'])[2]").click();					
				}
				
			//Close all the browsers
			driver.close();
			
			driver.switchTo().window(firstWindow);
			
			driver.close();
	}

}
