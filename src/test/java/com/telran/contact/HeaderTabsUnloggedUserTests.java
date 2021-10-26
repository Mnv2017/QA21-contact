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
        if (!isElementPresent(By.xpath("//a[contains(.,'LOGIN')]"))) {
            driver.findElement(By.xpath(("//button[contains(.,'Sign Out')]"))).click();
        }
    }

    @Test
    public void checkTabHome() {
        driver.findElement(By.cssSelector("[href='/home']")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("div:nth-child(2) >div >div")));
    }

    @Test
    public void checkTabAbout() {
//        driver.findElement(By.cssSelector("a[class='active']")).click();
        driver.findElement(By.cssSelector("[href='/about']")).click();
//        driver.findElement(By.xpath("//a[contains(.,'ABOUT')]")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("div.about_main__2Uv5W")));
    }

    @Test
    public void checkTabLogin() {
        driver.findElement(By.cssSelector("[href='/login']")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("div.login_login__3EHKB")));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // [href='/home']     div:nth-child(2) >div >div
    // [class='active']    div.about_main__2Uv5W
    // a[class='active'] ??
    // [href='/login']    div.login_login__3EHKB
}
