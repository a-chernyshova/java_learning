package learning.page_factory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPF extends AbstractPagePF{
    @FindBy(linkText = "Inbox")
    private WebElement inboxFolder;

    @FindBy(linkText = "Sent")
    private WebElement sentFolder;

    @FindBy(css = ".ns-view-folders a:last-child")
    private WebElement draftFolder;

    public static final By CREATE_NEW_LETTER_LOCATOR = By.cssSelector(".mail-ComposeButton");

    @FindBy(css = ".mail-ComposeButton")
    private WebElement createNewLetter;

    @FindBy(css=".mail-User-Name")
    private WebElement account_menu;

    @FindBy(linkText = "Log out")
    private WebElement logoutButton;

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
        account_menu.click();
    }
    public MenuPF logoutSubmit(){
        waitForElementEnabled(logoutButton);
        logoutButton.click();
        return this;
    }
}