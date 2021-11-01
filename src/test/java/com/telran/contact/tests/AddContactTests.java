package com.telran.contact.tests;

import com.telran.contact.models.Contact;
import com.telran.contact.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.getUser().isSignOutTabPresent()) {
            app.getUser().clickOnLoginTab();
            app.getUser().login(new User()
                    .setEmail("mmm@mail.ru")
                    .setPassword("Mm$123456"));
        }
    }

    @Test
    public void addContactPositiveTest() throws InterruptedException {
        // текущее время в сек - преобразуем в переменную
        int d = (int) ((System.currentTimeMillis()) / 1000) % 3600;
        app.getContact().addNewContact(new Contact()
                .setName("Masha")
                .setSurName("Marinina")
                .setPhone("123-456" + d)
                .setEmail("mmm" + d + "@gmail.com")
                .setAddress("Dortmund")
                .setDescription("description"));
        Assert.assertTrue(app.getContact().isContactCreated("123-456" + d));
//        removeContact("123-456" + d); //удаляем добавленный контракт

    }

}
