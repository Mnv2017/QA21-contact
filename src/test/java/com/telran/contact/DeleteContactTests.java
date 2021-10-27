package com.telran.contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class DeleteContactTests extends TestBase {

    @BeforeMethod
    public void ensuerPreconditions() {
        if (!isSignOutTabPresent()) {
            loginUser("mmm@mail.ru", "Mm$123456");
        }
        addContactExample();
    }

    @Test
    public void deleteContactPositiveTest() {
        click(By.cssSelector("[href='/contacts']")); //кнопка Contacts
        click(By.xpath("//div[@class='contact-item_card__2SOIM']/h3[contains(.,'123456')]")); // кликнуть по тестовому контакту
        click(By.xpath("//div[@class='contact-item-detailed_card__50dTS']/button[contains(.,'Remove')]"));// кнопка Remove
//        pause(700000);
        Assert.assertTrue(!isElementPresent2(By.xpath("//div[@class='contact-item_card__2SOIM']/h3[contains(.,'123456')]")));

    }

}
