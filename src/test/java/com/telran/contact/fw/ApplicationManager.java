package com.telran.contact.fw;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ApplicationManager {

    WebDriver driver;

    UserHelper user; //объявили переменную для связи с базовым хелпером
    ContactHelper contact; // --"--"--
    HomeHelper home;

    public void init() {
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

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
