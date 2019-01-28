package com.prestashop.tests;

import com.prestashop.pages.HomePage;
import com.prestashop.utilities.ConfigurationReader;
import com.prestashop.utilities.TestBase;
import org.testng.annotations.Test;

public class LoginTestWithProperties extends TestBase {
    @Test
    public void readpropertyTest (){

        String browser = ConfigurationReader.getProperty("password");
        System.out.println(browser);

        HomePage homepage= new HomePage();
        homepage.open();

        String username = ConfigurationReader.getProperty("username");
        String password = ConfigurationReader.getProperty("password");
        homepage.login (username,password);




    }

}
