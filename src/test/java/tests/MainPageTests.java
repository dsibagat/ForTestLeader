package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Feature("Main page content")
@Tag("web")
public class MainPageTests extends TestBase {
    @Test
    @DisplayName("Page should have title Honest testing")
    void titlePageTest() {
        step("Open main page", () ->
                open(""));

        step("Change language to en", () ->
                $(".lazyloaded[title=English]").click());

        step("Check that title is shown", () ->
                $(byText("Honest testing")).shouldBe(visible));
    }

    @Test
    @DisplayName("Page blocks should be loaded")
    void baseBlocksLoadedTest() {
        step("Open main page in en language", () ->
                open("/?lang=en"));

        step("Check that page blocks are loaded", () -> {
            $("#content").shouldHave(
                    text("About Us"),
                    text("Our Mission"),
                    text("Our Values"),
                    text("Our Services"),
                    text("Our Works"),
                    text("Our Clients"),
                    text("Our Vacancies"),
                    text("Our Coordinates"));
        });
    }

    @Test
    @DisplayName("Check our clients img is shown")
    void submenuAboutUsIsShownTest() {
        step("Open main page", () ->
                open(""));

        step("Click on client img", () ->
                $("div.owl-stage").click());

        step("Check that img is shown", () ->
                $(".active-item img.pswp__img").shouldBe(visible));

        step("Close img", () ->
                $("[title='Close (Esc)']").click());
    }
}