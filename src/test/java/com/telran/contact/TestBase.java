package com.telran.contact;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    //спец родительский класс, от которого все другие классы будут наследовать общие методы
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public boolean isHomeComponentPresent() { //проверяет существовование элемента на странице
        return driver.findElements(By.cssSelector("div:nth-child(2) > div > div")).size() > 0;
    }

    public boolean isElementPresent(By locator) { // универсальный: локатор = стратегия + сам локатор
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

    public void loginUser(String email, String password) {
        driver.findElement(By.cssSelector("[href='/login']")).click();

        driver.findElement(By.cssSelector("[placeholder='Email']")).click();
        driver.findElement(By.cssSelector("[placeholder='Email']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Email']")).sendKeys(email);

        driver.findElement(By.cssSelector("[placeholder='Password']")).click();
        driver.findElement(By.cssSelector("[placeholder='Password']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(., 'Login')]")).click();
    }

    public void addContactExample(){
        click(By.cssSelector("a:nth-child(5)"));

        type(By.cssSelector("[placeholder='Name']"), "Name");
        type(By.cssSelector("input:nth-child(2)"), "Lastname");
        type(By.cssSelector("input:nth-child(3)"), "123456");
        type(By.cssSelector("input:nth-child(4)"), "exampl@gmail.com");
        type(By.cssSelector("input:nth-child(5)"), "City");
        type(By.cssSelector("input:nth-child(6)"), "Description");

        click(By.cssSelector(".add_form__2rsm2 button"));

    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        driver.quit();
    }

    public void click(By locator){
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public boolean isLoginTabPresent() {
        return isElementPresent(By.xpath("//a[contains(.,'LOGIN')]"));
    }

    public boolean isSignOutTabPresent() {
        return isElementPresent(By.xpath("//button[contains(.,'Sign Out')]"));
    }

    public boolean isLoginRegistrationFormPresent() {
        return isElementPresent(By.cssSelector("div.login_login__3EHKB"));
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.alertIsPresent());
        if (alert== null) {
            return false;
        } else {
            // перенаправляет на всплывающее окно
            driver.switchTo().alert();
            // нажимаем на Ок
            alert.accept();
            return true;
        }
    }

    public void clickWithAction(By save) {
        // если элемент не активен, перейти к нему - 2й способ
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(save);
        action.moveToElement(element).build().perform();
        element.click();
    }

    public void jump() {
        //перейти в конец страницы (тк кнопка Save там) - этот метод не сработал
        driver.findElement(By.cssSelector(".add_form__2rsm2 button")).sendKeys(Keys.CONTROL, Keys.END);
    }

    public void pause(int millis){
        // ожидание в милисекундах
        new WebDriverWait(driver,Duration.ofSeconds(millis));
    }

}
