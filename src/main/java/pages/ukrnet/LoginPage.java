package pages.ukrnet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import testdata.User;

public class LoginPage extends BasePage {
    @FindBy(name = "login")
    private WebElement loginFieldElement;
    @FindBy(css = "[name='password']")
    private WebElement passwordFieldElement;
    @FindBy(css = "[type='submit']")
    private WebElement submitButtonElement;
    // input[name='login']
    //.root>[name='login'] приямий нащадок
    //.root [name='login'] нащадок на любому вкладенні

    public LoginPage(WebDriver driver) {
        super(driver);
        pageUrl = "https://mail.ukr.net/";
    }

    public void login(User user) {
        loginFieldElement.sendKeys(user.getLogin());
        passwordFieldElement.sendKeys(user.getPassword());
        submitButtonElement.click();
    }

}
