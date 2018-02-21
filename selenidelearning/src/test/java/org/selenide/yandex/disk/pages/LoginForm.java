package org.selenide.yandex.disk.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import org.openqa.selenium.By;

public class LoginForm{
    public static final SelenideElement loginInput = $(By.name("login"));
    public static final SelenideElement pwdInput = $(By.name("passwd"));
    public static final SelenideElement submitLoginButton = $(".auth__button");

    public void inputLogin(String loginName){
        loginInput.setValue(loginName);
    }
    public void inputPassword(String password){
        pwdInput.setValue(password).pressEnter();
    }
    public void submitLoginForm(){
        submitLoginButton.click();
    }
}
