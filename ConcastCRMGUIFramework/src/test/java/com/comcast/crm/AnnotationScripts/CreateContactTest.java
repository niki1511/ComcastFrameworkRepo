package com.comcast.crm.AnnotationScripts;
/**
 * @author nikki
 */

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
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

import junit.framework.Assert;

public class CreateContactTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createContactTest() throws IOException {

		/* Generate The random number and Read the test script from excel */
		String LastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		/* Step2: navigate to contact */
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		/* step3: click on create contact */
		ContactPage cp = new ContactPage(driver);
		cp.getContactBtn().click();

		// step 4: enter details and phone number and create contact
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastNameEdt().sendKeys(LastName);
		cncp.getSaveBtn().click();

		// verify and validation header
		String header = driver.findElement(By.className("dvHeaderText")).getText();
		if (header.contains(header)) {
			System.out.println(header + "verified");
		} else {
			System.out.println("not verified");
		}
		String num = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (num.contains(LastName)) {
			System.out.println(LastName + "verified");
		} else {
			System.out.println("not verified");
		}
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

//		// verify and validation header
		
//		String sDate = driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']")).getText();
//		if (sDate.contains(startdate)) {
//			System.out.println(startdate + "verified");
//		} else {
//			System.out.println("not verified");
//		}
//
//		String eDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
//		if (eDate.contains(end_date)) {
//			System.out.println(end_date + "verified");
//		} else {
//			System.out.println("not verified");
//		}
//
	}

	@Test(groups="regressionTest")
	public void createContactWithOrgTest() throws IOException {
		
		String orgName=elib.getDataFromExcel("contact", 7, 2)+jlib.getRandomNumber();
		String contactLastName=elib.getDataFromExcel("contact", 7, 3);

		// Step2: navigate to organization
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		// step3: click on create Organization and enter details and create organization		
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateOrgBtn().click();
		CreateNewOrganizationPage cop=new CreateNewOrganizationPage(driver);
		cop.createOrg(orgName);

		// verify Header message Expected Result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "is created");
		} else {
			System.out.println("Failed");
		}

		// step5: navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();

		// step6: click on create contact
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 7: enter details and create new organization
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//input[@name='account_name']//following-sibling::img[@title='Select']")).click();

		// switch to child window
		wlib.switchToTabOnchildURL(driver, "module=Accounts");

		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[contains(text(),'" + orgName + "')]")).click();

		// switch to parent window
		wlib.switchToTabOnParentTitle(driver, "Contacts&action");

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify Header message Expected Result
		headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		System.out.println(contactLastName);
		if (headerInfo.contains(contactLastName)) {
			System.out.println(contactLastName + "is created");
		} else {
			System.out.println(contactLastName + "Failed");
		}

		// Verify Header orgName info ExpectedResult
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actOrgName);
		if (actOrgName.trim().equals(orgName)) {
			System.out.println(orgName + "is created");
		} else {
			System.out.println(orgName + "Failed");
		}

	}

}
