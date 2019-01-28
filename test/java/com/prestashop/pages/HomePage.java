package com.prestashop.pages;

import com.prestashop.utilities.ConfigurationReader;
import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public HomePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(id = "ctl00_MainContent_username")
    public WebElement username;
    @FindBy(id = "ctl00_MainContent_password")
    public WebElement password;
    @FindBy(id = "ctl00_MainContent_login_button")
    public WebElement loginButton;
    @FindBy(id = "ctl00_MainContent_status")
    public WebElement errorMessage;
    public void login(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        loginButton.click();
    }
    public void open(){
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }
}
