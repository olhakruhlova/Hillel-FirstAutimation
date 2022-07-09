package pages.google;

import org.openqa.selenium.By;
import pages.BasePage;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class GoogleResultPage extends BasePage {

    private static final By LIST_OF_ELEMENTS = By.xpath("//h3[contains(text(), 'Selenide')]");


    public GoogleResultPage waitUntilSearchElementsDisplayed() {
        $$(LIST_OF_ELEMENTS).filter(visible);
        return this;
    }

    public GoogleResultPage checkCounterOfElements() {
        $$(LIST_OF_ELEMENTS).shouldHave(sizeGreaterThan(7));
        return this;
    }

    public GoogleResultPage openFirstLink() {
        $$(LIST_OF_ELEMENTS).get(0).click();
        return this;
    }
}
