package ru.netology.PageObjects;

import ru.netology.DataHelper;
import static com.codeborne.selenide.Selenide.$;


public class LoginPage1 {
    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }
}


