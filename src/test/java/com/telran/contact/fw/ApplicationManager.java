package com.telran.contact.fw;

import com.telran.contact.tests.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

//    WebDriver driver;

    EventFiringWebDriver driver;

    UserHelper user; //объявили переменную для связи с хелпером
    ContactHelper contact; // --"--"--
    HomeHelper home;

    public static class MyListener extends AbstractWebDriverEventListener {
        Logger logger = LoggerFactory.getLogger(TestBase.class);

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
//            super.beforeFindBy(by, element, driver);
            logger.info("Start search " + by);//инф о том, какой локатор ищем
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
//            super.afterFindBy(by, element, driver);
            logger.info(by+" found.");// инфо какой локатор найден
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
//            super.onException(throwable, driver);
            logger.error(throwable.toString());// строка описания исключения  - почему локатор не был найден
        }
    }

    public void init() {
        driver = new EventFiringWebDriver(new ChromeDriver());
//        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app");
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //для селениума 4.0.0
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        user = new UserHelper(driver); // инициализировали ее
        contact = new ContactHelper(driver); // --"--"--
        home = new HomeHelper(driver);

        driver.register(new MyListener()); // метод отслеживает все действия и может их регистрировать
    }

    public UserHelper getUser() {
        return user;
    }

    public ContactHelper getContact() {
        return contact;
    }

    public HomeHelper getHome() {
        return home;
    }

    public void stop() {
        driver.quit();
    }
}
