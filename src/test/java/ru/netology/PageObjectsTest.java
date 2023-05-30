package ru.netology;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class PageObjectsTest {
    @BeforeEach
    void setup() {
        open("http://localhost:7777");
    }

    @Test

}
