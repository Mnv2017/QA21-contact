package com.telran.contact.fw;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver driver;

    UserHelper user; //объявили переменную для связи с хелпером
    ContactHelper contact; // --"--"--
    HomeHelper home;

    public void init() {
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app");
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //для селениума 4.0.0
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        user = new UserHelper(driver); // инициализировали ее
        contact = new ContactHelper(driver); // --"--"--
        home = new HomeHelper(driver);
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
