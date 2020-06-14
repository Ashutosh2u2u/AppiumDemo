package PageObjectModel.AppiumFrwk;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.CheckoutPage;
import pageObjects.FormPage;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

public class Ecomm_TC_4 extends base
{
	@Test
	public void totalPriceValidation() throws InterruptedException, IOException
	{
		startServer();
        //lauch the shopping site---fill login form
		AndroidDriver<AndroidElement> driver=capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		FormPage formpage=new FormPage(driver);
		//formpage.nameField.sendKeys("Hello");
		formpage.getNameField().sendKeys("Hello");
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
		driver.hideKeyboard();
		formpage.femaleOption.click();
		//driver.findElement(By.xpath("//*[@text='Female']")).click();
		formpage.getCountrySelection().click();
		//driver.findElement(By.id("android:id/text1")).click();
         Utilities u=new Utilities(driver);
         u.scrollToText("Argentina");
        //fill country in form by scrolling
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
		//   driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))"));     

		driver.findElement(By.xpath("//*[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		//add two product one by one to cart by clicking Add to cart
		//Trick: here get(0) indicate that at a time only one active "add to cart" text available
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
        //open cart to vierw added items
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        //Trick: here thread is given as driver may confuse with same id available in pag 2 and page 3
		Thread.sleep(4000);
        //get count of product added in cart-product --price id is same for all so indexing require
		int count=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
		double sum=0;
        CheckoutPage check=new CheckoutPage(driver);
		for(int i=0;i<count;i++)
		{
			//get the product price in iterative manner and store as string
			String amount1= check.productList.get(i).getText();
			//call getAmount method for the conversion of sting to double
			double amount=getAmount(amount1);
			//take the sum of product price of all the items added in cart
			sum=sum+amount;//280.97+116.97
		}

		System.out.println(sum+"sum of products");
		//Get the total amount value by default calculated by site
		String total=check.totalAmount.getText();        
		total= total.substring(1);
		double totalValue=Double.parseDouble(total);
		System.out.println(totalValue+"Total value of products");
		Assert.assertEquals(sum, totalValue); 
		service.stop();

		//Mobile Gestures
/*
		WebElement checkbox=driver.findElement(By.className("android.widget.CheckBox"));
		TouchAction t=new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkbox))).perform();

		WebElement tc=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
		t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		*/
	}
		@BeforeTest
public void killAllNodes() throws IOException, InterruptedException
{
	Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	Thread.sleep(3000);
}
		
	
    //String to double converter
	public static double getAmount(String value)
	{
		value= value.substring(1);
		double amount2value=Double.parseDouble(value);
		return amount2value;
	}
}



