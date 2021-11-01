package com.telran.contact.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.getUser().isSignOutTabPresent()) {
            app.getUser().loginUser("mmm@mail.ru", "Mm$123456");
        }
        app.getContact().addContactExample();
    }

    @Test
    public void removeContactTest() throws InterruptedException {
        // проверяет количество контактов до и после удаления
        Thread.sleep(1000);
        int sizeBefore = app.getContact().sizeOfContacts();
        System.out.println(sizeBefore);
        app.getContact().removeContactExample();
        Thread.sleep(1000);
        int sizeAfter = app.getContact().sizeOfContacts();
        System.out.println(sizeAfter);
        Assert.assertEquals(sizeBefore, sizeAfter + 1);
    }

}
