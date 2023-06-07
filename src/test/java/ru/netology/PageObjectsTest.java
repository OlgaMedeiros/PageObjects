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
        $("[data-test-id = login] input").setValue("Vasya");
        $("[data-test-id = password] input").setValue("qwerty123");
        $(".button").click();
    }

    @Test
    @DisplayName("Should successfully login with Page Objects")
        // Запакуем предыдущий код в Page Objects
    void shouldSuccessfulLoginPage1() {
        var loginPage = new LoginPage1();
        var authInfo  = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);


    }
}
