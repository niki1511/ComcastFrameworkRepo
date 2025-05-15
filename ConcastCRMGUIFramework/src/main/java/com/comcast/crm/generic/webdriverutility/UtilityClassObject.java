package com.comcast.crm.generic.webdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {
	
	public static ThreadLocal<ExtentTest> test=new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();
	
	public static ExtentTest getTest() {  //getter method
		return test.get();
	}
	
	public static void setTest(ExtentTest actTest) {   //setter method
		test.set(actTest);
	}
	
	public static WebDriver getDriver() {   //getter method
		return driver.get() ;
	}
	
	public static void setDriver(WebDriver actDriver) {        //setter method
		driver.set(actDriver);
	}

}
