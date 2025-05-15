package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrgVtigerDropdownTest{
	public static void main(String[] args) throws IOException {
		
		FileUtility flib= new FileUtility();
		ExcelUtility elib= new ExcelUtility();
		JavaUtility jlib= new JavaUtility();
		
		String Browser=flib.getDataFromPropertiesFile("browser");
		String Url=flib.getDataFromPropertiesFile("url");
		String Username=flib.getDataFromPropertiesFile("username");
		String Password=flib.getDataFromPropertiesFile("password");
			
	    //Read TestScript data from excel file
	
	     String orgName=elib.getDataFromExcel("org", 4, 2) +jlib.getRandomNumber();
	     String industry=elib.getDataFromExcel("org", 4, 3);
	     String type=elib.getDataFromExcel("org", 4, 4);
	    
	    
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
	    
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
	    driver.get(Url);
	    
	    driver.findElement(By.name("user_name")).sendKeys(Username);
	    driver.findElement(By.name("user_password")).sendKeys(Password);
	    driver.findElement(By.id("submitButton")).click();
	    
	   // Step2: navigate to organization	    
	    driver.findElement(By.linkText("Organizations")).click();	
	    
	    //step3: click on create Organization	    
	    driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	    
	    //step 4: enter details select value from drop downs and create organization	   
	     driver.findElement(By.name("accountname")).sendKeys(orgName);
	     WebElement ele=driver.findElement(By.name("industry"));
	     Select sel=new Select(ele);
	     sel.selectByVisibleText(industry);
	     
	     WebElement ele2=driver.findElement(By.name("accounttype"));
	     Select sel2= new Select(ele2);
	     sel2.selectByVisibleText(type);
	     driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	     
	     //verify the dropdownIndustry and type Info
		  String actInduatries=driver.findElement(By.id("dtlview_Industry")).getText();  
		  if(actInduatries.contains(industry)) {
			  System.out.println(industry + "info is verified");
		  }else {
			  System.out.println("not verified");
		  }
		    
		 //Verify Type info drop down and ExpectedResult
		  String actType=driver.findElement(By.id("dtlview_Type")).getText();  
		  if(actType.contains(type)) {
			  System.out.println(type + "info is verified");
		  }else {
			  System.out.println("not verified");
		  }
	     
	     //Step 5: logout	     
		  WebElement profileIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		  Actions action = new Actions(driver);
		  action.moveToElement(profileIcon).perform();

		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign Out")));

		  driver.findElement(By.linkText("Sign Out")).click();
		  driver.quit();
	    

	}
}
