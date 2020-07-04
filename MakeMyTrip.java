package week1.selenium.workout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		
		//setting up driver and browser property
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		//create an object for chrome driver
		ChromeDriver driver = new ChromeDriver();
		
			//Launch URL
			driver.get("https://www.makemytrip.com/");
		
				driver.manage().window().maximize(); //maximize the window			
			
				driver.manage().deleteAllCookies();	//delete all cookies
			
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); //Implicitly wait to load all the webelements in DOM 
			
				WebDriverWait wait = new WebDriverWait(driver,30);//ExpliciWait
			
				Actions builder = new Actions(driver);//Create an object for Action class 
			
				
			builder.moveToElement(driver.findElementByXPath("(//div[@class='chHeaderContainer']//li)[2]")).click().perform();//mousehover on Hotels and click
			
			builder.click(driver.findElementByXPath("(//div[@class='chHeaderContainer']//li)[2]")).perform();// click on Hotels
			
			
			
			//Click on CITY / HOTEL / AREA / BUILDING
			WebElement city = driver.findElementByXPath("//input[@id='city']");
			city.click();
			Thread.sleep(2000);
			
				//Enter city as Goa, 
				driver.findElementByXPath("//div[@role='combobox']/input").sendKeys("Goa");
			
			
				//choose Goa, India 
				driver.findElementByXPath("//p[text()='Goa, India']").click();
				
				
			//Enter Check in date as Next month 15th (July 15) and Check out as start date+4 
			
				//Get current month
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("MMMMMMMMM");
				String currentMonth = formatter.format(date);
			
				
			
				//Get DayPicker Month
				String daypickerMonth = driver.findElementByXPath("(//div[@class='DayPicker-Caption']/div)[1]").getText();
				
			
					//StartDate -CheckIn Date
					int startDate = 15;
									
					//Checkout Date
					int checkoutDate = startDate+4;

			
				//if daypicker month matches current month then select Check in date as next month 15th
				if (daypickerMonth.contains(currentMonth)) 
				{
					//Select Check in date as Next month 15th 
					driver.findElementByXPath("(//div[@class='DayPicker-Caption']/div)[2]//following::div[text()='"+startDate+"']").click();
					Thread.sleep(2000);
					
					//Select Check out date as start date+4 
					driver.findElementByXPath("(//div[@class='DayPicker-Caption']/div)[2]//following::div[text()='"+checkoutDate+"']").click();
					Thread.sleep(2000);
				}
				
				//if first displaying day picker month is not matching with the current month
				else
				{
					//Select Check in date as Next month 15th 
					driver.findElementByXPath("(//div[@class='DayPicker-Caption']/div)[1]//following::div[text()='"+startDate+"']").click();
					Thread.sleep(2000);
					
					//Select Check out date as start date+4 
					driver.findElementByXPath("(//div[@class='DayPicker-Caption']/div)[1]//following::div[text()='"+checkoutDate+"']").click();
					Thread.sleep(2000);
				}
			
			//Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
				
				//Click on ROOMS & GUESTS
				driver.findElementByXPath("//input[@id='guest']").click();
			
				//click 2 Adults
				driver.findElementByXPath("//li[@data-cy='adults-2']").click();
			
				//click one Children
				driver.findElementByXPath("//li[@data-cy='children-1']").click();
				
				//Select age 12
				WebElement dropdown = driver.findElementByXPath("//select[@class='ageSelectBox']");
			
				Select option = new Select(dropdown);
			
				option.selectByVisibleText("12");
				
				//click Apply button
				driver.findElementByXPath("//button[text()='APPLY']").click();
				
			//Click Search button 
				WebElement searchButton = driver.findElementByXPath("//button[@id='hsw_search_button']");
			
				wait.until(ExpectedConditions.visibilityOf(searchButton));
			
				searchButton.click();
			
				Thread.sleep(3000);
		
		   //Select locality as Baga
			  
				driver.findElementByXPath("//label[@for='mmLocality_checkbox_35']").click();
			  
				WebElement firstSearchResult = driver.findElementByXPath("(//div[@class='listingRowOuter'])[1]");
			  
				wait.until(ExpectedConditions.visibilityOf(firstSearchResult));
			  
			  
		   //Select user rating as 3&above(good) under Select Filters
			   driver.findElementByXPath("//label[contains(text(),'3 & above (Good)')]").click(); 
			   
			   Thread.sleep(2000);
			  
		
		  //Select Sort By: Price-Low to High 
			   
			  WebElement sortBy = driver.findElementByXPath("//div[@id='hlistpg_sortby_option']");
			  
			  wait.until(ExpectedConditions.visibilityOf(sortBy));
			  
			  sortBy.click();
			  
			  driver.findElementByXPath("//li[text()='Price - Low to High']").click();
			  //Thread.sleep(4000);
			  wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//div[@class='listingRowOuter'])[1]")));
			  
			  
			  driver.manage().deleteAllCookies();//delete all cookies before clicking first resulting hotel
			  
		  //Click on the first resulting hotel and go to the new window
			  
			  driver.findElementByXPath("(//div[@class='listingRowOuter'])[1]").click();
			  
			  //Get into newly opened window
			  Set<String> firstandSecondWindows = driver.getWindowHandles();
			  //System.out.println(firstandSecondWindows);
			  
			  List<String> list = new ArrayList<String>(firstandSecondWindows);
			  
			  String secondWindow = list.get(1);
			  
			  //Switch into new window 
			  driver.switchTo().window(secondWindow);
			  
		  //Print the Hotel Name 
			  String hotelName = driver.findElementByXPath("//h1[@id='detpg_hotel_name']").getText();
			  
			  System.out.println("Hotel Name Selected for Booking is : "+hotelName);
			  
	      //Click VIEW THIS COMBO button under Recommended Combo
			  
			  try {
				  
				//Click VIEW THIS COMBO button
				  WebElement viewcomboButton = driver.findElementByXPath("//button[text()='VIEW THIS COMBO']");
				  wait.until(ExpectedConditions.visibilityOf(viewcomboButton));
				  viewcomboButton.click();
				  Thread.sleep(2000);
				  
				//Click on BOOK THIS COMBO button
				  WebElement bookThisComboButton = driver.findElementByXPath("//span[text()='Book this combo']");
				  wait.until(ExpectedConditions.visibilityOf(bookThisComboButton));
				  bookThisComboButton.click();
				  wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[@id='revpg_total_payable_amt']")));
				  
			} catch (Exception e) {
				
				driver.findElementByXPath("//a[text()='BOOK THIS NOW']").click();  //if view this combo button is not available then click Book this combo button directly
				wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[@id='revpg_total_payable_amt']")));
			}
			  
		  //Print the Total Payable amount 
			  
			  WebElement payableAmount = driver.findElementByXPath("//span[@id='revpg_total_payable_amt']");
			  
			  wait.until(ExpectedConditions.visibilityOf(payableAmount));
			  
			  String amount = payableAmount.getText();
			  
			  System.out.println("Total Payable amount: "+amount);
			 
	     //Close browser
			driver.close();
			
	}

}
