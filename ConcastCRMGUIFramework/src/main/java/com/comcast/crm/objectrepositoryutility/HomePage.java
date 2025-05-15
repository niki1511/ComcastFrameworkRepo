package com.comcast.crm.objectrepositoryutility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	

	@FindBy(linkText="Organizations")
	private WebElement orgLink;

	@FindBy(linkText="Contacts")
	private WebElement contactLink;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignLink;
	
	@FindBy(linkText="More")
	private WebElement moreLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOut;
	
	@FindBy(linkText="Products")
	private WebElement productLink;
	
	@FindBy(linkText="Documents")
	private WebElement documentLink;
	
	
	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getCampaignLink() {
		return campaignLink;
	}

	public WebElement getAdminImg() {
		return adminImg;
	}

	public WebElement getSignOut() {
		return signOut;
	}	

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}
	
	public WebElement getProductLink() {
		return productLink;
	}
	
	public WebElement geTDocumentLink() {
		return documentLink;
	}
	
	public void navigateToCampaignPage() {
		Actions action=new Actions(driver);
		action.moveToElement(moreLink).perform();
		campaignLink.click();
	}
	
	public void logOut() {
		Actions action=new Actions(driver);
		action.moveToElement(adminImg).perform();
		signOut.click();
	}
	
	public void clickSignOut() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(signOut));
	    signOut.click();
	}

	
	
}
