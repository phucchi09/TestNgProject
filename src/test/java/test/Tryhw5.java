package test;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Tryhw5
{
	 WebDriver driver;
		
		@BeforeMethod 
		 public void Hw5Beginner()
		 {
			    System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		        driver = new ChromeDriver();
			    driver.manage().window().maximize();
			    driver.manage().deleteAllCookies();
			    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);		    
			    driver.get("http://techfios.com/test/billing/?ng=admin/"); 		    
		 }
		
		@Test(priority = 1)
		public void addingRandomizeAccountAndBalance() throws InterruptedException
		{
			driver.findElement(By.xpath("//*[@type='text']")).sendKeys("techfiosdemo@gmail.com");
			Thread.sleep(1000);		
			driver.findElement(By.xpath("//input[contains(@placeholder,'assword')]")).sendKeys("abc123");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[contains(text(), 'Sign in')]")).click();		
			Thread.sleep(4000);
		
			//    5. Click on Bank & Cash
			By BANK_N_BALANCE_MENU_LOCATOR = By.xpath("//ul[@id='side-menu']/descendant::span[text()='Bank & Cash']");				
			driver.findElement(BANK_N_BALANCE_MENU_LOCATOR).click(); Thread.sleep(3000);	
			
			//  6. Click on New Account
			By NEW_ACCOUNT_PAGE_LOCATOR = By.linkText("New Account");
			waitForElement(NEW_ACCOUNT_PAGE_LOCATOR, driver, 30); 
			driver.findElement(NEW_ACCOUNT_PAGE_LOCATOR).click(); Thread.sleep(3000);
			
			//  7. Fill in the Add New Account Form (Randomize Account Title and Balance)
			String expectedAccountTitle = "JuAccount TestNo " + new Random().nextInt(999);
			driver.findElement(By.id("account")).sendKeys(expectedAccountTitle); Thread.sleep(3000);		
			driver.findElement(By.id("description")).sendKeys("NuJu testing new Account.");		
			String randIntialBalance = " "+ new Random().nextInt(999);
			driver.findElement(By.xpath("//*[@id='balance']")).sendKeys(randIntialBalance); Thread.sleep(3000);		
			driver.findElement(By.xpath("//button[@type='submit' and @class = 'btn btn-primary']")).click();	
			Thread.sleep(4000);		
			
		/****************** INTERMEDIATE  verify New Account Created****************************************
			
			//      //*[@id="page-wrapper"]/div[3]/div[1]/text()[3]
			 //By ACCOUNTS_MESSAGE_LOCATOR = By.xpath("//h2[contains(text(), 'Accounts')]");
			
			By ACCOUNTS_MESSAGE_LOCATOR = By.xpath("//*[@id='page-wrapper']/div[3]/div[1]/text()[3]");
			 boolean status = false;
				try {
				      status = driver.findElement(ACCOUNTS_MESSAGE_LOCATOR).isDisplayed();
				      System.out.println("Message is displayed");
				    }
				catch (NoSuchElementException e)
				   {
				     status = false;
				   }

				// Assertion to validate whether the Panel Header Displayed
				Assert.assertTrue(status);
		
			
		     By ACCOUNTS_MESSAGE_LOCATOR = By.xpath("//h2[contains(text(), 'Accounts')]");
			//By LIST_CONTACT_SIDE_NAV_LOCATOR = By.linkText("List Contacts");
			// 7. Click on List Contact,
			driver.findElement(ACCOUNTS_MESSAGE_LOCATOR);    //  yo mugi le message bheteko6

			// 8. Verify that account  added.
			String displayedAccountName = driver.findElement(ACCOUNTS_MESSAGE_LOCATOR).getText();   // displayedAccountName --> yesma  message save vayo 

			// Print to see
			
			Assert.assertEquals("Relevant contact name not found!", "Accounts", displayedAccountName);
			
*/
			/******************** Advance*********************/
			//   10. Scroll Down
			@Test(priority = 2)
			public void scrollDown() throws InterruptedException
			{
				
			}
			
		


		}

		private void waitForElement(By locator, WebDriver driver1, int time) 
		   {
			 new WebDriverWait(driver1, time).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)); 		
		   }
	 
		@AfterMethod
		 public void closeEverything()
		  {		
			 driver.close();
			 driver.quit();
		  }
		

}
