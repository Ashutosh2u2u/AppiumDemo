package PageObjectModel.AppiumFrwk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;


//taskkill /F /IM node.exe
public class base {
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement>  driver;


	//method for opening Appium server
	public static AppiumDriverLocalService startServer()
	{
		boolean flag= checkIfServerIsRunning(4723);
		if(!flag)
		{
			//There is a method "buildDefaultService" under "AppiumDriverLocalService" class
			service=AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}
    //method to check server state
	public static boolean checkIfServerIsRunning(int port)
	{
		boolean isSurverRunning= false;
		ServerSocket serverSocket;
		try{
			serverSocket=new ServerSocket(port);
			serverSocket.close();

		}catch(IOException e)
		{
			//if control comes here..that port is in use
			isSurverRunning = true;

		}finally{
			serverSocket = null;
		}
		return isSurverRunning;
	}
	//method to start emulator
	public static void startEmulator() throws IOException, InterruptedException
	{
		//D:\Automation\AppiumAutomation\AppiumFrwk\src\main\java\resources
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(10000);
	}

	public static  AndroidDriver<AndroidElement> capabilities(String appApk) throws IOException, InterruptedException
	{

		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\PageObjectModel\\AppiumFrwk\\global.properties\\global.properties");
		Properties prop=new Properties();
		prop.load(fis);
		//prop.get(appApk);

	
		File appDir = new File("src");
		File app = new File(appDir, (String) prop.get(appApk));//casting the app name
		//"General-Store.apk"
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//Ashuemulator  Pixel_3a_API_29_x86

		String Device=(String) prop.get("device");  //get device name from property file
		//String Device=System.getProperty("deviceName"); //get device name from cmd-maven command
		if(Device.contains("AVD"))
		{
			startEmulator();
		}
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Device);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,14);
		capabilities.setCapability("ignoreHiddenApiPolicyError", true);
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		return driver;
	}

public static void getScreenshot(String resTest) throws IOException
{
File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"Screenshots\\"+resTest+"defect.png"));
}
}
