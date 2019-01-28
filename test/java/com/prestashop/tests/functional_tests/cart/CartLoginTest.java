package com.prestashop.tests.functional_tests.cart;
import com.prestashop.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.prestashop.pages.ProductPage.cartLogo;
import static com.prestashop.pages.ProductPage.productNameinCart;
import static com.prestashop.utilities.BrowserUtils.open;

public class CartLoginTest extends TestBase {


    @Test
    public void loginTest (){
        extentLogger = report.createTest("Login Test");
        extentLogger.info(" logging in now");
        open ();
        extentLogger.info("clicking on one of the products");
        pages.index().blouseLink.click();
        extentLogger.info("adding the product in the cart");
        pages.productPage().addToCartButton.click();
        pages.productPage().crossButton.click();
//        move on the cart icon
        actions.moveToElement(cartLogo);
        String expected = "Blouse";
        String actual = productNameinCart.getAttribute("title");
//        System.out.println(productPage.productNameinCart.getAttribute("title"));
//        5.Verify that cart contains the product
        extentLogger.info("verifying that the product in the cart (step 5)");
        Assert.assertEquals(actual,expected,"please check if the item is in the cart");
//        sig in
        extentLogger.info("signing in");
        pages.productPage().signInButton.click();
        String emailAddress = faker.internet().emailAddress();
        pages.login().emailBox.sendKeys(emailAddress);
        extentLogger.info("creating account");
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
//        faker.finance().creditCard();
//        faker.number().numberBetween(500,999);
//        faker.number().digits(16);
//        faker.address().streetAddress();



        int options =  pages.login().chooseState( pages.login().stateBox).getOptions().size();
        pages.login().chooseState( pages.login().stateBox).selectByIndex(faker.number().numberBetween(1,options));
        pages.login().registerButton.click();
        actions.moveToElement(cartLogo);
        //        Verify that cart information is same as step 5
        extentLogger.info("verifying the cart informationis same as at step 5");
        Assert.assertEquals(actual,expected,"please check if the item is in the cart");
        extentLogger.pass("end of Login test");
    }
}
