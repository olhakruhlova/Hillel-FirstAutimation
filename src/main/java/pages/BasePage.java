package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected String pageUrl;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void waitUntilLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(ExpectedConditions.urlContains(pageUrl));
    }

    public void navigate() {
        driver.get(pageUrl);
    }
}
