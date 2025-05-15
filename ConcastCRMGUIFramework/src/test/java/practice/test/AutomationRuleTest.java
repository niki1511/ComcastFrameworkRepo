package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;

/**
 * test class for Rules of Automation
 * @author Nikki
 */

public class AutomationRuleTest extends BaseClass{
	/**
	 * scenario1:login==>navigate to contact
	 */
	
	@Test
	public void searchContactTest() {
		/* step1: login to application	 */
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp("url", "username", "password");
	}

}
