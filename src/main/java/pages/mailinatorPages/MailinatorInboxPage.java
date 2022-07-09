package pages.mailinatorPages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import pages.BasePage;
import testdata.Email;

import java.time.Duration;

public class MailinatorInboxPage extends BasePage {

    @FindBy(id = "inbox_field")
    private WebElement inboxFieldElement;
    @FindBy(className = "primary-btn")
    private WebElement goButtonElement;
    @FindBy(xpath = "//td[contains(text(), 'qwerty')]")
    private WebElement lastLetterSubjectElement;
    @FindBy(id = "html_msg_body")
    private WebElement bodyIframeElement;
    @FindBy(xpath = "//div[contains(text(), 'ukr.net')]")
    private WebElement fromDataElement;
    @FindBy(className = "xfmc1")
    private WebElement emailBodyTextElement;
    @FindBy(xpath = "//div [contains(text(), 'qwerty')]")
    private WebElement letterSubjectTextElement;

    public MailinatorInboxPage(WebDriver driver) {
        super(driver);
        pageUrl = "https://www.mailinator.com/v4/public/inboxes.jsp";
    }

    public void goToInbox(Email email) {
        inboxFieldElement.sendKeys(email.getToUserWithoutEmail());
        goButtonElement.click();
    }

    public void openLastLetter() {
        webDriverWait.until(ExpectedConditions.visibilityOf(lastLetterSubjectElement));
        lastLetterSubjectElement.click();
    }

    public void waitUntilInformAboutLetterIsDisplayed() {
        for (int i = 0; i < 8; i++) {
            System.out.println(i);
            try {
                if (fromDataElement.isDisplayed()) {
                    return;
                }
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        throw new TimeoutException();
    }

    public void waitUntilInformAboutLetterIsDisplayed(WebElement webElement) {
//        for (int i = 0; i < 8; i++) {
//            System.out.println(i);
//            try {
//                if (fromDataElement.isDisplayed()) {
//                    return;
//                }
//            } catch (NoSuchElementException e) {
//                System.out.println(e.getMessage());
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//        throw new TimeoutException();
        // Теж саме по суті але за допомогою FluentWait
        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200)) //вказуємо через скільки ми явно опрашуємо сторінку
                .ignoring(NoSuchElementException.class) //ігноруємо ексепшен
                .until(ExpectedConditions.visibilityOf(webElement));

    }

    public String getLetterSubject() {
        return letterSubjectTextElement.getText();
    }

    public String getFromUser() {
        return fromDataElement.getText();
    }

    public String getLetterBody() {
        String bodyText;
        try {
            driver.switchTo().frame(bodyIframeElement);
            bodyText = emailBodyTextElement.getText();
        } finally {
            driver.switchTo().parentFrame();
        }
        return bodyText;
    }
}
