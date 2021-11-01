package com.telran.contact.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HeaderTabsUnloggedUserTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.getUser().isLoginTabPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test
    public void checkTabHome() {
        app.getUser().click(By.cssSelector("[href='/home']"));
        Assert.assertTrue(app.getUser().isElementPresent(By.cssSelector("div:nth-child(2) >div >div")));
    }

    @Test
    public void checkTabAbout() {
        app.getUser().click(By.cssSelector("[href='/about']"));
        Assert.assertTrue(app.getUser().isElementPresent(By.cssSelector("div.about_main__2Uv5W")));
    }

    @Test
    public void checkTabLogin() {
        app.getUser().click(By.cssSelector("[href='/login']"));
        Assert.assertTrue(app.getUser().isLoginRegistrationFormPresent());
    }

}
