package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {
    public OrderPage () { PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy (className = "heading-counter")
    public WebElement cartConetentText;

//    @FindBy(xpath = "//table[@id='cart_summary']/tbody/tr[1]/td[7]")
//    public WebElement trashIcon;

    @FindBy(xpath = "//td[@class='cart_delete text-center']")
    public WebElement trashIcon;

    @FindBy (xpath = "//p[@class='alert alert-warning']")
    public WebElement cartEmptyText;

//    @FindBy (id = "summary_products_quantity")
//    public WebElement numberOfProductInCart;



}
