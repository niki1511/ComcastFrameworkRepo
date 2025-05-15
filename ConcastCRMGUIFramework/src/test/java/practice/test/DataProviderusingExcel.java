package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class DataProviderusingExcel {

    @Test(dataProvider = "getData")
    public void getProductInfoTest(String brandName, String productName) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.amazon.in/?&tag=googhydrabk1-21&ref=pd_sl_5szpgfto9i_e&adgrpid=155259813593&hvpone=&hvptwo=&hvadid=748926027055&hvpos=&hvnetw=g&hvrand=3208065406478911495&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9062134&hvtargid=kwd-64107830&hydadcr=14452_2417699&gad_source=1");
       // System.out.println("Page title is: " + driver.getTitle());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox"))).sendKeys(brandName, Keys.ENTER);

        // Modify XPath if the product name or structure changes
       
        // String x = "//span[text()='"+productName+"']/../../../../div[3]/div[1]/div/div[1]/div[2]/div[1]/a/span/span[1]";
        String x = "//span[text()='" + productName + "']/ancestor::div[@data-asin][1]//span[@class='a-price-whole']";

        String price = driver.findElement(By.xpath(x)).getText();
        System.out.println("Price: " + price);

    }
    
    @DataProvider
	public Object[][] getData() throws IOException{
    	ExcelUtility elib=new ExcelUtility();
    	int row_count=elib.getRowCount("product");
    	
		Object[][] objArr=new Object[row_count][2];
		
		for(int i=0;i<row_count;i++) {
			objArr[i][0]=elib.getDataFromExcel("product", i+1, 0);
			objArr[i][1]=elib.getDataFromExcel("product", i+1, 1);
		}
//		objArr[0][0]="iphone";  //1st set of data
//		objArr[0][1]="Apple iPhone 15 (128 GB) - Black";
//		
//		objArr[1][0]="iphone";   //2nd set of data
//		objArr[1][1]="Apple iPhone 15 (128 GB) - Pink";
		
		return objArr;
	}

}
