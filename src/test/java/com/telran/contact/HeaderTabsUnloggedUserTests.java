package com.telran.contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HeaderTabsUnloggedUserTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!isLoginTabPresent()) {
            clickOnSignOutButton();
        }
    }

    @Test
    public void checkTabHome() {
        click(By.cssSelector("[href='/home']"));
        Assert.assertTrue(isElementPresent(By.cssSelector("div:nth-child(2) >div >div")));
    }

    @Test
    public void checkTabAbout() {
        click(By.cssSelector("[href='/about']"));
        Assert.assertTrue(isElementPresent(By.cssSelector("div.about_main__2Uv5W")));
    }

    @Test
    public void checkTabLogin() {
        click(By.cssSelector("[href='/login']"));
        Assert.assertTrue(isLoginRegistrationFormPresent());
    }

}
