package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSelector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sun.tools.jar.resources.jar;

import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardTest {


    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Елена");
        form.$("[data-test-id=phone] input").setValue("+79340000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $(".Success_successBlock__2L3Cw").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldPass() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Алла-Мария");
        form.$("[data-test-id=phone] input").setValue("+79868758413");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $(".Success_successBlock__2L3Cw").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotPass() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Алла1");
        form.$("[data-test-id=phone] input").setValue("+79868758413");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[class=input__sub]").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

}