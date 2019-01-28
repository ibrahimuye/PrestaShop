package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {

    public LoginPage() { PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy (id = "email_create")
    public WebElement emailBox;

    @FindBy (id="SubmitCreate")
    public WebElement createAccountButton;

    @FindBy (id="customer_firstname")
    public WebElement firstNameBox;

    @FindBy (id="id_gender1")
    public WebElement Mr;

    @FindBy (id="id_gender2")
    public WebElement Mrs;
//  to choose random gender
    public WebElement gender (int n){
        if (n==1){return Mr;}
        else {return Mrs;}
    }

    @FindBy (id = "id_state")
    public WebElement stateBox;

    public Select chooseState (WebElement stateBox){
        Select selectstate = new Select(stateBox);
        return selectstate;
    }
                       

    @FindBy (id = "customer_lastname")
    public WebElement lastNameBox;

    @FindBy (id = "passwd")
    public WebElement passwordBox;

    @FindBy (id = "address1")
    public WebElement streetBox;

    @FindBy (id = "city")
    public WebElement cityBox;

    @FindBy (id = "postcode")
    public WebElement zipBox;

    @FindBy (id = "phone_mobile")
    public WebElement mobilePhNumber;

    @FindBy (id = "alias")
    public WebElement aliasBox;

    @FindBy (id = "submitAccount")
    public WebElement registerButton;














}
