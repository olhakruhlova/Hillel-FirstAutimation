package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testdata.Email;

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
        PageFactory.initElements(driver, this);
    }

    public void clickWriteLetter() {
        webDriverWait.until(ExpectedConditions.visibilityOf(writeLetterButtonElement));
        writeLetterButtonElement.click();
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
