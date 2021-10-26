package com.telran.contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTest extends TestBase {
// предусловия
    // убедиться, что пользователь разлогинен
    // кликнуть на Логин
    // заполнить форму регистрации
    // кликнуть на кнопку Регистрация
    // проверить, что зарегились (есть кнопка выхода)

    @BeforeMethod
    public void ensurePreconditions(){
        if (!isElementPresent(By.xpath("//a[contains(.,'LOGIN')]"))){
            // если нет кнопки Логин, нужно выйти (Логаут)
            driver.findElement(By.xpath(("//button[contains(.,'Sign Out')]"))).click();
        }
    }

    @Test
    public void registrationPositiveTest(){
        driver.findElement(By.xpath("//a[contains(.,'LOGIN')]")).click();
        // форма регистрации присутствует
        Assert.assertTrue(isElementPresent(By.cssSelector("div.login_login__3EHKB")));
        // 1) кликаем на поле 2) очищаем 3) заполняем

        driver.findElement(By.cssSelector("[placeholder='Email']")).click();
        driver.findElement(By.cssSelector("[placeholder='Email']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Email']")).sendKeys("mmm@mail.ru");

        driver.findElement(By.cssSelector("[placeholder='Password']")).click();
        driver.findElement(By.cssSelector("[placeholder='Password']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Password']")).sendKeys("Mm$123456");

        driver.findElement(By.xpath("//button[contains(., 'Registration')]")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//button[contains(.,'Sign Out')]")));

        // [placeholder='Email']
        // [placeholder='Password']
        // //button[contains(., 'Registration')]
    }
}
