package pages.google;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class SelenideHomePage extends BasePage {

    public static final By DONATE_ELEMENT = By.cssSelector(".donate_header");
    public static final By BLOG_TAB = By.xpath("//a[contains(text(), 'Blog')]");
    public SelenideHomePage findDonateElement() {
        $(DONATE_ELEMENT).shouldHave(Condition.text("Selenide Supports Ukraine \uD83C\uDDFA\uD83C\uDDE6"));
        return this;
    }

    public SelenideHomePage clickBlogTab() {
        $(BLOG_TAB).click();
        return this;
    }
}
