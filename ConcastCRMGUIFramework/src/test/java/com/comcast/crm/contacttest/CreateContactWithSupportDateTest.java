package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactWithSupportDateTest {
	public static void main(String args[]) throws IOException {
		
		FileUtility flib= new FileUtility();
		ExcelUtility elib= new ExcelUtility();
		JavaUtility jlib= new JavaUtility();
		
		String Browser=flib.getDataFromPropertiesFile("browser");
		String Url=flib.getDataFromPropertiesFile("url");
		String Username=flib.getDataFromPropertiesFile("username");
		String Password=flib.getDataFromPropertiesFile("password");
			 
	    // Read the test script from excel
		
		String LastName=elib.getDataFromExcel("contact", 4, 2)+jlib.getRandomNumber();
			 
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
	    
	   // Step2: navigate to contact	    
	    driver.findElement(By.linkText("Contacts")).click();	
	    
	    //step3: click on create contact	    
	    driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	    
	    //step 4: enter details like lastname and start and end date and create contacts
	    
	    
	    String startdate=jlib.getSystemDateYYYYDDMM();
	    String end_date=jlib.getRequiredDateYYYYDDMM(30);
	
	    driver.findElement(By.name("lastname")).sendKeys(LastName);
	    driver.findElement(By.name("support_start_date")).clear();
	    driver.findElement(By.name("support_start_date")).sendKeys(startdate);
	     
	     
	    driver.findElement(By.name("support_end_date")).clear();
	    driver.findElement(By.name("support_end_date")).sendKeys(end_date);
	     
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	     
	     
	     //verify and validation header
	     
	     String sDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
	     if(sDate.contains(startdate)) {
	    	 System.out.println(startdate+ "verified");
	     }else {
	    	 System.out.println("not verified");
	     }
	     
	     String eDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
	     if(eDate.contains(end_date)) {
	    	 System.out.println(end_date + "verified");
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
