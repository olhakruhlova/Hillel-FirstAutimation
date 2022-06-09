package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testdata.Email;
import testdata.User;

public class MailinatorPage extends BasePage {

    private By inboxEmailField = By.id("inbox_field");
    private By goButton = By.className("primary-btn");
    // private String email = "olha.kruhlova";
    private By subjectLetter = By.xpath("//td[contains(text(), 'qwerty')]");
    private By bodyIframe = By.id("html_msg_body");
    private By fromData = By.xpath("//div[contains(text(), 'ukr.net')]");
    private By emailBodyText = By.className("xfmc1");
    private String textBody = "text body";


    public MailinatorPage(WebDriver driver) {
        super(driver);
        pageUrl = "https://www.mailinator.com/v4/public/inboxes.jsp";
    }

    public void enterEmail(Email email) {
        driver.findElement(inboxEmailField).sendKeys(email.getToUserWithoutEmail());
        driver.findElement(goButton).click();
    }

    public boolean letterExist() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement element = driver.findElement(subjectLetter);
        return element.isDisplayed();
    }

    public void openLetter() {
        driver.findElement(subjectLetter).click();
    }

    public boolean verifyEmail(Email email, User user) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        boolean loginEquals = user.getLogin().equals(driver.findElement(fromData).getText());
        boolean emailEquals;

        try {
            driver.switchTo().frame(driver.findElement(bodyIframe));
            emailEquals = email.getEmailBody().equals(driver.findElement(emailBodyText).getText());
        } finally {
            driver.switchTo().parentFrame();
        }
        return emailEquals && loginEquals;
    }
//    для засвідчення що кокретний елемент є на сторінці
//    public void waitUntilLetterDisplayed() {
//        WebElement element = driver.findElement(subjectLetter);
//        new WebDriverWait(driver, Duration.ofSeconds(4)).until(ExpectedConditions.visibilityOfElementLocated(subjectLetter));
//    }
}
