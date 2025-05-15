package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class CreateOrgVtigerPhoneNumberTest {
	public static void main(String args[]) throws IOException, InterruptedException {
		//load the property file
		FileUtility flib= new FileUtility();
		ExcelUtility elib= new ExcelUtility();
		JavaUtility jlib= new JavaUtility();
		
		String Browser=flib.getDataFromPropertiesFile("browser");
		String Url=flib.getDataFromPropertiesFile("url");
		String Username=flib.getDataFromPropertiesFile("username");
		String Password=flib.getDataFromPropertiesFile("password");
			
	    //Read TestScript data from excel file
	
	     String orgName=elib.getDataFromExcel("org", 7, 2) +jlib.getRandomNumber();
	     String phoneNumber=elib.getDataFromExcel("org", 7, 3);
	     
		 //Browser selection
		 
		 WebDriver driver=null;
		 
		 if(Browser.equals("chrome")) {
			 driver=new ChromeDriver();
		 }else if(Browser.equals("edge")) {
			 driver=new EdgeDriver();
		 }else if(Browser.equals("firefox")) {
			 driver=new FirefoxDriver();
		 }else {
			 driver=new ChromeDriver();
		 }
		 
		 //step1: login
		 driver.get(Url);
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		 driver.findElement(By.name("user_name")).sendKeys(Username);
	    driver.findElement(By.name("user_password")).sendKeys(Password);
	    driver.findElement(By.id("submitButton")).click();
	    
	   // Step2: navigate to organization	    
	    driver.findElement(By.linkText("Organizations")).click();	
	    
	    //step3: click on create Organization	    
	    driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	    
	    //step 4: enter details and phone number and create organization	   
	     driver.findElement(By.name("accountname")).sendKeys(orgName);
	     driver.findElement(By.id("phone")).sendKeys(phoneNumber);
	     driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	     
	     
	     //verify and validation
	     
	     String num=driver.findElement(By.id("dtlview_Phone")).getText();
	     if(num.contains(phoneNumber)) {
	    	 System.out.println(phoneNumber + "verified");
	     }else {
	    	 System.out.println("not verified");
	     }
	     
	   
	     //Step : logout	     
	     WebElement profileIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		  Actions action = new Actions(driver);
		  action.moveToElement(profileIcon).perform();

		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign Out")));

		  driver.findElement(By.linkText("Sign Out")).click();
		  driver.quit();
	}

}
