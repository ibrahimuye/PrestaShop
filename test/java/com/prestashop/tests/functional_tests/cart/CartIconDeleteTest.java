package com.prestashop.tests.functional_tests.cart;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.prestashop.pages.ProductPage.viewCard;
import static com.prestashop.utilities.BrowserUtils.open;

public class CartIconDeleteTest extends TestBase {


    @Test
    public void IconDeleteTest () {
        extentLogger = report.createTest("Icon Delete Test");
        extentLogger.info(" logging in now");
        open ();
        extentLogger.info("clicking on one of the products");
        pages.index().blouseLink.click();
        extentLogger.info("adding the product in the cart");
        pages.productPage().addToCartButton.click();
        extentLogger.info("clicking on continue shopping");
        pages.productPage().continueShopping.click();
//                move on the cart icon
        extentLogger.info("deleting the product in the cart");
        actions.moveToElement(viewCard).perform();
        pages.productPage().cartProdDelete.click();
        String expectedEmpty = "empty";
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        wait.until(ExpectedConditions.textMatches(By.xpath("//a[@title='View my shopping cart']"), Pattern.compile(expectedEmpty)));
        String actualEmpty = viewCard.getText();
//        System.out.println(actualEmpty);
        extentLogger.info("verifying that the cart is empty");
        Assert.assertTrue(actualEmpty.contains(expectedEmpty),"please check if the item is in the cart it must be empty");
        extentLogger.pass("end of Icon Delete Test");


    }
}
