package com.comcast.crm.AnnotationScripts;

import static org.testng.Assert.assertEquals;

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
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

@Listeners(com.comcast.crm.listenerUtility.ListenerImplementationClass.class)

public class AssertCreateContactTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createContactTest() throws IOException {
		 UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		// Generate The random number and Read the test script from excel
		String LastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		// Step2: navigate to contact
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact page");
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step3: click on create contact
		UtilityClassObject.getTest().log(Status.INFO, "create new org");
		ContactPage cp = new ContactPage(driver);
		cp.getContactBtn().click();

		// step 4: enter details and phone number and create contact
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastNameEdt().sendKeys(LastName);
		cncp.getSaveBtn().click();

		// verify and validation header
		cp.getHeaderMsg();
		String actheader = cp.getHeaderMsg().getText();
		//actheader is dynamic data so use contains method
		boolean status1 = actheader.contains(LastName);
		Assert.assertEquals(status1,true);
		
		String actLastName = cp.getLastNameEdt().getText();
		SoftAssert sf=new SoftAssert();
		sf.assertEquals(actLastName, LastName);
		sf.assertAll();
		
	}


	@Test(groups="regressionTest")
	public void createContactwithSupportDateTest() throws IOException {

		// Read the test script from excel
		String LastName = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

		// Step2: navigate to contact
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step3: click on create contact
		ContactPage cp = new ContactPage(driver);
		cp.getContactBtn().click();

		// step 4: enter details like last name and start and end date and create
		// contacts
		String startdate = jlib.getSystemDateYYYYDDMM();
		String end_date = jlib.getRequiredDateYYYYDDMM(30);
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastNameEdt().sendKeys(LastName);
		cncp.getStartDateinput(startdate);
		cncp.getEndDateinput(end_date);
		cncp.getSaveBtn();

		// verify and validation header
		ContactInfoPage cip= new ContactInfoPage(driver);
		String headermsg=cip.getHeaderMsg().getText();
		String actualstartDate=cip.getActstartDate().getText();
		Assert.assertEquals(actualstartDate.trim(), startdate);
		
		String actualendDate=cip.getActendDate().getText();
		Assert.assertEquals(actualendDate.trim(), end_date);

	}
	@Test(groups="regressionTest")
	public void createContactWithOrgTest() throws IOException {
	    
	    String orgName = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
	    String contactLastName = elib.getDataFromExcel("contact", 7, 3);

	    // Step2: navigate to organization
	    HomePage hp = new HomePage(driver);
	    hp.getOrgLink().click();
	    
	    // step3: click on create Organization and enter details and create organization        
	    OrganizationPage op = new OrganizationPage(driver);
	    op.getCreateOrgBtn().click();
	    CreateNewOrganizationPage cop = new CreateNewOrganizationPage(driver);
	    cop.createOrg(orgName);

	    // verify Header orgName info Expected Result
	    OrganizationInfoPage oip = new OrganizationInfoPage(driver);
	    String headerInfo = oip.getHeaderMsg().getText();
	    Assert.assertTrue(headerInfo.contains(orgName), "Org name not matched in header");

	    // step5: navigate to contact module
	    hp.getContactLink().click();

	    // step6: click on create contact
	    ContactPage cp = new ContactPage(driver);
	    cp.getContactBtn().click();

	    // step 7: enter details and create new contact
	    CreateNewContactPage cncp = new CreateNewContactPage(driver);
	    cncp.getLastNameEdt().sendKeys(contactLastName);
		driver.findElement(By.xpath("//input[@name='account_name']//following-sibling::img[@title='Select']")).click();


	    // switch to child window
	    wlib.switchToTabOnchildURL(driver, "module=Accounts");

	    driver.findElement(By.id("search_txt")).sendKeys(orgName);
	    driver.findElement(By.name("search")).click();
	    driver.findElement(By.xpath("//a[contains(text(),'" + orgName + "')]")).click();

	    // switch to parent window
	    wlib.switchToTabOnParentTitle(driver, "Contacts&action");

	    cncp.getSaveBtn().click();

	    // Verify Organization name in contact details
	    ContactInfoPage cip = new ContactInfoPage(driver);
	    String actOrgName = cip.getOrgName().getText();
	    Assert.assertEquals(actOrgName.trim(), orgName, "Organization name in contact details doesn't match expected value");
	}

}
