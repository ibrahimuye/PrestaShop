package com.prestashop.utilities;

import com.prestashop.pages.*;

public class Pages {
    private LoginPage loginPage;
    private HomePage homePage;
    private MyAccountPage myAccountPage;
    private OrderPage orderPage;
    private ProductPage productPage;
    private MyStorePage index;

    public LoginPage login() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public MyAccountPage myAccountPage() {
        if (myAccountPage == null) {
            myAccountPage = new MyAccountPage();
        }
        return  myAccountPage;
    }

    public OrderPage order() {
        if (orderPage == null) {
            orderPage = new OrderPage();
        }
        return orderPage;
    }

    public ProductPage productPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }
    public MyStorePage index() {
        if (index == null) {
            index = new MyStorePage();
        }
        return index;
    }
}
