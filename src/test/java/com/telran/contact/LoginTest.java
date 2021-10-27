package com.telran.contact;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!isLoginTabPresent()) {
            // если нет кнопки Логин, нужно выйти (Логаут)
            click(By.xpath(("//button[contains(.,'Sign Out')]")));
        }
    }

    @Test
    public void loginRegisteredUserPositiveTest() {
        // click on Login tab
        click(By.xpath("//a[contains(.,'LOGIN')]"));
        // форма регистрации присутствует
        Assert.assertTrue(isLoginRegistrationFormPresent());

        // fill Login form
        type(By.cssSelector("[placeholder='Email']"), "mmm@mail.ru");
        type(By.cssSelector("[placeholder='Password']"), "Mm$123456");
        // submit Login
        click(By.xpath("//button[contains(., 'Login')]"));
        // Assert user logged
        Assert.assertTrue(isSignOutTabPresent());
    }

    @Test
    public void loginRegisteredUserNegativeWrongPasswortTest() {
        // click on Login tab
        click(By.xpath("//a[contains(.,'LOGIN')]"));
        // форма регистрации присутствует
        Assert.assertTrue(isLoginRegistrationFormPresent());

        // fill Login form
        type(By.cssSelector("[placeholder='Email']"), "mmm@mail.ru");

        type(By.cssSelector("[placeholder='Password']"), "Mm123456");
        // submit Login
        click(By.xpath("//button[contains(., 'Login')]"));
        // Assert user logged
        Assert.assertTrue(isAlertPresent());
    }

}
