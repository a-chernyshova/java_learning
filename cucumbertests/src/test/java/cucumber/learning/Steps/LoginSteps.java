package cucumber.learning.Steps;

import com.codeborne.selenide.Condition;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.learning.Pages.AbstractPage;
import cucumber.learning.Pages.HomePage;
import cucumber.learning.Pages.LoginPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class LoginSteps extends AbstractPage{

    @Given("^I navigate to yandex home page$")
    public void openHomePage() throws Throwable {
        open(HomePage.MAIN_URL);
        System.out.println("Given step done");
    }

    @When("^enter username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void enterUsernameAndPassword(String username, String password) throws Throwable {
        $(LoginPage.LOGIN_INPUT_LOCATOR).sendKeys(username + Keys.TAB + password + Keys.ENTER);
        System.out.println("Inputed log: " + username + ", and pass: " + password + "\n");
    }

    @And("^click submit button$")
    public void clickSubmitButton() throws Throwable {
        $(".auth__button domik3__auth-button").waitUntil(Condition.visible, 4000).click();
        System.out.println("Submit login form");
    }

    @Then("^I should see mail logo$")
    public void findMailLogo() throws Throwable {
        $$(".mail-App-Header-Item").shouldHaveSize(1);
    }

    @Given("^enter username as nassy\\.kusaka and password as blabla(\\d+)$")
    public void enterIncorrectCredentials(int arg1) throws Throwable {
        throw new PendingException();
    }

    @Then("^I should see error message$")
    public void seeErrorMessage() throws Throwable {
        assert $(".passport-Domik-Form-Error_active").isDisplayed();
    }
}
