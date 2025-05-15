package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	WebDriver driver;
	public ContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement contactBtn;
	
	@FindBy(className="dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id="dtlview_Last Name")
	private WebElement lastNameEdt;
	
	
	public WebElement getContactBtn() {
		return contactBtn;
	}
	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	
	
}
