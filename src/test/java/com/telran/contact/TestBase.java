package com.telran.contact;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.List;

public class TestBase {
    //спец родительский класс, от которого все другие классы будут наследовать общие методы
    static WebDriver driver;

    @BeforeSuite
    public void setUp() {
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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

    public boolean isLoginTabPresent() {
        return isElementPresent2(By.xpath("//a[contains(.,'LOGIN')]"));
    }

    public boolean isSignOutTabPresent() {
        return isElementPresent2(By.xpath("//button[contains(.,'Sign Out')]"));
    }

    public boolean isLoginRegistrationFormPresent() {
        return isElementPresent(By.cssSelector("div.login_login__3EHKB"));
    }

    public void loginUser(String email, String password) {
        // выполняет login пользователя с email и password
        driver.findElement(By.cssSelector("[href='/login']")).click();

        driver.findElement(By.cssSelector("[placeholder='Email']")).click();
        driver.findElement(By.cssSelector("[placeholder='Email']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Email']")).sendKeys(email);

        driver.findElement(By.cssSelector("[placeholder='Password']")).click();
        driver.findElement(By.cssSelector("[placeholder='Password']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(., 'Login')]")).click();
    }

    public void addContactExample() {
        //добавляет тестовый контакт в список контактов зарегистрированного пользователя
        click(By.cssSelector("a:nth-child(5)"));

        type(By.cssSelector("[placeholder='Name']"), "Tanya");
        type(By.cssSelector("input:nth-child(2)"), "Marinina");
        type(By.cssSelector("input:nth-child(3)"), "654321");
        type(By.cssSelector("input:nth-child(4)"), "exampl@gmail.com");
        type(By.cssSelector("input:nth-child(5)"), "City");
        type(By.cssSelector("input:nth-child(6)"), "Description");

        click(By.cssSelector(".add_form__2rsm2 button"));
        System.out.println("***** Создан Tanya Marinina 654321 ******");
    }

    public void removeContactExample() {
        // удаляет тестовый контакт в списке
        if (!isContactListEmpty()) {
            // клик на карточке тестового контракта
            driver.findElement(By.xpath("//div[@class='contact-item_card__2SOIM']/h3[contains(.,'654321')]")).click();
            driver.findElement(By.xpath("//button[text()='Remove']")).click();
            System.out.println("***** Удален Tanya Marinina 654321 ******");

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

    public void clickOnSignOutButton() {
        click(By.xpath(("//button[contains(.,'Sign Out')]")));
    }

    public void clickOnLoginTab() {
        click(By.xpath("//a[contains(.,'LOGIN')]"));
    }

    public void createNewAccount(User user) {
        // создает аккаунт, атрибут - объект user
        type(By.cssSelector("[placeholder='Email']"), user.getEmail());
        type(By.cssSelector("[placeholder='Password']"), user.getPassword());
        click(By.xpath("//button[contains(., 'Registration')]"));
    }

    public void login(User user) {
        // вход в систему, атрибут - объект user
        type(By.cssSelector("[placeholder='Email']"), user.getEmail());
        type(By.cssSelector("[placeholder='Password']"), user.getPassword());
        click(By.xpath("//button[contains(., 'Login')]"));
    }

    public void addNewContact(String name, String surName, String phone, String email, String address, String description) {
        // кликнуть на Add
        click(By.cssSelector("a:nth-child(5)"));
        pause(1000);
        // заполнить поля
        type(By.cssSelector("[placeholder='Name']"), name);
        type(By.cssSelector("input:nth-child(2)"), surName);
        type(By.cssSelector("input:nth-child(3)"), phone);
//        type(By.cssSelector("input:nth-child(3)"), "123-456");
        type(By.cssSelector("input:nth-child(4)"), email);
        type(By.cssSelector("input:nth-child(5)"), address);
        type(By.cssSelector("input:nth-child(6)"), description);
        // если элемент не активен - 2й способ
//        clickWithAction(By.cssSelector(".add_form__2rsm2 button"));
        // кликнуть на Save
        click(By.cssSelector(".add_form__2rsm2 button"));
        System.out.println("########## Add New Contact ###########");
    }

    public void removeContact(String phoneNum) {
        // удаляет контракт с заданным номером телефона
        click(By.cssSelector("[href='/contacts']"));
        String locator = "//div[@class='contact-item_card__2SOIM']/h3[contains(.,'" + phoneNum + "')]";
        WebElement el = driver.findElement(By.xpath(locator));
        if (el != null) {
            el.click();
            click(By.xpath("//div[@class='contact-item-detailed_card__50dTS']/button[contains(.,'Remove')]"));
            System.out.println("#########  Remove Contact  ########");
        }
    }

    public boolean isContactCreated(String text) {
        //проверяет наличие контакта по номеру телефона
        List<WebElement> contacts = driver.findElements(By.xpath("//h3"));
        for (WebElement el : contacts) {
            if (el.getText().contains(text))
                return true;
        }
        return false;
    }



    public boolean isContactListEmpty() {
        // проверяет, пустой ли список контактов
        return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).isEmpty();
    }

    public int sizeOfContacts() {
        // возвращает количество контактов в списке пользователя
        if (!isContactListEmpty()) {
            return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        } else
            return 0;
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }


}
