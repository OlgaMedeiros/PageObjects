package ru.netology;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.PageObjects.LoginPage1;
import ru.netology.PageObjects.DashBoardPage;
import ru.netology.PageObjects.TransferMoneyPage;

public class PageObjectsTest {
    @BeforeEach
    public void openPage() {

        open("http://localhost:7777");

        val loginPage = new LoginPage1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }


    @Test
    void shouldTransferMoneyFromCards1() {

        val dashboardPage = new DashBoardPage();

        int balanceFirstCard = dashboardPage.getFirstCardBalance();
        int balanceSecondCard = dashboardPage.getSecondCardBalance();
        val transferMoneyPage = dashboardPage.firstCardButton();
        val infoCard = DataHelper.getSecondCardNumber();
        String sum = "100";
        transferMoneyPage.transferForm(sum, infoCard);

        assertEquals(balanceFirstCard + Integer.parseInt(sum), dashboardPage.getFirstCardBalance());
        assertEquals(balanceSecondCard - Integer.parseInt(sum), dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyFromCards2() {

        val dashboardPage = new DashBoardPage();

        int balanceFirstCard = dashboardPage.getFirstCardBalance();
        int balanceSecondCard = dashboardPage.getSecondCardBalance();
        val transferMoneyPage = dashboardPage.secondCardButton();
        val infoCard = DataHelper.getFirstCardNumber();
        String sum = "1500";
        transferMoneyPage.transferForm(sum, infoCard);

        assertEquals(balanceFirstCard - Integer.parseInt(sum), dashboardPage.getFirstCardBalance());
        assertEquals(balanceSecondCard + Integer.parseInt(sum), dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldCancellationOfMoneyTransfer() {

        val dashboardPage = new DashBoardPage();

        val transferMoneyPage = dashboardPage.firstCardButton();
        transferMoneyPage.cancelButton();
    }

    @Test
    void shouldTransferMoneyCardsError() {

        var dashboardPage = new DashBoardPage();

        var transferMoneyPage = dashboardPage.secondCardButton();
        var infoCard = DataHelper.getFirstCardNumber();
        String sum = "50000";
        transferMoneyPage.transferForm(sum, infoCard);

    }

}
