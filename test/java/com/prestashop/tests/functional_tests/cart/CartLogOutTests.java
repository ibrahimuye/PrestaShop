package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.MyStorePage;
import com.prestashop.pages.LoginPage;
import com.prestashop.pages.MyAccountPage;
import com.prestashop.pages.ProductPage;
import com.prestashop.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.prestashop.pages.ProductPage.cartLogo;
import static com.prestashop.pages.ProductPage.productNameinCart;
import static com.prestashop.pages.ProductPage.viewCard;
import static com.prestashop.utilities.BrowserUtils.open;

public class CartLogOutTests extends TestBase {
    /*
    1.Open browser
    2.Go to http://automationpractice.com/index.php
    3.Login as any valid user
    4.Go back to home page
    5.Add any product in the homepage to the cart
    6.Hover over the cart icon
    7.Verify that cart contains the product
    8.Log out
    9.Verify word empty is displayed in the Cart icon
    */

    @Test
    public void logOut () {
        //      task   1-2
        extentLogger = report.createTest("Log Out Test");
        extentLogger.info(" logging in now");
        open ();
//      task   3
        extentLogger.info("signing in");
        pages.index().signInButton.click();
        String emailAddress = faker.internet().emailAddress();
        pages.login().emailBox.sendKeys(emailAddress);
        pages.login().createAccountButton.click();
//        creating account details
        String firstName = faker.name().firstName();
        pages.login().firstNameBox.sendKeys(firstName);
//        to choose random gender
        int genderNumber = faker.number().numberBetween(1,2);
        pages.login().gender(genderNumber).click();
        pages.login().lastNameBox.sendKeys(faker.name().lastName());
        pages.login().passwordBox.sendKeys(faker.internet().password(5,8));
        pages.login().streetBox.sendKeys(faker.address().streetAddress());
        pages.login().cityBox.sendKeys(faker.address().city());
        pages.login().zipBox.sendKeys(""+faker.number().numberBetween(10000,99999));
        pages.login().mobilePhNumber.sendKeys(faker.phoneNumber().cellPhone());
        pages.login().aliasBox.sendKeys(faker.address().streetAddress());
        int options =  pages.login().chooseState( pages.login().stateBox).getOptions().size();
        pages.login().chooseState( pages.login().stateBox).selectByIndex(faker.number().numberBetween(1,options));
        pages.login().registerButton.click();
//          4.Go back to home page
        extentLogger.info("go back to the home page");
        pages.myAccountPage().home.click();
//        tasks 5-6-7
        extentLogger.info("clicking on one of the products");
        pages.index().blouseLink.click();
        extentLogger.info("adding the product in the cart");
        pages.productPage().addToCartButton.click();
        pages.productPage().crossButton.click();
//        move on the cart icon
        actions.moveToElement(cartLogo);
        String expected = "Blouse";
        String actual = productNameinCart.getAttribute("title");
        extentLogger.info("Verifying that cart contains the product");
        Assert.assertEquals(actual,expected,"please check if the item is in the cart");
//         8.Log out
        extentLogger.info("Logging Out");
        pages.productPage().signOutButton.click();
        String expectedEmpty = "empty";
        String actualEmpty = viewCard.getText();
        System.out.println(actualEmpty);
        extentLogger.info("Verifying the word empty is displayed in the Cart icon");
        Assert.assertTrue(actualEmpty.contains(expectedEmpty),"please check if the item is in the cart it must be empty");
        extentLogger.pass("end of Log Out test");
    }
}
