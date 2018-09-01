package com.dropbox.test.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.dropbox.test.utils.TestUtils;

public class BaseClass {
	
	public static WebDriver driver;
	public static Properties properties;
	
	//Base Class Constructor for loading config.properties file
	public BaseClass(){
		try {
			properties = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/dropbox/test/config/config.properties");
			properties.load(fis);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Initializing Browser Launch	
	public void initializeTest() {
		
		String browserName = properties.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/geckodriver");	
			driver = new FirefoxDriver(); 
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(properties.getProperty("url"));
		TestUtils.implicityWait();
		
		
	}
	
	//Closing the Browser after Test execution
	public void closeBrowser(){
		
		TestUtils.implicityWait();
		driver.quit();
	}

}
