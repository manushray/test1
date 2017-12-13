package appiumTest;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import org.testng.annotations.BeforeClass;


import org.openqa.selenium.WebElement;



public class TestWebApplication {

	static AppiumDriver driver;
	static String Parent_Window;
	public static DesiredCapabilities capability = new DesiredCapabilities();
	
	@BeforeClass()
	public static void testbrowsermain() throws MalformedURLException, InterruptedException {
		
		
		capability.setCapability("automationName", "XCUITest");
		capability.setCapability("deviceName", "Shradha's iPhone");
		capability.setCapability("platformName", "iOS");
		capability.setCapability("Version", "10.3.3");
		capability.setCapability("udid", "382fec0fdd486a711fb9945a79c7f16237298c7f");
		capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.apple.mobilesafari");
		capability.setCapability(IOSMobileCapabilityType.SHOW_IOS_LOG, true);
		capability.setCapability(IOSMobileCapabilityType.NATIVE_WEB_TAP, true);
		capability.setCapability(IOSMobileCapabilityType.BROWSER_NAME, "Safari");
		
		
		driver=new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capability);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	 public void testWebElement(String socialmedia) throws InterruptedException{
		  
		 String href ="font-thin contact_text";
			String hrefAttribute="class";
		 
		  List<WebElement> anchors = driver.findElements(By.tagName("a"));
	      Iterator<WebElement> i = anchors.iterator();
	      
	      while(i.hasNext()) {
	          WebElement anchor = i.next();
	          System.out.println(anchor.getText());
	          if(anchor.getAttribute(hrefAttribute).contains(href) && anchor.getText().equals(socialmedia)) {
	        	  anchor.click();
	        	  Thread.sleep(4000);
	              break;
	          }
		 
		  Thread.sleep(500);
	  }
		 return;
	 }
	
		@Test( priority = 1 )
		public void fobSolutions() throws InterruptedException {
			//This will open the web application and click one of the tab 
			
			driver.get("http://www.fob-solutions.com/");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			driver.findElement(By.xpath("/html/body/nav/div/div[1]/button")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			driver.findElement(By.id("menulink menu-link-2")).click();
			String key=driver.findElement(By.cssSelector("#slide-2 > div > div.row.title-row > div")).getText();
			Assert.assertEquals(key,"SERVICES");  	
		
		}
		
		@Test( priority = 2 )
		public void ContactUs() throws InterruptedException {
			//This will open the contact us page of the web application 
			
			driver.findElement(By.xpath("/html/body/nav/div/div[1]/button")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			driver.findElement(By.id("menulink menu-link-6")).click();
			String key=driver.findElement(By.cssSelector("#slide-6 > div > div.row.title-row > div")).getText();
			Assert.assertEquals(key,"CONTACTS");
				    	
		}
			
		@Test( priority = 4 )
		public void linkdeln() throws InterruptedException {
			//This will open the linkedln page of the web application 
			
			String socialmedia="fob-solutions";
			
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			
			//To handle the new window 
			String winHandleBefore = driver.getWindowHandle(); 
			testWebElement(socialmedia);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			for(String winHandle : driver.getWindowHandles())
			{				
				driver.switchTo().window(winHandle);
			}	
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String url = driver.getCurrentUrl();
			Assert.assertEquals(url,"https://www.linkedin.com/company/fob-solutions");
	    
		}
		
		@Test( priority = 3 )
		public void facebook() throws InterruptedException {
			 // To call the contact number of the web application which contains the number in the pop-up    
			    String socialmedia="+372 516 9307";
			
		    	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		   
				testWebElement(socialmedia);
				
				Alert alert = driver.switchTo().alert();		
        		    
		        String alertMessage= driver.switchTo().alert().getText();		
		        		
		        // Displaying alert message		
		        Assert.assertEquals(socialmedia,"+372 516 9307"); 
		        Thread.sleep(5000);
		        		
		        // Accepting alert		
		        alert.dismiss();
			
		}  
		
}
		



