package test;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HW5AddAccntOnTechfios
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
	/****************** INTERMEDIATE ****************************************/
		
		/**************************Advance***********************/
		// 10. Scroll Down,
	    scrollDown(driver);
	    Thread.sleep(4000);
	    
	    //   11. Validate new account showed up in the bottom of the table
	    //   account xpath   //*[@id="page-wrapper"]/div[3]/div[2]/div/div/div[2]/table/tbody/tr[745]/td[1]
	    
	    //description xpath -->  //*[@id="page-wrapper"]/div[3]/div[2]/div/div/div[2]/table/tbody/tr[2]/td[2]
	    List<WebElement> accountNameElements = driver.findElements(By.xpath("//table/descendant::td"));
	    String actualAccountTitle = accountNameElements.get(accountNameElements.size()-3).getText();
		//Assert.assertTrue(isDescriptionMatch(expectedAccountTitle, accountNameElements), "Done");
	    Assert.assertTrue(isDescriptionMatch(expectedAccountTitle, accountNameElements), "Done");
		Thread.sleep(4000);
		 
		System.out.println(accountNameElements.get(accountNameElements.size()-3).getText());
	    String removeEle =accountNameElements.get(accountNameElements.size()-3).getText();
	    System.out.println("remove hun alagya element is " +actualAccountTitle);
	    
		
		// deleting Recent Account Name 
		//WebDriver driver = new ChromeDriver();
		JavascriptExecutor jsd;
		if (driver instanceof JavascriptExecutor) 
		{
		    jsd = (JavascriptExecutor) driver;
	
		    jsd.executeScript("return document.removeEle.remove()");

		//jsd.executeScript("tr:nth-child(759) > td:nth-child(3)".remove();
		}
	
	    
	}
	

	

	
	
	private boolean isDescriptionMatch(String expectedAccountTitle, List<WebElement> accountNameElements)
	{
		 for (int i=0; i < accountNameElements.size(); i++) 
		     {
		        if(expectedAccountTitle.equalsIgnoreCase(accountNameElements.get(i).getText()))
		        {
		        	System.out.println("Passed          lol");
		        }
		     {
		     return true;
		  }
		}
		 return false;
		
	}

	private void scrollDown(WebDriver a)
	    {
		  ((JavascriptExecutor)a).executeScript("scroll(0,12000)");
	    }


	
	private boolean isMessageMatch(String string, By aCCOUNTS_MESSG_LOCATOR, String string2) {
		// TODO Auto-generated method stub
		return false;
	}

	private void waitForElement(By locator, WebDriver driver1, int time) 
	   {
		 new WebDriverWait(driver1, time).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)); 		
	   }
 
	@AfterMethod
	 public void closeEverything()
	  {		
		// driver.close();
		// driver.quit();
	  }
	
}
