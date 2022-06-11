package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testdata.Email;

public class MailinatorInboxPage extends BasePage {

    private By inboxField = By.id("inbox_field");
    private By goButton = By.className("primary-btn");
    // private String email = "olha.kruhlova";
    private By lastLetterSubject = By.xpath("//td[contains(text(), 'qwerty')]");
    private By bodyIframe = By.id("html_msg_body");
    private By fromData = By.xpath("//div[contains(text(), 'ukr.net')]");
    private By emailBodyText = By.className("xfmc1");

    private By letterSubjectText = By.xpath("//div [contains(text(), 'qwerty')]");


    public MailinatorInboxPage(WebDriver driver) {
        super(driver);
        pageUrl = "https://www.mailinator.com/v4/public/inboxes.jsp";
    }

    public void goToInbox(Email email) {
        driver.findElement(inboxField).sendKeys(email.getToUserWithoutEmail());
        driver.findElement(goButton).click();
    }

    public void openLastLetter() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(lastLetterSubject));
        driver.findElement(lastLetterSubject).click();
    }

    public void waitUntilInformAboutLetterIsDisplayed() {
        for (int i = 0; i < 8; i++) {
            System.out.println(i);
            try {
                if (driver.findElement(fromData).isDisplayed()) {
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

    public String getLetterSubject() {
        return driver.findElement(letterSubjectText).getText();
    }

    public String getFromUser() {
        return driver.findElement(fromData).getText();
    }

    public String getLetterBody() {
        String bodyText;
        try {
            driver.switchTo().frame(driver.findElement(bodyIframe));
            bodyText = driver.findElement(emailBodyText).getText();
        } finally {
            driver.switchTo().parentFrame();
        }
        return bodyText;
    }
}