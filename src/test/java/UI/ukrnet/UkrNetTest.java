package UI.ukrnet;

import UI.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.mailinator.MailinatorInboxPage;
import pages.ukrnet.HomePage;
import pages.ukrnet.LoginPage;
import testdata.Email;
import testdata.User;

public class UkrNetTest extends BaseTest {

    @Test
    private void sendEmailToMailinatorTest() {
        User user = new User("olha.kruhlova@ukr.net", "qwerty123!");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login(user);

        HomePage homePage = new HomePage(driver);
        homePage.waitUntilLoaded();
        // Assert.assertTrue(driver.getCurrentUrl().contains(homePage.getPageUrl()),"Url isn't as expected");
        // не має сенсу в цій перевірки томущо ми юзаємо wait

        Email email = new Email("olha.kruhlova@mailinator.com", "qwerty", "test body");
        homePage.clickWriteLetter();
        homePage.writeLetter(email);
        homePage.sendLetter();
        Assert.assertTrue(homePage.getTextLetterIsSend("Ваш лист надіслано"));

        MailinatorInboxPage mailinatorInboxPage = new MailinatorInboxPage(driver);
        mailinatorInboxPage.navigate();
        mailinatorInboxPage.waitUntilLoaded();

        Email emailWithoutDomen = new Email("olha.kruhlova");
        mailinatorInboxPage.goToInbox(emailWithoutDomen);
        mailinatorInboxPage.waitUntilJsIsReady();
        mailinatorInboxPage.openLastLetter();

        //mailinatorInboxPage.waitUntilInformAboutLetterIsDisplayed();
        Assert.assertEquals(email.getEmailBody(), mailinatorInboxPage.getLetterBody(), "Letter with incorrect body");
        Assert.assertEquals(user.getLogin(), mailinatorInboxPage.getFromUser(), "Letter from not correct user");
        Assert.assertEquals(email.getEmailSubject(), mailinatorInboxPage.getLetterSubject(), "Letter with incorrect subject");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
