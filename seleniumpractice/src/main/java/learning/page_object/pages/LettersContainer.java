package learning.page_object.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

// The same module for draft, inbox, sent folders

public class LettersContainer {
    WebDriver driver;
    public static final By LAST_MESSAGE_LOCATOR = By.cssSelector(".mail-MessagesList .ns-view-messages-item-wrap:first-child");
    public static final By CHOOSE_ALL_MESSAGE_LOCATOR = By.cssSelector(".mail-Toolbar-Item-Checkbox");
    public static final By MESSAGE_TITLE_LOCATOR = By.cssSelector(".mail-MessageSnippet-Item_subject span");
    public static final By MESSAGE_ADDRESS_LOCATOR = By.cssSelector(".mail-MessageSnippet-Content " +
            ".mail-MessageSnippet-FromText");
    public static final By CHOOSE_MESSAGE_LOCATOR = By.cssSelector("._nb-checkbox-flag");
    public static final By FORWARD_BUTTON_LOCATOR = By.linkText("Forward");
    public static final By IS_EMPTY_LIST_LOCATOR = By.className("ns-view-messages-empty");
    public static final By DELETE_BUTTON_LOCATOR = By.className("mail-Toolbar-Item_delete");

    public LettersContainer(WebDriver driver){
        this.driver = driver;
    }
    public By getLastMessageLocator(){
        return LAST_MESSAGE_LOCATOR;
    }
    public NewLetter openLastMessage(){
        driver.findElement(LAST_MESSAGE_LOCATOR).click();
        return new NewLetter(driver);
    }
    public String getLastMessageSubject(){
        String title = driver.findElement(LAST_MESSAGE_LOCATOR).findElement(MESSAGE_TITLE_LOCATOR).getAttribute("title");
        return title;
    }
    public String getLastMessageAddress(){
        String email = driver.findElement(LAST_MESSAGE_LOCATOR).findElement(MESSAGE_ADDRESS_LOCATOR).getAttribute("title");
        return email;
    }
    public void chooseLastMessage(){
        driver.findElement(LAST_MESSAGE_LOCATOR).findElement(CHOOSE_MESSAGE_LOCATOR).click();
    }
    public By getIsEmptyListLocator(){
        return IS_EMPTY_LIST_LOCATOR;
    }
    public void chooseAllMessage(){
        driver.findElement(CHOOSE_ALL_MESSAGE_LOCATOR).click();
    }
    public void pushDelete(){
        driver.findElement(DELETE_BUTTON_LOCATOR).click();
    }
}