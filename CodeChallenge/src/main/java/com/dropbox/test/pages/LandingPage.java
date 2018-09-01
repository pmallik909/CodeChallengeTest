package com.dropbox.test.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dropbox.test.base.BaseClass;
import com.dropbox.test.utils.TestUtils;

public class LandingPage extends BaseClass{
	
	@FindBy(xpath = "//a[contains(text(),'Sign in')]")
	WebElement signInLink;
	
	@FindBy(name = "login_email")
	WebElement loginEmailInputField;

	@FindBy(name = "login_password")
	WebElement loginPasswordInputField;
	
	@FindBy(xpath = "//div[@class='clearfix']/button")
	WebElement signInButton;
	
	
	
	public LandingPage() {
		PageFactory.initElements(driver, this);
	}

	//Login To the Application
	public LandingPage login(String email, String password) {
		
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(loginEmailInputField));
		loginEmailInputField.sendKeys(email);
		loginPasswordInputField.sendKeys(password);
		signInButton.click();
		
		return new LandingPage();
	}
	
	public String getPageTitle(){
		
		return driver.getTitle();
		
		
		
	}
	
}
