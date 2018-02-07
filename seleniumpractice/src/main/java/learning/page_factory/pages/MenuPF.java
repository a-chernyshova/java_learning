package learning.page_factory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPF extends AbstractPagePF{
    @FindBy(linkText = "Inbox")
    public WebElement inboxFolder;

    @FindBy(linkText = "Sent")
    public WebElement sentFolder;

    @FindBy(css = ".ns-view-folders a:last-child")
    public WebElement draftFolder;

    public static final By CREATE_NEW_LETTER_LOCATOR = By.cssSelector(".mail-ComposeButton");

    @FindBy(css = ".mail-ComposeButton")
    WebElement createNewLetter;

    public static final By ACCOUNT_MENU_LOCATOR = By.cssSelector(".mail-User-Name");

    @FindBy(linkText = "Log out")
    WebElement logoutButton;


    public MenuPF(WebDriver driver){
        super(driver);
    }
    public By getCreateButtonLocator(){
        return CREATE_NEW_LETTER_LOCATOR;
    }
    public NewLetterPF openNewMailForm(){
        waitForElementEnabled(createNewLetter);
        createNewLetter.click();
        return new NewLetterPF(driver);
    }
    public LettersContainerPF openDraftsFolder(){
        draftFolder.click();
        return new LettersContainerPF(driver);
    }
    public LettersContainerPF openInboxFolder(){
        inboxFolder.click();
        return new LettersContainerPF(driver);
    }
    public LettersContainerPF openSentFolder(){
        sentFolder.click();
        return new LettersContainerPF(driver);
    }
    public void openAccountMenu(){
        driver.findElement(ACCOUNT_MENU_LOCATOR).click();
    }
    public MenuPF logoutSubmit(){
        waitForElementEnabled(logoutButton);
        logoutButton.click();
        return this;
    }
}