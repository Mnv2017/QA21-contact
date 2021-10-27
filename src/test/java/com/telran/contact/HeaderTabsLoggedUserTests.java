package com.telran.contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HeaderTabsLoggedUserTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!isLoginTabPresent()) {
            click(By.xpath(("//button[contains(.,'Sign Out')]")));
        }
    }

    @Test
    public void checkTabLogin() {
        click(By.cssSelector("[href='/login']"));
        Assert.assertTrue(isLoginRegistrationFormPresent());
    }

    @Test
    public void checkTabContacts() {
        loginUser("mmm@mail.ru", "Mm$123456");
        click(By.cssSelector("[href='/contacts']"));
        Assert.assertTrue(isElementPresent(By.cssSelector("div.contact-page_message__2qafk")));
    }

    @Test
    public void checkTabAdd() {
        loginUser("mmm@mail.ru", "Mm$123456");
        click(By.cssSelector("[href='/add']"));
        Assert.assertTrue(isElementPresent(By.cssSelector("[class='add_form__2rsm2']")));
    }
}

