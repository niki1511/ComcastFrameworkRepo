package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {
	WebDriver driver;
	public CreateNewContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastNameEdt;
	
	@FindBy(name="support_start_date")
	private WebElement startDate;
	
	@FindBy(name="support_end_date")
	private WebElement endDATE;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getStartDate() {
		return startDate;
	}

	public WebElement getEndDATE() {
		return endDATE;
	}
    
	public WebElement getSaveBtn() {
		return saveBtn;
	}
    
	public void getStartDateinput(String startdate) {
		getStartDate().clear();
		getStartDate().sendKeys(startdate);
	}
	public void getEndDateinput(String enddate) {
		getEndDATE().clear();;
		getEndDATE().sendKeys(enddate);
	}
	public void createContactWithSupportDate(String lastName, String startDate, String endDate) {
		
	}
}
