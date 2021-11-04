package com.telran.contact.fw;

import com.telran.contact.models.Contact;
import com.telran.contact.models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    // создаем методы DataProvider
    @DataProvider
    public Iterator<Object[]> newContact() {
        // создаем список массивов объектов и добавляем туда несколько контактов(= массив строк)
        List<Object[]> list = new ArrayList<>() {{
            add(new Object[]{"Petr", "Petrov", "88996677", "email22@mail.com", "Adresse 1", "description 1"});
            add(new Object[]{"Sergey", "Ivanov", "00445566", "email55@mail.com", "Adresse 1", "description 1"});
            add(new Object[]{"Alex", "Smirnov", "5566999", "email44@mail.com", "Adresse 1", "description 1"});
            add(new Object[]{"Andrey", "Smirnov", "777777", "email77@mail.com", "Adresse 1", "description 1"});
        }};
        // метод возвращает итератор списка с данными контактов
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> newContactFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/Contacts.csv")));
        String line = reader.readLine();// читаем строку из файла CSV

        while (line != null) {
            String[] split = line.split(","); // делим строку на части, отделенные запятыми
            // добавляем данные контракта в список
            list.add(new Object[]{new Contact().setName(split[0]).setSurName(split[1]).setPhone(split[2]).setEmail(split[3]).setAddress(split[4]).setDescription(split[5])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> newUserWrongPassword() {
        List<Object[]> list = new ArrayList<>() {{
            add(new Object[]{"email1@yandex.ru", "nn12345$bb"});
            add(new Object[]{"email1@yandex.ru", "NN12345$BB"});
            add(new Object[]{"email1@yandex.ru", "12345$678"});
            add(new Object[]{"email1@yandex.ru", "Nnnnnn$bb"});
            add(new Object[]{"email1@yandex.ru", "Nnn1234bb"});
        }};
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> newUserFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/UsersWrongEmail.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new User().setEmail(split[0]).setPassword(split[1])});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
