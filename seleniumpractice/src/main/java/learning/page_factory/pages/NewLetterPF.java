package learning.page_factory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewLetterPF extends AbstractPagePF{
    @FindBy(css = "._nb-modal-popup button")
    public WebElement saveChanges;

    @FindBy(linkText = "Don't save")
    public WebElement discardChangesButton;

    @FindBy(linkText = "Cancel")
    public WebElement goBackButton;

    @FindBy(css = ".mail-Compose-From ._nb-action-button")
    public WebElement sendButton;

    @FindBy(className = "mail-Compose-Field-Input")
    public WebElement inputForm;

    public static final By INPUT_FORM_LOCATOR = By.className("mail-Compose-Field-Input");

    public NewLetterPF(WebDriver driver){
        super(driver);
    }
    public By getInputLocator(){
        return INPUT_FORM_LOCATOR;
    }
    public NewLetterPF fillNewLetterForm(String[] testData){
        waitForElementEnabled(inputForm);
        WebElement currentElement = driver.switchTo().activeElement();
        currentElement.sendKeys(testData[0] + Keys.TAB + testData[1] + Keys.TAB + testData[2]);
        return this;
    }
    public NewLetterPF saveDraft(){
        saveChanges.click();
        return this;
    }
    public NewLetterPF discardChanges(){
        discardChangesButton.click();
        return this;
    }
    public NewLetterPF goBackToDraft(){
        goBackButton.click();
        return this;
    }
    public SendResultPagePF sendLetter(){
        sendButton.click();
        return new SendResultPagePF(driver);
    }
}