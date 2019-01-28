package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

    public ProductPage() { PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy (id="add_to_cart")
    public WebElement addToCartButton;

    @FindBy (className = "cross")
    public WebElement crossButton;
//      it is common and visible from all pages - that is why it is static
    @FindBy (xpath = "//span [@class='ajax_cart_no_product']")
    public static  WebElement cartLogo;
//  it is common and visible from all pages - that is why it is static
    @FindBy (xpath = "//a[@class='cart_block_product_name']")
    public static WebElement productNameinCart;

    @FindBy (className = "login")
    public WebElement signInButton;

    @FindBy (className = "logout")
    public WebElement signOutButton;

    @FindBy (xpath = "//a[@title='View my shopping cart']")
    public static WebElement viewCard;

    @FindBy (xpath = "//span[@class='continue btn btn-default button exclusive-medium']")
    public WebElement continueShopping;

    @FindBy (xpath = "//a[@class='ajax_cart_block_remove_link']")
    public WebElement cartProdDelete;

//    @FindBy (xpath = "//span[@class='remove_link']")
//    public WebElement cartProdDelete;

    @FindBy (className = "home")
    public WebElement home;

    @FindBy (xpath = "//a[@class='btn btn-default button button-medium']")
    public WebElement proceedToCheckOutButton;


}
