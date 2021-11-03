package com.telran.contact.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class DeleteContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.getUser().isSignOutTabPresent()) {
            app.getUser().loginUser("mmm@mail.ru", "Mm$123456");
        }
        app.getContact().addContactExample();
        Thread.sleep(1000);
    }

    @Test
    public void deleteContactPositiveTest() throws InterruptedException {
        app.getContact().removeContactExample();
        Thread.sleep(1000);
//        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(2));
//        w.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='contact-item_card__2SOIM']/h3[contains(.,'654321')]")));
        Assert.assertFalse(app.getContact().isElementPresent2(By.xpath("//div[@class='contact-item_card__2SOIM']/h3[contains(.,'654321')]")));

    }

}
