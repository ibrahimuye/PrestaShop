package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    public MyAccountPage () { PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy (className = "home")
    public WebElement home;

}



