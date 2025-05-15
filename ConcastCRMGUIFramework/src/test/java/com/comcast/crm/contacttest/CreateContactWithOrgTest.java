package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws IOException {
		//create object 
		FileUtility flib= new FileUtility();
		ExcelUtility elib= new ExcelUtility();
		JavaUtility jlib= new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
			
		String Browser=flib.getDataFromPropertiesFile("browser");
		String Url=flib.getDataFromPropertiesFile("url");
		String Username=flib.getDataFromPropertiesFile("username");
		String Password=flib.getDataFromPropertiesFile("password");		
		
	    //Read TestScript data from excel file
		 String orgName=elib.getDataFromExcel("contact", 7, 2)+jlib.getRandomNumber();
		 String contactLastName=elib.getDataFromExcel("contact", 7, 3);
	    
	    WebDriver driver=null;
	    
	    if(Browser.equals("chrome")) {
	    	driver=new ChromeDriver();
	    	
	    }else if(Browser.equals("firefox")) {
	    	driver=new FirefoxDriver();
	    }else if(Browser.equals("edge")) {
	    	driver=new EdgeDriver();
	    }else {
	    	driver=new ChromeDriver();
	    }
	    
	    //Step1: Login to app
	    
	    wlib.waitForPageToLoad(driver);
	    driver.get(Url);
	    
	    driver.findElement(By.name("user_name")).sendKeys(Username);
	    driver.findElement(By.name("user_password")).sendKeys(Password);
	    driver.findElement(By.id("submitButton")).click();
	    
	   // Step2: navigate to organization	    
	    driver.findElement(By.linkText("Organizations")).click();	
	    
	    //step3: click on create Organization	    
	    driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	    
	    //step 4: enter details and create organization	    	   
	     driver.findElement(By.name("accountname")).sendKeys(orgName);
	     driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	     
	     //verify Header message Expected Result
		  String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();  
		  if(headerInfo.contains(orgName)) {
			  System.out.println(orgName + "is created");
		  }else {
			  System.out.println("Failed");
		  }
		    
		 //step5: navigate to contact module	  
	    driver.findElement(By.linkText("Contacts")).click();	
	    
	    //step6: click on create contact	    
	    driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	    
	    //step 7: enter details and create new organization	   
	     driver.findElement(By.name("lastname")).sendKeys(contactLastName);	
	     driver.findElement(By.xpath("//input[@name='account_name']//following-sibling::img[@title='Select']")).click();
	     
	     //switch to child window
	     wlib.switchToTabOnchildURL(driver, "module=Accounts");
     
	     
	     driver.findElement(By.id("search_txt")).sendKeys(orgName);
	     driver.findElement(By.name("search")).click();
	     driver.findElement(By.xpath("//a[contains(text(),'"+orgName+ "')]")).click();
	     
	     //switch to parent window	     
	     wlib.switchToTabOnParentTitle(driver, "Contacts&action");
 
	     driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	     
	     //verify Header message Expected Result
		  headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();  
		  System.out.println(contactLastName);
		  if(headerInfo.contains(contactLastName)) {
			  System.out.println(contactLastName + "is created");
		  }else {
			  System.out.println(contactLastName +"Failed");
		  }
		    
		 //Verify Header orgName info ExpectedResult
		  String actOrgName=driver.findElement(By.id("mouseArea_Organization Name")).getText();
		  System.out.println(actOrgName);
		  if(actOrgName.trim().equals(orgName)) {
			  System.out.println(orgName + "is created");
		  }else {
			  System.out.println(orgName +"Failed");
		  }
     
	     //Step : logout	     
		  WebElement profileIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		  Actions action = new Actions(driver);
		  action.moveToElement(profileIcon).perform();
		  
		  WebElement logo=driver.findElement(By.linkText("Sign Out"));	  
		  wlib.waitForElementPresent(driver, logo);
		  driver.findElement(By.linkText("Sign Out")).click();
				  driver.quit();
	}

}
