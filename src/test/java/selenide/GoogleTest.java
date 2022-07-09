package selenide;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.google.GoogleHomePage;
import pages.google.GoogleResultPage;
import pages.google.SelenideBlogPage;
import pages.google.SelenideHomePage;

import static com.codeborne.selenide.Selenide.open;


public class GoogleTest {
    // https://ru.selenide.org/

    @BeforeClass
    public void setUp() {
        //Налаштування
        Configuration.savePageSource = false;
        Configuration.browserSize = "1920x600";
        Configuration.timeout = 5000; //default value 4000;
    }

    @Test
    public void userShouldSearch() {
        // теж саме за допомогою Selenium WebDriver
//        System.setProperty("selenium.chrome.driver", "chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://google.com");

        // теж саме за допомогою Selenide
//        Selenide.open("https://google.co");
        //по дефолту вфдкриває Google Chrome

        open("https://google.com");
        GoogleHomePage googleHomePage = new GoogleHomePage();
        googleHomePage.waitUntilSearchFieldDisplayed()
                .getSearchText("Selenide")
                .pressEnter();

        GoogleResultPage googleResultPage = new GoogleResultPage();
        googleResultPage.waitUntilSearchElementsDisplayed()
                .checkCounterOfElements()
                .openFirstLink();

        SelenideHomePage selenideHomePage = new SelenideHomePage();
        selenideHomePage.findDonateElement()
                .clickBlogTab();

        SelenideBlogPage selenideBlogPage = new SelenideBlogPage();
        selenideBlogPage.fingBlogBanner();




        //взяти вікно та його використовувати
//        WebDriverRunner.getWebDriver();//взяти вікно та його використовувати
    }
}
