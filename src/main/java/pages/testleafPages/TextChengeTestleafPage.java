package pages.testleafPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;

public class TextChengeTestleafPage extends BasePage {

    @FindBy(id = "btn")
    private WebElement buttonWithText;


    public TextChengeTestleafPage(WebDriver driver) {
        super(driver);
        pageUrl = "http://www.leafground.com/pages/TextChange.html";
    }

    public String getStartTextOnTheButton() {
        return buttonWithText.getText();
    }

    public boolean waitIfTextIsDisplayed() {
      return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementWithText(By.id("btn"), "Click ME!"));
    }
}