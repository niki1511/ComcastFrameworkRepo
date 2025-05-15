package practice.test;

import org.testng.annotations.Test;

import junit.framework.Assert;

public class RetrysampleTESTCasesTest {
	
	@Test(retryAnalyzer = com.comcast.crm.listenerUtility.RetryListenerImplementationClass.class)

	public void createInvoiceTest() {
		System.out.println("execute invoice test");
		Assert.assertEquals("", "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}

}
