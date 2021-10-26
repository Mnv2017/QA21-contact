package com.telran.contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HeaderTabsLoggedUserTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!isElementPresent(By.xpath("//a[contains(.,'LOGIN')]"))) {
            driver.findElement(By.xpath(("//button[contains(.,'Sign Out')]"))).click();
        }
    }

    @Test(enabled = false)
    public void checkTabLogin() {
        driver.findElement(By.cssSelector("[href='/login']")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("div.login_login__3EHKB")));
    }

    @Test
    public void checkTabContacts() {
        loginUser("mmm@mail.ru", "Mm$123456");
        Assert.assertTrue(isElementPresent(By.cssSelector("div.contact-page_message__2qafk")));
    }

    @Test
    public void checkTabAdd() {
        loginUser("mmm@mail.ru", "Mm$123456");
        driver.findElement(By.cssSelector("[href='/add']")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("[class='add_form__2rsm2']")));
    }
}
