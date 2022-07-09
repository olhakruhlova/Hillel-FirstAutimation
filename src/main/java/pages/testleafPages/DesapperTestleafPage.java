package pages.testleafPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;

public class DesapperTestleafPage extends BasePage {

    @FindBy(id = "btn")
    private WebElement hiddenButtonElement;

    public DesapperTestleafPage(WebDriver driver) {
        super(driver);
        pageUrl = "http://www.leafground.com/pages/disapper.html";
    }

    public boolean waiteThatElementHidden() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .invisibilityOf(hiddenButtonElement));
    }

    public boolean checkIsDisplayed() {
        return super.checkThatElementIsDisplayed(hiddenButtonElement);
    }
}
