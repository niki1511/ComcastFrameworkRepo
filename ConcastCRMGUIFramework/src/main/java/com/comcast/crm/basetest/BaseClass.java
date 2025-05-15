package com.comcast.crm.basetest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v114.database.Database;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

import lombok.experimental.UtilityClass;

public class BaseClass {
	
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	
	//create object 
	public FileUtility flib= new FileUtility();
	public ExcelUtility elib= new ExcelUtility();
	public JavaUtility jlib= new JavaUtility();
	public WebDriverUtility wlib=new WebDriverUtility();
	public DatabaseUtility dlib= new DatabaseUtility();
	

	@BeforeSuite(groups={"smokeTest", "RegressionTest"})
	public void configBS() {
		System.out.println("=====connect to db======");
		//dlib.getDbconnection();
		
		
	}
		@Parameters("BROWSER")
	@BeforeClass(groups={"smokeTest", "RegressionTest"})
	public void configBC(@Optional("chrome") String browser) throws IOException {
		System.out.println("===launch the browser==");
		String Browser=browser;
				//flib.getDataFromPropertiesFile("browser");				 
		 if(Browser.equals("chrome")) {
			 driver=new ChromeDriver();
		 }else if(Browser.equals("edge")) {
			 driver=new EdgeDriver();
		 }else if(Browser.equals("firefox")) {
			 driver=new FirefoxDriver();
		 }else {
			 driver=new ChromeDriver();
		 }
		 sdriver=driver;
		 UtilityClassObject.setDriver(driver);
	}
	
	@BeforeMethod(groups={"smokeTest", "RegressionTest"})
	public void configBM() throws IOException {
		System.out.println("login");
		String Url=flib.getDataFromPropertiesFile("url");
		String Username=flib.getDataFromPropertiesFile("username");
		String Password=flib.getDataFromPropertiesFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp(Url, Username, Password);
	}
	
	@AfterMethod(groups={"smokeTest", "RegressionTest"})
	public void configAM() {
		System.out.println("logout");
		HomePage hp=new HomePage(driver);
		hp.logOut();
	}
	
	@AfterClass(groups={"smokeTest", "RegressionTest"})
	public void configAC() {
		System.out.println("==close the browser==");
		driver.quit();
	}
	
	@AfterSuite(groups={"smokeTest", "RegressionTest"})
	public void configAS() {
		System.out.println("=====close db======");
	}


}
