package application;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class SendingMsg {
	AndroidDriver  driver;
	@SuppressWarnings("deprecation")
	public void textMsg(String Number, String Msg ) throws MalformedURLException, InterruptedException
	{
		File app= new File("D:\\ApiDemos-debug.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "HUAWEI Y7a");
		capabilities.setCapability("platformVersion", "10");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.android.mms");
		capabilities.setCapability("appActivity", "com.android.mms.ui.ComposeMessageActivity");
		capabilities.setCapability("app", app.getAbsolutePath());
		driver = new AndroidDriver(new URL("http://192.168.1.7:4723/wd/hub"), capabilities);
		
		driver.findElement(By.id("recipients_editor")).sendKeys(Number);
		driver.findElement(By.id("embedded_text_editor")).sendKeys(Msg);
		driver.findElement(By.id("send_button_sms")).click();
		Thread.sleep(5000);
		
		
		driver.quit();
		
		
		
		
	}

}

