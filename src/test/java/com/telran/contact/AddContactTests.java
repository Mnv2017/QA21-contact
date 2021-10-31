package com.telran.contact;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!isSignOutTabPresent()) {
            clickOnLoginTab();
            login(new User()
                    .setEmail("mmm@mail.ru")
                    .setPassword("Mm$123456"));
        }
    }

    @Test
    public void addContactPositiveTest() throws InterruptedException {
        // текущее время в сек - преобразуем в переменную
        int d = (int) ((System.currentTimeMillis()) / 1000) % 3600;
        addNewContact("Masha", "Marinina", "123-456" + d, "mmm" + d + "@gmail.com", "Dortmund", "description");
        Assert.assertTrue(isContactCreated("123-456" + d));
//        removeContact("123-456" + d); //удаляем добавленный контракт

    }

}
