package com.prestashop.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    protected WebDriver driver;
    protected SoftAssert softAssert;
    protected Actions actions;
    protected Faker faker;
    protected Random random;
    protected WebDriverWait wait;


    protected Pages pages;
    protected static ExtentReports report;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentTest extentLogger;

    @BeforeMethod
    public void setupMethod() {
        driver = Driver.getDriver();
        pages = new Pages();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("url"));
        actions = new Actions(driver);
        faker= new Faker();
        random= new Random();
        softAssert= new SoftAssert();
        wait=new WebDriverWait(Driver.getDriver(),10);
    }

    @AfterMethod
    // if any test fails it can detect and take the screen shot and add to the report
    public void tearDownMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotLocation = BrowserUtils.getScreenshot(result.getName());
            extentLogger.fail(result.getName());
            extentLogger.addScreenCaptureFromPath(screenshotLocation);
            extentLogger.fail(result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {
            extentLogger.skip("Test Case Skipped: " + result.getName());
        }
        Driver.closeDriver();
    }

    @BeforeTest
    public void setUpTest() {
        //this is our custom location of the report that wiil be generated
        //report will be generated in the current project inside folder :test-output
        //report file name:report.html
        report = new ExtentReports();
        String filePath = System.getProperty("user.dir") + "/test-output/report.html";
        //initialise the htmlReporter with to our report
        htmlReporter = new ExtentHtmlReporter(filePath);
        //we attach the htmlreport to our report
        report.attachReporter(htmlReporter);

        report.setSystemInfo("Environment", "Staging");
        report.setSystemInfo("Browser", ConfigurationReader.getProperty("browser"));
        report.setSystemInfo("OS", System.getProperty("os.name"));
        report.setSystemInfo("QA Enginer","Ibrahim UYE");
        htmlReporter.config().setDocumentTitle("Prestashop Reports");
        htmlReporter.config().setReportName("Prestashop Automated Test Reports");

        htmlReporter.config().setTheme(Theme.DARK);

    }

    @AfterTest
    public void tearDownTest() {
        report.flush();
    }
}
