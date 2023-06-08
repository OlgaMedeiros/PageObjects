package ru.netology;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.DataHelper;
import ru.netology.PageObjects.LoginPage1;
import ru.netology.PageObjects.VerificationPage;

import java.time.Duration;


public class PageObjectsTest {
    @BeforeEach
    void setup() {
        open("http://localhost:7777");
    }

    @Test
    @DisplayName("Should successfully login with active registered user")
        //метод без Page Objects
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        $("[data-test-id = login] input").setValue("vasya");
        $("[data-test-id = password] input").setValue("qwerty123");
        $(".button").click();

        //попадаем во второе окно для подтверждения пароля через смс
        $("[data-test-id = code] input").setValue("12345");
        $("[data-test-id = action-verify]").click();

        //попадаем в окно со списком карт и нажимаем пополнить
        $("[data-test-id = action-deposit]").click();

        //переходим на страницу перевода со счета
        $("[data-test-id = amount] input").setValue("200");
        $("[data-test-id = from] input").setValue("5559 0000 0000 0001");
        $("[data-test-id = action-transfer").click();










//    @Test
//    @DisplayName("Should successfully login with Page Objects")
//        // Запакуем предыдущий код в Page Objects
//    void shouldSuccessfulLoginPage1() {
//        var loginPage = new LoginPage1();
//        var authInfo  = DataHelper.getAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    }
}



