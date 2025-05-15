package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
WebDriver driver;

	public ContactInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="dvHeaderText")
	private WebElement headerMsg;
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	@FindBy(xpath="//span[@id='dtlview_Support Start Date']")
    private WebElement actstartDate;
	
	public WebElement getActstartDate() {
		return actstartDate;
	}
	@FindBy(id="dtlview_Support End Date")
    private WebElement actendDate;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement orgName;
	
	public WebElement getActendDate() {
		return actendDate;
	}
	
	public WebElement getOrgName() {
		return orgName;
	}
	

}
