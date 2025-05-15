package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {
	
	WebDriver driver;
	
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDD;
	
	@FindBy(id="phone")
	private WebElement phoneEdt;
	
	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}
	
	public WebElement getPhoneEdt() {
		return phoneEdt;
	}


	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public void createOrg(String orgName) {  //creating org with just name
		
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	
	}
	
    public void createOrg(String orgName, String pindustry) {		//if creating org with dropdown
		orgNameEdt.sendKeys(orgName);
		Select sel=new Select(industryDD);
		sel.selectByVisibleText(pindustry);
		saveBtn.click();
	
	}

}
