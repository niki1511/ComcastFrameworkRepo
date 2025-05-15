package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class DeleteOrgPOMTest {
	
	public static void main(String[] args) throws IOException {
		
		FileUtility flib= new FileUtility();
		ExcelUtility elib= new ExcelUtility();
		JavaUtility jlib= new JavaUtility();
		WebDriverUtility wlib= new WebDriverUtility();
		
		String Browser=flib.getDataFromPropertiesFile("browser");
		String Url=flib.getDataFromPropertiesFile("url");
		String Username=flib.getDataFromPropertiesFile("username");
		String Password=flib.getDataFromPropertiesFile("password");
			
	    //Read TestScript data from excel file
	
	     String orgName=elib.getDataFromExcel("org", 10, 2) +jlib.getRandomNumber();
	    
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
	    
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(35));
	    driver.get(Url);
	    
	    //LoginPage lp=PageFactory.initElements(driver, LoginPage.class);
	    
	    LoginPage lp=new LoginPage(driver);
	    
	    lp.loginToapp(Url, "admin", "admin");
	    
	    //Create Organization page
	    HomePage hp=new HomePage(driver);
	    hp.getOrgLink().click();
	    
	    //click on create new organization
	    
	    OrganizationPage op= new OrganizationPage(driver);
	    op.getCreateOrgBtn().click();
	    
	    //create new organization by entering details
	     CreateNewOrganizationPage cn= new CreateNewOrganizationPage(driver);
	     cn.createOrg(orgName);
	     
	     //verify the org name created
	    OrganizationInfoPage oip= new OrganizationInfoPage(driver);
	    String actOrgName=oip.getHeaderMsg().getText();
	    if(actOrgName.contains(orgName)) {
	    	System.out.println(orgName + "verified");
	    }else {
	    	System.out.println(orgName + "not verifired");
	    }
	    
        //go back to org page
	    hp.getOrgLink().click();
	       
	    //search for created organization
	    
	    op.getSearchEdt().sendKeys(orgName);
	    wlib.select(op.getSearchDD(), "Organization Name");
	    op.getSearcBtn().click();
	    
	  //in dynamic table select and delete
	    //static to dynamic xpath
	    
	    driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
	    driver.switchTo().alert().accept();
	    
	    
	    
	    //logout
	    
	    hp.logOut();  //from home page 	    
	    driver.quit();
	    
	}

}
