package ru.netology;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.PageObjects.LoginPage1;
import ru.netology.PageObjects.LoginPage2;
import ru.netology.PageObjects.DashBoardPage;


public class PageObjectsTest {
    @BeforeEach
    public void openPage() {

        open( "http://localhost:7777" );

    }
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
    }

    @Test
    @DisplayName("Should successfully login with active registered user Page Objects")
        // Запакуем предыдущий код в Page Objects
    void shouldSuccessfulLoginPage1() {
        var loginPage = new LoginPage1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    }

    @Test

        //подтвердим через смс код
    void shouldSuccessfulVerificationCodePage2() {
        var loginPage = new LoginPage2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    }

    @Test
    void shouldTopUpBankAccount1() {

            var dashBoardPage = new DashBoardPage();

            int balanceFirstCard = dashBoardPage.getFirstCardBalance();
            int balanceSecondCard = dashBoardPage.getSecondCardBalance();
            var transferMoneyPage = dashBoardPage.firstCardButton();
            var infoCard = DataHelper.getSecondCardNumber();
            String sum = "200";
            transferMoneyPage.transferForm( sum, infoCard );

           assertEquals( balanceFirstCard + Integer.parseInt( sum ), dashBoardPage.getFirstCardBalance() );
           assertEquals( balanceSecondCard - Integer.parseInt( sum ), dashBoardPage.getSecondCardBalance() );
        }

        @Test
        void shouldTopUpBankAccount2() {

            var dashBoardPage = new DashBoardPage();

            int balanceFirstCard = dashBoardPage.getFirstCardBalance();
            int balanceSecondCard = dashBoardPage.getSecondCardBalance();
            var moneyTransfer = dashBoardPage.secondCardButton();
            var infoCard = DataHelper.getFirstCardNumber();
            String sum = "150";
            moneyTransfer.transferForm( sum, infoCard );

            assertEquals( balanceFirstCard - Integer.parseInt( sum ), dashBoardPage.getFirstCardBalance() );
            assertEquals( balanceSecondCard + Integer.parseInt( sum ), dashBoardPage.getSecondCardBalance() );
        }

        @Test
        void shouldCancellationOfMoneyTransfer() {

            var dashBoardPage = new DashBoardPage();

            var moneyTransfer = dashBoardPage.firstCardButton();
            moneyTransfer.cancelButton();
        }

        @Test
        void shouldTransferMoneyBetweenOwnCardsError() {

            var dashBoardPage = new DashBoardPage();

            var moneyTransfer = dashBoardPage.secondCardButton();
            var infoCard = DataHelper.getFirstCardNumber();
            String sum = "2000";
            moneyTransfer.transferForm( sum, infoCard );
            moneyTransfer.getError();
        }
    }