package com.prestashop.tests.smoke_tests;
import com.google.common.base.Verify;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.*;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static javax.swing.text.html.CSS.getAttribute;

public class ProductInfo {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void priceTest() {
//   1. Go	to	http://automationpractice.com/index.php
        driver.get("http://automationpractice.com/index.php");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//   2         get the price of a ProductPage in the main page
        WebElement priceBoxMain = driver.findElement(By.xpath("(//div[@class='content_price'])[6]"));
        String mainPagePrice = priceBoxMain.getText(); /// price of ProductPage in the main page
//    3    get the name of a ProductPage in the main page
        WebElement nameBoxMain = driver.findElement(By.xpath("(//*[@class='ProductPage-name'])[4]"));
        String productNameMain = nameBoxMain.getText(); // name of ProductPage in the main page
//    4. Click	on	any	ProductPage
        WebElement product = driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[3]/div/div[1]/div/a[1]/img"));
//    WebElement ProductPage  = driver.findElement(By.xpath("//*[.='More']"));  did not work
        Actions waitPlease = new Actions(driver);
        waitPlease.moveToElement(product).build().perform();
        driver.findElement(By.linkText("More")).click();
//   5 get the price in the detail page
        WebElement priceBox = driver.findElement(By.id("our_price_display"));
        String detailPagePrice = priceBox.getText(); // price of ProductPage in the detail page
//   6     get the name of the ProductPage in detail page
        WebElement nameBox = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/div[3]/h1"));
        String detailPageName = nameBox.getText(); // name of ProductPage in detail page
//        compare two ProductPage prices
        Assert.assertEquals(detailPagePrice, mainPagePrice, "ProductPage prices are different please check");
//        compare the names on different pages
        Assert.assertEquals(detailPageName, productNameMain, "names of the ProductPage are different please check");
    }


    @Test
    public void detailsTest() {
//               1. Go	to	http://automationpractice.com/index.php
        driver.get("http://automationpractice.com/index.php");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//       2. Click	on	any	ProductPage
        WebElement product2 = driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[3]/div/div[1]/div/a[1]/img"));
        Actions waitPlease2 = new Actions(driver);
        waitPlease2.moveToElement(product2).build().perform();
        driver.findElement(By.linkText("More")).click();
//     we are in the details page
//        locate the quantity box and read the text quantity and verify if it is 1
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement quantityBox = driver.findElement(By.cssSelector("#quantity_wanted"));
        String defaultQuantity = quantityBox.getAttribute("value");
        Assert.assertEquals(defaultQuantity, "1", "please chech the default quantity, it must be 1");
//  locate the size drop down menu and check if default S is selected
        WebElement selectSize = driver.findElement(By.id("group_1"));
        Select select = new Select(selectSize);
        System.out.println(select.getFirstSelectedOption().getText());  // print what is selected
        String sizeSelected = select.getFirstSelectedOption().getText();

//    5.    compare if it is S
        Assert.assertEquals("S",sizeSelected,"default selected size must be S, please check");
//  6. other size options
//        create an array list to store S,M,L as web elements
        ArrayList<WebElement> sizes = new ArrayList<WebElement>();
        sizes.addAll(select.getOptions() );
        String sizeAll = "";
        for (WebElement ss:sizes) {
            sizeAll=sizeAll+ss.getText();
        }
        System.out.println(sizeAll);
        Assert.assertEquals("SML",sizeAll,"size options are SML please check ");
    }

    @Test
    public void addToCart() {
//               1. Go	to	http://automationpractice.com/index.php
        driver.get("http://automationpractice.com/index.php");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//       2. Click	on	any	ProductPage
        WebElement product2 = driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[3]/div/div[1]/div/a[1]/img"));
        Actions waitPlease2 = new Actions(driver);
        waitPlease2.moveToElement(product2).build().perform();
        driver.findElement(By.linkText("More")).click();
//     we are in the details page
//     7.  locate the add to cart button
        WebElement addButton = driver.findElement(By.name("Submit"));
        addButton.click();
//        8. verfy confirmation message  can not locate work on this
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement message=driver.findElement(By.xpath("(//h2)[1]"));
//        String str=message.getText(); does not work
        String str2=message.getAttribute("textContent");
//        String str3=message.getAttribute("placeholder"); does not work
//        String str4=message.getAttribute("InnerText"); does not work
//        System.out.println(str+"1111");
//        System.out.println(str2+"2");
//        System.out.println(str3+"3");
//        System.out.println(str4+"4");
        Assert.assertEquals(str2.trim(),"ProductPage successfully added to your shopping cart", "wrong message when adding items in the cart");
////        9. Verfy quantity in the cart is 1
        WebElement quantityCartBox= driver.findElement(By.xpath("//div[@id='layer_cart']/div[1]/div[1]/div[2]/div[1]/span"));
        String quantityCart= quantityCartBox.getAttribute("textContent");
        String quantityCart2= quantityCartBox.getAttribute("innerText");
        System.out.println(quantityCartBox.getText());
        System.out.println(quantityCart);
        System.out.println(quantityCart2);
        System.out.println(quantityCartBox.getAttribute("placeholder"));
        System.out.println(quantityCartBox.getAttribute("value"));
        String quantityCart3= quantityCartBox.getAttribute("outerText");
//        Assert.assertEquals(quantityCart,"1","default quantity is wrong please check");oes not work
        WebElement sizeText = driver.findElement(By.id("layer_cart_product_attributes"));
        System.out.println(sizeText.getText());
        System.out.println(sizeText.getAttribute("textContent"));
        System.out.println(sizeText.getAttribute("innerText"));
        System.out.println(sizeText.getAttribute("placeholder"));
        System.out.println(sizeText.getAttribute("outerText"));



    }



    @AfterMethod
    public  void afterMethod(){
//        driver.quit();
    }
    @AfterClass
    public void afterClass (){
        System.out.println("end of test, thank you");

    }

}
