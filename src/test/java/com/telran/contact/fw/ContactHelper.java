package com.telran.contact.fw;

import com.telran.contact.models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver driver) {
        super(driver);
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

    public void addNewContact(Contact contact) {
        // кликнуть на Add
        click(By.cssSelector("a:nth-child(5)"));
        pause(1000);
        // заполнить поля
        type(By.cssSelector("[placeholder='Name']"), contact.getName());
        type(By.cssSelector("input:nth-child(2)"), contact.getSurName());
        type(By.cssSelector("input:nth-child(3)"), contact.getPhone());
//        type(By.cssSelector("input:nth-child(3)"), "123-456");
        type(By.cssSelector("input:nth-child(4)"), contact.getEmail());
        type(By.cssSelector("input:nth-child(5)"), contact.getAddress());
        type(By.cssSelector("input:nth-child(6)"), contact.getDescription());
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
}
