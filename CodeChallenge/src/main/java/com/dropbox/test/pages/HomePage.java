package com.dropbox.test.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dropbox.test.base.BaseClass;
import com.dropbox.test.utils.TestUtils;

public class HomePage extends BaseClass{

	TestUtils testUtils = new TestUtils();
	
	@FindBy(xpath = "//div[@class='primary-action-menu__button-wrapper']/div/button")
	WebElement uploadBTN;
	
	@FindBy(xpath = "//div[@class='primary-action-menu__button-wrapper']/div/div/nav/div/button[@class='action-upload-files mc-popover-content-item']")
	WebElement fileUploadBTN;
	
	@FindBy(xpath = "//div[@class='mc-util-modal-body']//ul/li/span/div")
	WebElement dropboxUploadLink;
	
	@FindBy(xpath = "//div[@class='mc-util-modal-actions-buttons']/button[@class='mc-button mc-button-primary']/span")
	WebElement uploadDocumentButton;
	
	@FindBy(xpath = "//input[@class='search-bar__text-input']")
	WebElement searchInputBox;
	
	//@FindBy(xpath = "//p[@class='mc-snackbar-title']")
	@FindBy(xpath = "//p[contains(text(),'Uploaded')]")
	WebElement uploadText;
	
	@FindBy(xpath = "//h1[contains(text(),'Home')]")
	WebElement homePageValidation;
	
	
	//PAGE FACTORY INITIALIZATION
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public HomePage homePageElementVisbility(){
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(homePageValidation)).isDisplayed();
		Assert.assertEquals(homePageValidation.getText(), "Home");
		return new HomePage();
		
	}
	
	public HomePage uploadFile() throws AWTException, InterruptedException{
		
		WebDriverWait wait = new WebDriverWait(driver, 35);
		
		wait.until(ExpectedConditions.elementToBeClickable(uploadBTN));
		uploadBTN.click();
		//TestUtils.implicityWait();
		
		wait.until(ExpectedConditions.elementToBeClickable(fileUploadBTN));
		fileUploadBTN.click();
		Thread.sleep(3000);
		testUtils.uploadAction(System.getProperty("user.dir")+"\\uploadFile.txt");
		wait.until(ExpectedConditions.elementToBeClickable(dropboxUploadLink));		
		dropboxUploadLink.click();
		Boolean value = wait.until(ExpectedConditions.elementToBeClickable(uploadDocumentButton)).isEnabled();
		System.out.println("value is -"+value);
		uploadDocumentButton.click();	
		waitForTheUploadText();			
		
		return new HomePage();
		
	}
	
	public HomePage searchFile(){
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(searchInputBox));
		searchInputBox.sendKeys("uploadFile.txt");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		searchInputBox.sendKeys(Keys.ENTER);
		
		return new HomePage();
		
	}
	
	public void waitForTheUploadText(){
		
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.visibilityOf(uploadText));		
		String getUploadText = uploadText.getText();
		System.out.println(getUploadText);		
		boolean value = getUploadText.contains("uploadFile");
		Assert.assertTrue(value);
		//wait.until(ExpectedConditions.visibilityOf(uploadText));
		
	}
	
	
	
	
}
