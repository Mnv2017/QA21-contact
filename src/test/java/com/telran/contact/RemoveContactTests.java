package com.telran.contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!isSignOutTabPresent()) {
            loginUser("mmm@mail.ru", "Mm$123456");
        }
        addContactExample();
    }

    @Test
    public void removeContactTest() throws InterruptedException {
        // проверяет количество контактов до и после удаления
        Thread.sleep(1000);
        int sizeBefore = sizeOfContacts();
        System.out.println(sizeBefore);
        removeContactExample();
        Thread.sleep(1000);
        int sizeAfter = sizeOfContacts();
        System.out.println(sizeAfter);
        Assert.assertEquals(sizeBefore, sizeAfter + 1);
    }

}
