package com.telran.contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!isLoginTabPresent()) {
            clickOnSignOutButton();
        }
    }

    @Test(priority = 1)
    public void loginRegisteredUserPositiveTest() {
        // click on Login tab
        clickOnLoginTab();
        // форма регистрации присутствует
        Assert.assertTrue(isLoginRegistrationFormPresent());
        login(new User()
                .setEmail("mmm@mail.ru")
                .setPassword("Mm$123456"));
        // Assert user logged
        Assert.assertTrue(isSignOutTabPresent());
    }

    @Test(priority =2)
    public void loginRegisteredUserNegativeWrongPasswortTest() {
        // click on Login tab
        clickOnLoginTab();
        // форма регистрации присутствует
        Assert.assertTrue(isLoginRegistrationFormPresent());
        // fill Login form
        login(new User()
                .setEmail("mmm@mail.ru")
                .setPassword("Mm123456")); // убрали спецсимвол
        // Assert user logged
        Assert.assertTrue(isAlertPresent());
    }

}
