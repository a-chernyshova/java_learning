package learning.page_factory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePagePF extends AbstractPagePF{

    @FindBy(css=".search.2 .input__control")
    WebElement searchInput;

    @FindBy(css = ".search2_button")
    WebElement searchSubmitButton;

    public HomePagePF(WebDriver driver){
        super(driver);
    }
    public HomePagePF open(){
        driver.get("https://www.yandex.ru/");
        return this;
    }
    public HomePagePF fillSearchInput(String query){
        searchInput.sendKeys(query);
        return this;
    }
    public HomePagePF startSearch(){
        searchSubmitButton.click();
        return this;
    }
}
