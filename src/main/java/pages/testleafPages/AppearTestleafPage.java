package pages.testleafPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;

public class AppearTestleafPage extends BasePage {

    @FindBy(id = "btn")
    private WebElement appearButton;

    public AppearTestleafPage(WebDriver driver) {
        super(driver);
        pageUrl = "http://www.leafground.com/pages/appear.html";
    }


    public void waiteThatElementIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(appearButton));
    }

    public boolean checkIsDisplayed() {
        return super.checkThatElementIsDisplayed(appearButton);
    }
}
