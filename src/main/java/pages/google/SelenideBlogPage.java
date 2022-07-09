package pages.google;

import org.openqa.selenium.By;
import pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SelenideBlogPage extends BasePage {

    public static final By BLOG_BANNER = By.xpath("//h3[contains(text(), 'Selenide blog')]");

    public SelenideBlogPage fingBlogBanner() {
        $(BLOG_BANNER).shouldBe(visible);
        return this;
    }
}
