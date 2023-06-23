package ru.netology.test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DataHelper;
import ru.netology.pageobjects.LoginPage1;
import ru.netology.pageobjects.DashBoardPage;
import ru.netology.pageobjects.TransferMoneyPage;

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
    void shouldTransferMoneyFromCards2() {

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
    void shouldTransferMoneyFromCards1() {

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
    void shouldTransferMoneyFromCards1OverLimit() {

        val dashboardPage = new DashBoardPage();

        int balanceFirstCard = dashboardPage.getFirstCardBalance();
        int balanceSecondCard = dashboardPage.getSecondCardBalance();
        val transferMoneyPage = dashboardPage.secondCardButton();
        val infoCard = DataHelper.getFirstCardNumber();
        String sum = "50000";
        transferMoneyPage.transferForm(sum, infoCard);

        assertEquals(balanceFirstCard - Integer.parseInt(sum), dashboardPage.getFirstCardBalance());
        assertEquals(balanceSecondCard + Integer.parseInt(sum), dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyFromCards2OverLimit() {

        val dashboardPage = new DashBoardPage();

        int balanceFirstCard = dashboardPage.getFirstCardBalance();
        int balanceSecondCard = dashboardPage.getSecondCardBalance();
        val transferMoneyPage = dashboardPage.secondCardButton();
        val infoCard = DataHelper.getFirstCardNumber();
        String sum = "60000";
        transferMoneyPage.transferForm(sum, infoCard);

        assertEquals(balanceFirstCard - Integer.parseInt(sum), dashboardPage.getFirstCardBalance());
        assertEquals(balanceSecondCard + Integer.parseInt(sum), dashboardPage.getSecondCardBalance());
    }

}

