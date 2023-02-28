package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class CardDeliveryTest {
LocalDate currentDate = LocalDate.now();
LocalDate targetDate = currentDate.plusDays(5);
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
String dateString = targetDate.format(formatter);

    @Test
    void shouldBooking() {
        open("http://localhost:9999");
//        Configuration.holdBrowserOpen = true;
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dateString);
        $("[data-test-id='name'] input").setValue("Фамилия Имя");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(exactText("Встреча успешно забронирована на " + dateString));
    }
    @Test
    void shouldBookingWithHyphen() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dateString);
        $("[data-test-id='name'] input").setValue("Фамилия-Фамилия Имя");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(exactText("Встреча успешно забронирована на " + dateString));
    }
}
