package com.telran.contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HeaderTabsUnloggedUserTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        // если нет кнопки Логин, нужно сделать Логаут
        if (!isLoginTabPresent()) {
            click(By.xpath(("//button[contains(.,'Sign Out')]")));
        }
    }

    @Test
    public void checkTabHome() {
        click(By.cssSelector("[href='/home']"));
        Assert.assertTrue(isElementPresent(By.cssSelector("div:nth-child(2) >div >div")));
    }

    @Test
    public void checkTabAbout() {
//        driver.findElement(By.cssSelector("a[class='active']")).click();
        click(By.cssSelector("[href='/about']"));
//        driver.findElement(By.xpath("//a[contains(.,'ABOUT')]")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("div.about_main__2Uv5W")));
    }

    @Test
    public void checkTabLogin() {
        click(By.cssSelector("[href='/login']"));
        Assert.assertTrue(isLoginRegistrationFormPresent());
    }


    // [href='/home']     div:nth-child(2) >div >div
    // [class='active']    div.about_main__2Uv5W
    // a[class='active'] ??
    // [href='/login']    div.login_login__3EHKB
}
