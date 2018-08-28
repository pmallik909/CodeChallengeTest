package com.dropbox.test.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dropbox.test.base.BaseClass;

public class LandingPage extends BaseClass{
	
	@FindBy(linkText = "Sign in")
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

	@Test
	@Parameters({"email","password"})
	public LandingPage login(String email, String password) {
		
		signInLink.click();
		loginEmailInputField.sendKeys(email);
		loginPasswordInputField.sendKeys(password);
		signInButton.click();
		
		return new LandingPage();
	}
}
