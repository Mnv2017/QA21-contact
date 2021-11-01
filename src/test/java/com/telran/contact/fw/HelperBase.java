package com.telran.contact.fw;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperBase {

    //спец родительский класс, от которого все другие классы будут наследовать общие методы
    WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By locator) {
        //проверяет наличие элементов с locator на странице
        //  locator = стратегия + сам локатор
        return driver.findElements(locator).size() > 0;
    }

    public boolean isElementPresent2(By locator) {
        // то же самое с обработкой исключения
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        //вводим в элемент с locator строку text
        if (text != null) { // для пустой строки
            click(locator);
            driver.findElement(locator).clear(); //очищаем поле
            driver.findElement(locator).sendKeys(text);
        }
    }

    public boolean isAlertPresent() {
        //проверяет появление окна сообщения
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            // перенаправляет на всплывающее окно
            driver.switchTo().alert();
            // нажимаем на Ок
            alert.accept();
            return true;
        }
    }

    public void jump() {
        //как перейти в конец страницы, чтобы отобразить/сделать активной кнопку Save - этот метод не сработал
        driver.findElement(By.cssSelector(".add_form__2rsm2 button")).sendKeys(Keys.CONTROL, Keys.END);
    }

    public void clickWithAction(By save) {
        // как перейти к элементу, если он не активен на странице - 2й способ
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(save);
        action.moveToElement(element).build().perform();
        element.click();
    }

    public void pause(int millis) {
        // ожидание в милисекундах - пока не работает
        new WebDriverWait(driver, Duration.ofSeconds(millis));
    }
}
