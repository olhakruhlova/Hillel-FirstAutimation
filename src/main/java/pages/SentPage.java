package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SentPage extends BasePage {

    private By sendMsgDiv = By.cssSelector(".sendmsg__ads-ready");


    public SentPage(WebDriver driver) {
        super(driver);
        pageUrl = "https://mail.ukr.net/desktop#sendmsg/sent";
    }

    public void confirmationOfSendLetter() {
        driver.findElement(sendMsgDiv);
    }
}
