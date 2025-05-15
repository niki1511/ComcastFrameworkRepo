package practice.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

import junit.framework.Assert;

public class ListenerInvoiceTest extends BaseClass{
	
	@Test
	public void createInvoiceTest() {
		System.out.println("execute invoice test");
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
	
	@Test
	public void createInvoicewithContactTest() {
		System.out.println("execute inoice test with contact");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}

}
