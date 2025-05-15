package com.comcast.crm.objectrepositoryutility;
/**
 * @author Nikki
 * 
 * Contains Login page elements and business library like login()
 * 
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility {  //rule1- create separate java class 
                          //rule2- object creation
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	private WebElement usernamedit;
	
	@FindBy(name="user_password")
	private WebElement pswdedit;
	
	@FindBy(id="submitButton")
	private WebElement loginbutton;
	                    //Rule3- Object Initialization it will be done in testscript
	
	//rule 4: use getters object encapsulation
	public WebElement getUsernamedit() {
		return usernamedit;
	}
	public WebElement getPswdedit() {
		return pswdedit;
	}
	public WebElement getLoginbutton() {
		return loginbutton;
	}
	
	//rule 5-> provide action{business method}
	/**
	 * login to application
	 * @param url
	 * @param username
	 * @param password
	 */
	
	public void loginToapp(String url, String username, String password) {
		driver.manage().window().maximize();
		driver.get(url);
		waitForPageToLoad(driver);
		usernamedit.sendKeys(username);
		pswdedit.sendKeys(password);
		loginbutton.click();
	}
}
