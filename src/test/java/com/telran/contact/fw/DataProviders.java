package com.telran.contact.fw;

import com.telran.contact.models.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    // создаем метод DataProvider
    @DataProvider
    public Iterator<Object[]> newContact() {
        // создаем список объектов и добавляем туда те объекты, кот необходимы
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Petr", "Petrov", "88996677", "email22@mail.com", "Adresse 1", "description 1"});
        list.add(new Object[]{"Sergey", "Ivanov", "00445566", "email55@mail.com", "Adresse 1", "description 1"});
        list.add(new Object[]{"Alex", "Smirnov", "5566999", "email44@mail.com", "Adresse 1", "description 1"});
        list.add(new Object[]{"Andrey", "Smirnov", "777777", "email77@mail.com", "Adresse 1", "description 1"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> newContactFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/Contacts.csv")));
        String line = reader.readLine();// читаем строку

        while (line != null) {
            String[] split = line.split(","); // делим на части
            list.add(new Object[]{new Contact().setName(split[0]).setSurName(split[1]).setPhone(split[2]).setEmail(split[3]).setAddress(split[4]).setDescription(split[5])});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
