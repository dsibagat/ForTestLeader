package tests;

import config.ConfigHelper;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Feature("Booking page content")
@Tag("web")
public class BookingPageTest extends TestBase {

    @Test
    @DisplayName("Найти комнады для отдыха")
    void findRoomsForRent() {
        //Открыть страницу сайта
        open("https://www.booking.com");

        //Выбрать место отдыха
        $("#ss").val("london ");
        $(byText("Central London")).click();

        //Выбрать даты для отдыха
        $("[data-date='2021-04-07']").click();
        $("[data-date='2021-04-14']").click();

        //Выбрать 2 взрослых и 1 ребенка
        $("#xp__guests__toggle").click();
        $("[aria-label='Increase number of Children']").click();

        //Нажать подтвердить
        $(".sb-searchbox__button ").click();
    }

    @Test
    @DisplayName("Авторизация на букинг через гугл аккаунт")
    void authorisationTest() {
        step("Open main page", () -> open("https://account.booking.com/sign-in"));

        step("Fill google auth form", () -> {
            $("[data-ga-label=google]").click();
            switchTo().window(1);
            googleAuthPage.login(ConfigHelper.getGoogleUsername(),
                    ConfigHelper.getGooglePassword());
        });

        step("Verify successful authorization", () ->
                $(byText("Where to next, ForBook?")).shouldBe(visible));
    }

}

