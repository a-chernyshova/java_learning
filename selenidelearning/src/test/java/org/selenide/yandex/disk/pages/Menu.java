package org.selenide.yandex.disk.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.SelenideElementListIterator;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class Menu {
    public static final SelenideElement MAIL_LOGO = $(".mail-Logo-Yandex");
    public static final SelenideElement DISK_LINK = $(".mail-ui-Link-Disk");
    public static final SelenideElement MAIL_ACCOUNT_MENU_LOCATOR = $(".mail-User-Name");
    public static final SelenideElement DISK_ACCOUNT_MENU_LOCATOR = $(".avatar");
    public static final SelenideElement LOGOUT_LOCATOR = $(By.linkText("Log out"));
    public static final SelenideElement CONTACTS_LOCATOR = $(By.linkText("Contacts"));
    public static final SelenideElement DISK_LOCATOR = $(By.linkText("Disk"));
    public static final SelenideElement CALENDAR_LOCATOR = $(By.linkText("Calendar"));

    public static void logOut(){
        DISK_ACCOUNT_MENU_LOCATOR.click();
        LOGOUT_LOCATOR.waitUntil(Condition.enabled, 4000).click();
    }
    public static void openAccSettings(){
        MAIL_ACCOUNT_MENU_LOCATOR.click();
        $(By.partialLinkText("passport")).waitUntil(Condition.enabled, 5000).click();
    }
}
