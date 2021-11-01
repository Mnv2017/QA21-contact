package com.telran.contact.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HomeHelper extends HelperBase {

    public HomeHelper(WebDriver driver) {
        super(driver);
    }

    public boolean isHomeComponentPresent() {
        //проверяет существовование элемента Home Component на странице
        return driver.findElements(By.cssSelector("div:nth-child(2) > div > div")).size() > 0;
    }

    public boolean isHomeComponentPresent2() {
        //то же самое с обработкой исключения
        try {
            driver.findElement(By.cssSelector("div:nth-child(2) > div > div"));
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }
}
