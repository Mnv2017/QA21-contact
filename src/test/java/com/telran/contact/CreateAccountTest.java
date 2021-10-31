package com.telran.contact;

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
    public void ensurePreconditions() throws InterruptedException {
        if (!isLoginTabPresent()){
            clickOnSignOutButton();
        }
    }

    @Test
    public void registrationPositiveTest() throws InterruptedException {
        clickOnLoginTab();
        // форма регистрации присутствует
        Assert.assertTrue(isLoginRegistrationFormPresent());
        createNewAccount(new User()
                // fluent interface
                .setEmail("mmm@mail.ru")
                .setPassword("Mm$123456"));
        Thread.sleep(1000);
        Assert.assertTrue(isSignOutTabPresent());
    }

    @Test
    public void registrationNegativeWithoutPasswordTest(){
        clickOnLoginTab();
        // форма регистрации присутствует
        Assert.assertTrue(isLoginRegistrationFormPresent());
        createNewAccount(new User()
                        .setEmail("mmm@mail.ru")
                // с незаполненным паролем
                //      .setPassword("Mm$123456")
                );
        Assert.assertTrue(isAlertPresent());
    }

}
