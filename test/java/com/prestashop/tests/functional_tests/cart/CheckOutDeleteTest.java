package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.MyStorePage;
import com.prestashop.pages.OrderPage;
import com.prestashop.pages.ProductPage;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.prestashop.utilities.BrowserUtils.open;

public class CheckOutDeleteTest extends TestBase {

    @Test
    public void CheckOutdeleteTest() throws InterruptedException {
        extentLogger = report.createTest("CheckOutdeleteTest");
        extentLogger.info(" logging in now");
        open ();
        extentLogger.info("clicking on one of the products");
        pages.index().blouseLink.click();
        extentLogger.info("adding the product in the cart");
        pages.productPage().addToCartButton.click();
        pages.productPage().continueShopping.click();
        extentLogger.info("going to home page");
        pages.productPage().home.click();
        extentLogger.info("clicking on another product");
        pages.index().printedDress.click();
        pages.productPage().addToCartButton.click();
        extentLogger.info("adding the product in the cart");
        pages.productPage().proceedToCheckOutButton.click();
        //        System.out.println(orderpage.cartConetentText.getText());
        String expected = "Your shopping cart contains: 2 Products";
        extentLogger.info("verifying there are 2 products in the cart");
        Assert.assertEquals(pages.order().cartConetentText.getText(),expected,"error in cart content");
        extentLogger.info("deleting one of  the products in the cart");
        pages.order().trashIcon.click();
//        Thread.sleep(5000); // sartsiz bekleme
        String expected2 = "Your shopping cart contains: 1 Product";
//        System.out.println(expected2);
//        System.out.println(orderpage.cartConetentText.getText());
//        System.out.println(orderpage.numberOfProductInCart.getText());
        wait.until(ExpectedConditions.textToBe(By.className("heading-counter"),"Your shopping cart contains: 1 Product"));
        extentLogger.info("verifying there is one product in the cart");
        Assert.assertEquals(pages.order().cartConetentText.getText(),expected2,"error in cart content");
        extentLogger.info("deleting the other product in the cart");
        pages.order().trashIcon.click();
        String expected3 = "Your shopping cart is empty.";
        extentLogger.info("verifying there is no product in the cart");
        Assert.assertEquals(pages.order().cartEmptyText.getAttribute("textContent"),expected3,"error in cart content");
//        delete 2nd item
        Thread.sleep(5000);

//        wait.until(ExpectedConditions.elementToBeClickable(orderpage.trashIcon));
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("OrderPage")));
//        actions.moveToElement(orderpage.trashIcon).perform();

        pages.order().trashIcon.click();
//        String actualEmptyText = orderpage.cartEmptyText.getText();
        String actualEmptyText = pages.order().cartEmptyText.getAttribute("textContent");
        String expectedEmptyText = "Your shopping cart is empty.";
        Assert.assertTrue(actualEmptyText.equals(expectedEmptyText));
        extentLogger.pass("end of Check Out Delete test");
    }
}
