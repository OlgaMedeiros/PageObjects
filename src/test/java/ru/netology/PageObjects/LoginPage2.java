package ru.netology.PageObjects;

import ru.netology.DataHelper;
import static com.codeborne.selenide.Selenide.$;


public class LoginPage2 {
    public VerificationPage passwordСonfirmation(DataHelper.VerificationCode verificationCode) {
        $("[data-test-id = code] input").setValue(verificationCode.getCode());
        $("[data-test-id = action-verify]").click();
        return new VerificationPage();
    }
}




