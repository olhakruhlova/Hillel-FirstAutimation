package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testdata.Email;

public class HomePage extends BasePage {

    private By writeLetterButton = By.cssSelector(".primary.compose");
    private By toInput = By.name("toFieldInput");
    private By subjectInput = By.name("subject");
    private By letterBody = By.id("tinymce");
    private By sendButton = By.cssSelector(".screen__head .send.button");
    private By bodуIFrame = By.cssSelector("#mce_0_ifr");

    public HomePage(WebDriver driver) {

        super(driver);
        pageUrl = "https://mail.ukr.net/desktop";
    }

    public void clickWriteLetter() {
        driver.findElement(writeLetterButton).click();
    }

    public void writeLetter(Email email) {
        driver.findElement(toInput).sendKeys(email.getToUser());
        driver.findElement(subjectInput).sendKeys(email.getEmailSubject());
        try {
            driver.switchTo().frame(driver.findElement(bodуIFrame));
            driver.findElement(letterBody).sendKeys(email.getEmailBody());
        } finally {
            driver.switchTo().parentFrame();
        }
    }

    public void sendLetter() {
        driver.findElement(sendButton).click();
    }
}
