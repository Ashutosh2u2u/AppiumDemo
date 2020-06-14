package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Preferences {
	
	public Preferences(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
@AndroidFindBy(xpath="//android.widget.TextView[@text='3. Preference dependencies']")
public WebElement Dependencies;

@AndroidFindBy(className="android.widget.Button")
public List<WebElement> buttons;


//driver.findElementsByClassName("android.widget.Button").get(1).click();//select 1st class out of available

}

