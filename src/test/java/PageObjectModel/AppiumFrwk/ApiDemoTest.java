package PageObjectModel.AppiumFrwk;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.HomePage;
import pageObjects.Preferences;

public class ApiDemoTest extends base 
{
	@Test(dataProvider="InputData",dataProviderClass=TestData.class)//to fatch data from the data provider define in testdata class
	public void apiDemoTest(String input) throws IOException, InterruptedException {
		service=startServer();
		AndroidDriver<AndroidElement> driver=capabilities("ApiDemosApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//xpath id className, androidUIautomator
		/*    xpath Syntax
		 *    //tagName[@attribute='value']

		 * 
		 */
		HomePage h=new HomePage(driver);
		h.Preferences.click();
		//driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();//click on preference option
		Preferences p=new Preferences(driver);
		p.Dependencies.click();
		//driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();//select pref dependencies option
		driver.findElementById("android:id/checkbox").click();
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		driver.findElementByClassName("android.widget.EditText").sendKeys(input);//get data from provider
		p.buttons.get(1).click();
		//driver.findElementsByClassName("android.widget.Button").get(1).click();//select 1st class out of available
		service.stop();
	}
}