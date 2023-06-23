package ru.netology.pageobjects;

import com.codeborne.selenide.Condition;
import ru.netology.data.DataHelper;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class TransferMoneyPage {

    private SelenideElement heading = $(withText("Пополнение карты"));
    private final SelenideElement amount = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement button = $("[data-test-id=action-transfer]");
    private final SelenideElement cancelButton = $("[data-test-id=action-cancel]");


    public TransferMoneyPage() {
        heading.shouldBe(visible);
    }

    public DashBoardPage transferForm(String sum, DataHelper.CardNumber cardNumber) {
        amount.setValue(sum);
        from.setValue(String.valueOf(cardNumber));
        button.click();
        return new DashBoardPage();

    }


    public DashBoardPage cancelButton() {
        cancelButton.click();
        return new DashBoardPage();
    }


}