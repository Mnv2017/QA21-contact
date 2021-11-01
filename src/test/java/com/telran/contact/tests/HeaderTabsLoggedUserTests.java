package com.telran.contact.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HeaderTabsLoggedUserTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.getUser().isLoginTabPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test(priority = 1)
    public void checkTabLogin() {
        app.getHome().click(By.cssSelector("[href='/login']"));
        Assert.assertTrue(app.getUser().isLoginRegistrationFormPresent());
    }

    @Test(priority = 2)
    public void checkTabContacts() {
        app.getUser().loginUser("mmm@mail.ru", "Mm$123456");
        app.getUser().click(By.cssSelector("[href='/contacts']"));
//        Assert.assertTrue(isElementPresent(By.cssSelector("div.contact-page_message__2qafk"))); // нет контактов
        Assert.assertTrue(app.getContact().isElementPresent(By.cssSelector("div.contact-item_card__2SOIM"))); // есть хотя бы один
    }

    @Test(priority = 3)
    public void checkTabAdd() {
        app.getUser().loginUser("mmm@mail.ru", "Mm$123456");
        app.getHome().click(By.cssSelector("[href='/add']"));
        Assert.assertTrue(app.getUser().isElementPresent(By.cssSelector("[class='add_form__2rsm2']")));
    }
}

