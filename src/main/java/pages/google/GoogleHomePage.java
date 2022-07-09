package pages.google;

import org.openqa.selenium.By;
import pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class GoogleHomePage extends BasePage {
    private static final By SEARCH_FIELD = By.cssSelector("[name = 'q']");

    // щоб повертати послідовністьв викликів - застосовуємо патерн chain of responsibility
    public GoogleHomePage waitUntilSearchFieldDisplayed() {
        $(SEARCH_FIELD).shouldBe(visible);
        return this;
    }

    public GoogleHomePage getSearchText(String text) {
        $(SEARCH_FIELD).setValue(text);
        return this;
    }

    public GoogleHomePage pressEnter() {
        $(SEARCH_FIELD).pressEnter();
        return this;
    }
}
