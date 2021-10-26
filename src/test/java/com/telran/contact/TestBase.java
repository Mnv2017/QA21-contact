package com.telran.contact;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    //спец родительский класс, от которого все другие классы будут наследовать общие методы
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public boolean isHomeComponentPresent() { //проверяет существовование элемента на странице
        return driver.findElements(By.cssSelector("div:nth-child(2) > div > div")).size() > 0;
    }

    public boolean isElementPresent(By locator) { // универсальный - локатор = стратегия + сам локатор
        return driver.findElements(locator).size() > 0;
    }

    public boolean isHomeComponentPresent2() { //то же самое с исключением
        try {
            driver.findElement(By.cssSelector("div:nth-child(2) > div > div"));
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public boolean isElementPresent2(By locator) { //универсальный - то же самое с исключением
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        driver.quit();
    }

}
