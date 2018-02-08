package learning.page_object.pages;

import org.openqa.selenium.By;

public class Menu extends AbstractPage{
    public static final By INBOX_LOCATOR = By.linkText("Inbox");
    public static final By SENT_LOCATOR = By.linkText("Sent");
    public static final By DRAFT_LOCATOR = By.cssSelector(".ns-view-folders a:last-child");
    public static final By CREATE_NEW_LETTER_LOCATOR = By.cssSelector(".mail-ComposeButton");
    public static final By ACCOUNT_MENU_LOCATOR = By.cssSelector(".mail-User-Name");
    public static final By LOGOUT_LOCATOR = By.linkText("Log out");

    public By getCreateButtonLocator(){
        return CREATE_NEW_LETTER_LOCATOR;
    }
    public NewLetter openNewMailForm(){
        waitForElementEnabled(CREATE_NEW_LETTER_LOCATOR);
        driver.findElement(CREATE_NEW_LETTER_LOCATOR).click();
        return new NewLetter();
    }
    public LettersContainer openDraftsFolder(){
        driver.findElement(DRAFT_LOCATOR).click();
        return new LettersContainer();
    }
    public LettersContainer openInboxFolder(){
        driver.findElement(INBOX_LOCATOR).click();
        return new LettersContainer();
    }
    public LettersContainer openSentFolder(){
        driver.findElement(SENT_LOCATOR).click();
        return new LettersContainer();
    }
    public void openAccountMenu(){
        driver.findElement(ACCOUNT_MENU_LOCATOR).click();
    }
    public void logoutSubmit(){
        waitForElementEnabled(LOGOUT_LOCATOR);
        driver.findElement(LOGOUT_LOCATOR).click();
    }
    public String returnTitle(){
        return driver.getTitle();
    }
}
