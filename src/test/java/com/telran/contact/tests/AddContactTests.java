package com.telran.contact.tests;

import com.telran.contact.fw.DataProviders;
import com.telran.contact.models.Contact;
import com.telran.contact.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @Test(dataProvider = "newContact", dataProviderClass = DataProviders.class)
    public void addContactPositiveFromDataProviderTest(String name, String surname, String phone, String email, String addr, String des) throws InterruptedException {

        app.getContact().addNewContact(new Contact()
                .setName(name)
                .setSurName(surname)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(addr)
                .setDescription(des));
        Thread.sleep(1000);
//        app.getContact().removeContact();
    }

    @Test(dataProvider = "newContactFromCSV", dataProviderClass = DataProviders.class)

    public void addContactPositiveFromCSVTest(Contact contact) throws InterruptedException {
        app.getContact().addNewContact(contact);
        Thread.sleep(1000);
//        app.getContact().removeContact();

    }


}
