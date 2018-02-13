package learning.page_factory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// The same module for draft, inbox, sent folders
public class LettersContainerPF extends AbstractPagePF {

    public static final By LAST_MESSAGE_LOCATOR = By.cssSelector(".mail-MessagesList .ns-view-messages-item-wrap:first-child");

    @FindBy(css = ".mail-MessagesList .ns-view-messages-item-wrap:first-child")
    private WebElement lastMessage;

    @FindBy(css = ".mail-Toolbar-Item-Checkbox")
    private WebElement chooseAllMessages;

    public static final By MESSAGE_TITLE_LOCATOR = By.cssSelector(".mail-MessageSnippet-Item_subject span");

    @FindBy(css = ".mail-MessageSnippet-Item_subject span")
    private WebElement messageTitle;

    public static final By MESSAGE_ADDRESS_LOCATOR = By.cssSelector(".mail-MessageSnippet-Content .mail-MessageSnippet-FromText");
    @FindBy(css = ".mail-MessageSnippet-Content .mail-MessageSnippet-FromText")
    private WebElement messageAddress;

    public static final By CHOOSE_MESSAGE_LOCATOR = By.cssSelector("._nb-checkbox-flag");
    @FindBy(css = "._nb-checkbox-flag")
    private WebElement chooseMessage;

    @FindBy(linkText = "Forward")
    private WebElement forwardButton;

    @FindBy(className = "ns-view-messages-empty")
    private WebElement isEmpty;

    @FindBy(css = ".mail-Toolbar-Item_delete")
    private WebElement deleteButton;

    public LettersContainerPF(WebDriver driver){
        super(driver);
    }
    public By getLastMessageLocator(){
        return LAST_MESSAGE_LOCATOR;
    }
    public NewLetterPF openLastMessage(){
        waitForElementEnabled(lastMessage);
        lastMessage.click();
        return new NewLetterPF(driver);
    }
    public String getLastMessageSubject(){
        waitForElementEnabled(lastMessage);
        String title = lastMessage.findElement(MESSAGE_TITLE_LOCATOR).getAttribute("title");
        return title;
    }
    public String getLastMessageAddress(){
        waitForElementEnabled(lastMessage);
        String email = lastMessage.findElement(MESSAGE_ADDRESS_LOCATOR).getAttribute("title");
        return email;
    }
    public void chooseLastMessage(){
        lastMessage.findElement(CHOOSE_MESSAGE_LOCATOR).click();
    }
    public WebElement getIsEmptyListLocator(){
        return isEmpty;
    }
    public LettersContainerPF chooseAllMessage(){
        chooseAllMessages.click();
        return this;
    }
    public LettersContainerPF pushDelete(){
        deleteButton.click();
        return this;
    }
}