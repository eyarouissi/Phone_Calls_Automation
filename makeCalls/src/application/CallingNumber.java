package application;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class CallingNumber {
	AndroidDriver  driver;
	@SuppressWarnings("deprecation")
	
	public void call(String Number) throws MalformedURLException
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("0", "zero");
		map.put("1", "one");
		map.put("2", "two");
		map.put("3", "three");
		map.put("4", "four");
		map.put("5", "five");
		map.put("6", "six");
		map.put("7", "seven");
		map.put("8", "eight");
		map.put("9", "nine");
		File app= new File("D:\\ApiDemos-debug.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "HUAWEI Y7a");
		capabilities.setCapability("platformVersion", "10");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.huawei.contacts");
		capabilities.setCapability("appActivity", "com.android.contacts.activities.DialtactsActivity");
		capabilities.setCapability("app", app.getAbsolutePath());
		driver = new AndroidDriver(new URL("http://192.168.1.7:4723/wd/hub"), capabilities);
		for (int i = 0; i < Number.length(); i++) {
			driver.findElement(By.id("contacts_dialpad_"+map.get(String.valueOf(Number.charAt(i)) )+"_layout")).click();
		}
		driver.findElement(By.id("nameDialButton3")).click();
		driver.quit();
		System.out.println("Success");
		
		
		
		
	}



}
