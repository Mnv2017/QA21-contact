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
        if (!isLoginTabPresent()){
            // если нет кнопки Логин, нужно выйти (Логаут)
            click(By.xpath(("//button[contains(.,'Sign Out')]")));
        }
    }

    @Test
    public void registrationPositiveTest(){
        click(By.xpath("//a[contains(.,'LOGIN')]"));
        // форма регистрации присутствует
        Assert.assertTrue(isLoginRegistrationFormPresent());
        // 1) кликаем на поле 2) очищаем 3) заполняем

        type(By.cssSelector("[placeholder='Email']"), "mmm@mail.ru");

        type(By.cssSelector("[placeholder='Password']"), "Mm$123456");

        click(By.xpath("//button[contains(., 'Registration')]"));
        Assert.assertTrue(isSignOutTabPresent());

        // [placeholder='Email']
        // [placeholder='Password']
        // //button[contains(., 'Registration')]
    }

}
