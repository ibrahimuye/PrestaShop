package com.prestashop.tests.smoke_tests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class AccountInfo {
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
    public void login (){
//        1. Go	to	http://automationpractice.com/index.php
        driver.get("http://automationpractice.com/index.php");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        2. locate and  Click	Sign in link
        WebElement signIn = driver.findElement(By.className("login"));
        signIn.click();
        WebElement emailCreateAcc = driver.findElement(By.name("email_create"));
        Faker faker=new Faker();
        String email = faker.internet().emailAddress();
        emailCreateAcc.sendKeys(email);
//        create account
        WebElement submitCreateButton = driver.findElement(By.name("SubmitCreate"));
        submitCreateButton.click();
//        we are in the create account page
//        locate title button and click Mr.
        WebElement title = driver.findElement(By.name("id_gender"));
        title.click();
//        locate and fill first name
        WebElement firstNameBox = driver.findElement(By.id("customer_firstname"));
        String firstName = faker.name().firstName();
        firstNameBox.sendKeys(firstName);
//        locate and fill last name
        WebElement lastNameBox = driver.findElement(By.id("customer_lastname"));
        String lastName = faker.name().lastName();
        lastNameBox.sendKeys(lastName);
//        locate password box and fill
        WebElement passwordBox = driver.findElement(By.id("passwd"));
        String password = faker.internet().password(5,8);
        passwordBox.sendKeys(password);
//        locate and fill day of Birth
        WebElement dayOfBirthBox = driver.findElement(By.id("days"));
        Select select = new Select(dayOfBirthBox);
        int day = faker.number().numberBetween(1,31);
        select.selectByIndex(day);
//        locate month of Birthday
        WebElement monthOfBirthdayBox = driver.findElement(By.id("months"));
        Select selectmonth = new Select(monthOfBirthdayBox);
        int  month = faker.number().numberBetween(1,12);
        selectmonth.selectByIndex(month);
//        locate year of Birthday
        WebElement yearBox = driver.findElement(By.id("years"));
        Select selectYear = new Select(yearBox);
        int year = faker.number().numberBetween(1,119);
        selectYear.selectByIndex(year);
//        sign up for newsletter
        WebElement newsletterBox = driver.findElement(By.id("newsletter"));
        newsletterBox.click();
//        special offers click
        WebElement offers = driver.findElement(By.id("optin"));
        offers.click();
//        address line street
        WebElement streetBox = driver.findElement(By.id("address1"));
        String street = faker.address().streetAddress();
        streetBox.sendKeys(street);
//        locate and fill city
        WebElement cityBox = driver.findElement(By.id("city"));
        String city = faker.address().city();
        cityBox.sendKeys(city);
//        locate state box and fill
        WebElement stateBox = driver.findElement(By.id("id_state"));
        Select selectState = new Select(stateBox);
        int options = selectState.getOptions().size();
        int state = faker.number().numberBetween(1,options);
        selectState.selectByIndex(state);
//          locate and fill zip code
        WebElement zipCodeBox = driver.findElement(By.id("postcode"));
        int zipCode1 = faker.number().numberBetween(10000,99999);
        String zipCode = ""+zipCode1;
        zipCodeBox.sendKeys((zipCode));

//        locate and fill mobile phone number
        WebElement phoneBox = driver.findElement(By.id("phone_mobile"));
        String phoneNumber = faker.phoneNumber().cellPhone().toString();
        phoneBox.sendKeys(phoneNumber);
//        alias addressbox
        WebElement aliasBox = driver.findElement(By.id("alias"));
        String alias = faker.address().streetAddress();
        aliasBox.sendKeys(alias);
//        submit Register button locate and click
        WebElement registerButton = driver.findElement(By.id("submitAccount"));
        registerButton.click();
//        4. Verify	that	title contains My	account
        Assert.assertTrue(driver.getTitle().contains("My account"),"page title must contain 'My account'");
//        5. Verify	that	account	holder	full	name	is	displayed	next	to	the	Sign	out	link
        WebElement nameNextToSignOutBox = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span"));
        String nameNextToSignOut = nameNextToSignOutBox.getText();
        Assert.assertTrue(nameNextToSignOut.equalsIgnoreCase(firstName+" "+lastName));
//        6. Click	on	My	personal	information button
        WebElement MypersonalButton = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[4]/a/span"));
        MypersonalButton.click();
//      7. Verify	title	contains	Identity
        Assert.assertTrue(driver.getTitle().contains("Identity"));
//        8. Verify	that first	name	and	last	name	matches	the	full	name	on	top
        WebElement firstNameInPersonalInfo= driver.findElement(By.id("firstname"));
        WebElement lastNameInPersonalInfo=driver.findElement(By.id("lastname"));
//        System.out.println(firstNameInPersonalInfo.getAttribute("value"));
        Assert.assertEquals(firstNameInPersonalInfo.getAttribute("value")+" "+lastNameInPersonalInfo.getAttribute("value"),(nameNextToSignOut),"name and last name do not match those on the top");
//        9. Click	on	Save	button
        WebElement saveButton = driver.findElement(By.name("submitIdentity"));
        saveButton.click();
//        10. Verify	error	message	“The	password	you	entered	is	incorrect.”
        WebElement errorMessage = driver.findElement(By.cssSelector("#center_column > div > div > ol > li"));
        String actualError = errorMessage.getText();
        Assert.assertEquals("The password you entered is incorrect.",actualError,"wrong error message");
//        11. Click	on Back	to	your	account
        WebElement backToYourAcc= driver.findElement(By.xpath("(//a[@class='btn btn-default button button-small']) [2]"));
        backToYourAcc.click();
//        12. Verify	that	title contains My	account
        Assert.assertTrue(driver.getTitle().contains("My account"),"the title is wrong, you might be ina wrong page");
//      13. Click on	My	addresses
        WebElement myAddressesBox = driver.findElement(By.cssSelector("a[title='Addresses']"));
        myAddressesBox.click();
//      14. Click on	Add a	new	add  Add an address
        WebElement addNewAddressBox = driver.findElement(By.cssSelector("a[title='Add an address']"));
        addNewAddressBox.click();
//        15. Verify	that first	name	and	last	name	matches	the	full	name	on
        WebElement firstNameInAddress= driver.findElement(By.id("firstname"));
        WebElement lastNameInaddress=driver.findElement(By.id("lastname"));
        String fullnameAddress = firstNameInAddress.getAttribute("value")+" "+lastNameInaddress.getAttribute("value");
//        System.out.println(lastNameInaddress.getAttribute("value"));
//        System.out.println(fullnameAddress);
        WebElement nameNextToSignOutAddress = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span"));
        String nameNextToSOAddress= nameNextToSignOutAddress.getText();
        Assert.assertEquals(nameNextToSOAddress,fullnameAddress, "wrong name please check");
//      16. Delete	the	first	name
        firstNameInAddress.clear();
//        17. Click	save
        WebElement saveaddressButton = driver.findElement(By.name("submitAddress"));
        saveaddressButton.click();
//        18. Verify	error	message	“firstname	is	required.”
        WebElement allErrorMessages = driver.findElement(By.cssSelector("#center_column > div > div"));
//        System.out.println(allErrorMessages.getText());
        Assert.assertTrue(allErrorMessages.getText().contains("firstname is required"), "error messages do not include 'firstname is required'");
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
