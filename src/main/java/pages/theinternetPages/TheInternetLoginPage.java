package pages.theinternetPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import testdata.User;

public class TheInternetLoginPage extends BasePage {
    @FindBy(id = "username")
    private WebElement loginField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "[type = submit]")
    private WebElement loginButton;

    @FindBy(css = "#flash")
    private WebElement errorMessage;

    public TheInternetLoginPage(WebDriver driver) {
        super(driver);
        pageUrl = "http://the-internet.herokuapp.com/login";
    }

    public void login(User user) {
        loginField.sendKeys(user.getLogin());
        passwordField.sendKeys(user.getPassword());
        loginButton.click();
    }

    public String getErrorMessage() {
        return waitAndGetText(errorMessage);
    }
}
