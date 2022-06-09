package ukrnet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MailinatorPage;
import pages.SentPage;
import testdata.Email;
import testdata.User;

public class UkrNetTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("selenium.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        // driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @Test
    private void sendEmailToMailinatorTest() {
        User user = new User("olha.kruhlova@ukr.net", "qwerty123!");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login(user);

        HomePage homePage = new HomePage(driver);
        homePage.waitUntilLoaded();
        // Assert.assertTrue(driver.getCurrentUrl().contains(homePage.getPageUrl()),"Url isn't as expected");
        // не має сенсу в цій перевірки тому що ми юзаємо wait

        Email email = new Email("olha.kruhlova@mailinator.com", "qwerty", "test body");
        homePage.clickWriteLetter();
        homePage.writeLetter(email);
        homePage.sendLetter();

        SentPage sentPage = new SentPage(driver);
        sentPage.waitUntilLoaded();
        sentPage.confirmationOfSendLetter();

        MailinatorPage mailinatorPage = new MailinatorPage(driver);
        mailinatorPage.navigate();
        mailinatorPage.waitUntilLoaded();

        Email emailWithoutDomen = new Email("olha.kruhlova");
        mailinatorPage.enterEmail(emailWithoutDomen);

        //mailinatorPage.waitUntilLetterDisplayed();
        Assert.assertTrue(mailinatorPage.letterExist(), "Sent letter isn't found");
        mailinatorPage.openLetter();


        Assert.assertTrue(mailinatorPage.verifyEmail(email, user), "Email body isn't correct");
    }

    @AfterMethod
    public void tearmDown() {
        driver.close();
    }
}
