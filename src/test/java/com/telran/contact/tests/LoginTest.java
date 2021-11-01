package com.telran.contact.tests;

import com.telran.contact.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.getUser().isLoginTabPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test(priority = 1)
    public void loginRegisteredUserPositiveTest() {
        // click on Login tab
        app.getUser().clickOnLoginTab();
        // форма регистрации присутствует
        Assert.assertTrue(app.getUser().isLoginRegistrationFormPresent());
        app.getUser().login(new User()
                .setEmail("mmm@mail.ru")
                .setPassword("Mm$123456"));
        // Assert user logged
        Assert.assertTrue(app.getUser().isSignOutTabPresent());
    }

    @Test(priority =2)
    public void loginRegisteredUserNegativeWrongPasswortTest() {
        // click on Login tab
        app.getUser().clickOnLoginTab();
        // форма регистрации присутствует
        Assert.assertTrue(app.getUser().isLoginRegistrationFormPresent());
        // fill Login form
        app.getUser().login(new User()
                .setEmail("mmm@mail.ru")
                .setPassword("Mm123456")); // убрали спецсимвол
        // Assert user logged
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

}
