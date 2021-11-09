package com.telran.contact.fw;

import com.telran.contact.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends HelperBase{

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public boolean isSignOutTabPresent() {
        return isElementPresent2(By.xpath("//button[contains(.,'Sign Out')]"));
    }

    public boolean isLoginTabPresent() {
        return isElementPresent2(By.xpath("//a[contains(.,'LOGIN')]"));
    }

    public boolean isLoginRegistrationFormPresent() {
        return isElementPresent(By.cssSelector("div.login_login__3EHKB"));
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
        click(By.xpath("//button[contains(., 'Login')]"));// ломаем локатор
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
}
