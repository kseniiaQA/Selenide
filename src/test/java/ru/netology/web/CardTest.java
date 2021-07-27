package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }


    @Test
    void shouldSubmitRequest() {

        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=name] input").setValue("Елена");
        form.$("[data-test-id=phone] input").setValue("+79540000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldPass() {

        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=name] input").setValue("Алла-Мария");
        form.$("[data-test-id=phone] input").setValue("+79868758413");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotPass() {

        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=name] input").setValue("Алла1");
        form.$("[data-test-id=phone] input").setValue("+79868758413");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $$(".input__sub").get(0).shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotPass1() {

        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=name] input").setValue("Алла");
        form.$("[data-test-id=phone] input").setValue("+7986875841");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $$(".input__sub").get(1).shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotPass2() {

        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=name] input").setValue("Алла");
        form.$("[data-test-id=phone] input").setValue("+79868758413");

        form.$("[role=button]").click();
        form.$(".input_invalid").exists();
    }
}

