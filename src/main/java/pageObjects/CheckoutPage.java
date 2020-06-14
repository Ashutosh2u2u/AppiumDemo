package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutPage {
	
	public CheckoutPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
public List<WebElement> productList;

//String total=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();

@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
public WebElement totalAmount;
}