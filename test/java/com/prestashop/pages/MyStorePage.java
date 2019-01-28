package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyStorePage {

    public MyStorePage() { PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy (linkText = "Blouse")
    public WebElement blouseLink;

    @FindBy (className = "login")
    public WebElement signInButton;

    @FindBy (linkText = "Printed Dress")
    public WebElement printedDress;



}
