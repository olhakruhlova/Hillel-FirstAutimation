package pages.ukrnet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import testdata.Email;

import java.time.Duration;

public class HomePage extends BasePage {

    @FindBy(css = ".primary.compose") //рефлексія
    private WebElement writeLetterButtonElement;
    @FindBy(name = "toFieldInput")
    private WebElement toInputElement;
    @FindBy(name = "subject")
    private WebElement subjectInputElement;
    @FindBy(id = "tinymce")
    private WebElement letterBodyElement;
    @FindBy(css = ".screen__head .send.button")
    private WebElement sendButtonElement;
    @FindBy(css = "#mce_0_ifr")
    private WebElement bodуIFrameElement;
    @FindBy(css = ".sendmsg__ads-ready")
    private WebElement letterIsSendElement;

    public HomePage(WebDriver driver) {
        super(driver);
        pageUrl = "https://mail.ukr.net/desktop";
    }

    public void clickWriteLetter() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions
                                .elementToBeClickable(writeLetterButtonElement)).click();
        // -> експлісіт вейт (чемаємо щоб елемент був клікабельним)
        //webDriverWait.until(ExpectedConditions.visibilityOf(writeLetterButtonElement));
        //writeLetterButtonElement.click(); - не порідний окремий рядок, переюзала це в попередньому рядку
    }

    public void writeLetter(Email email) {
        toInputElement.sendKeys(email.getToUser());
        subjectInputElement.sendKeys(email.getEmailSubject());
        try {
            driver.switchTo().frame(bodуIFrameElement);
            letterBodyElement.sendKeys(email.getEmailBody());
        } finally {
            driver.switchTo().parentFrame();
        }
    }

    public void sendLetter() {
        sendButtonElement.click();
    }

    public boolean getTextLetterIsSend(String expectedText) {
        return webDriverWait.until(driver -> letterIsSendElement.getText()
                .matches("^" + expectedText + "\n.*"));
    }
}
