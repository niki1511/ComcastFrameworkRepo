package com.comcast.crm.AnnotationScripts2;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

@Listeners(com.comcast.crm.listenerUtility.ListenerImplementationClass.class)

public class CreateOrgTest extends BaseClass {

	@Test(groups = "smokeTest")

	public void createOrgTest() throws IOException {
		
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");

		// Read TestScript data from excel file
		String orgName = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();

		// Step2: navigate to organization
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step3: click on create Organization 
		UtilityClassObject.getTest().log(Status.INFO, "create new org");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();
		
		/*enter details and create organization */
		CreateNewOrganizationPage cop = new CreateNewOrganizationPage(driver);
		cop.createOrg(orgName);

		// verify Header message Expected Result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "is created");
		} else {
			System.out.println("Failed");
		}

		// Verify Header orgName info ExpectedResult
		String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if (actOrgName.equals(orgName)) {
			System.out.println(orgName + "is created");
		} else {
			System.out.println("Failed");
		}
	}

	@Test(groups="regressionTest")

	public void createOrgwithPhoneNumberTest() throws IOException {
		// Read TestScript data from excel file

		String orgName = elib.getDataFromExcel("org", 7, 2) + jlib.getRandomNumber();
		String phoneNumber = elib.getDataFromExcel("org", 7, 3);

		// Step2: navigate to organization
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step3: click on create Organization and enter details and create organization
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();
		CreateNewOrganizationPage cop = new CreateNewOrganizationPage(driver);
		cop.getOrgNameEdt().sendKeys(orgName);

		cop.getPhoneEdt().sendKeys(phoneNumber);
		cop.getSaveBtn().click();

		// verify and validation

		String num = driver.findElement(By.id("dtlview_Phone")).getText();
		if (num.contains(phoneNumber)) {
			System.out.println(phoneNumber + "verified");
		} else {
			System.out.println("not verified");
		}

	}
}
