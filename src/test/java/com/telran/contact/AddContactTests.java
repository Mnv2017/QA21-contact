package com.telran.contact;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void ensuerPreconditions() {
        if (!isSignOutTabPresent()) {
            click(By.xpath("//a[contains(.,'LOGIN')]"));
            type(By.cssSelector("[placeholder='Email']"), "mmm@mail.ru");
            type(By.cssSelector("[placeholder='Password']"), "Mm$123456");
            click(By.xpath("//button[contains(., 'Login')]"));
        }
    }

    @Test
    public void addContactPositiveTest() {
        // текущее время в сек - преобразуем в переменную
        int d = (int) ((System.currentTimeMillis()) / 1000) % 3600;
        // кликнуть на Add
        click(By.cssSelector("a:nth-child(5)"));
        pause(1000);
        // заполнить поля
        type(By.cssSelector("[placeholder='Name']"), "Maria");
        type(By.cssSelector("input:nth-child(2)"), "Marinina");
        type(By.cssSelector("input:nth-child(3)"), "123-456" + d);
        type(By.cssSelector("input:nth-child(4)"), "mmm" + d + "@gmail.com");
        type(By.cssSelector("input:nth-child(5)"), "Dortmund");
        type(By.cssSelector("input:nth-child(6)"), "description");

        // если элемент не активен - 2й способ
//        clickWithAction(By.cssSelector(".add_form__2rsm2 button"));

        click(By.cssSelector(".add_form__2rsm2 button"));


        // кликнуть на Save
        // Assert

    }

}
