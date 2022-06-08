import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
  public class TestNGInteractWithCheckboxes {
    private String baseUrl = "http://www.leafground.com/pages/checkbox.html";
    private By languagesJavaInputLocator = By.xpath("//label[text() = 'Select the languages that you know?']/following-sibling::input");
    private By confirmSeleniumInputLocator = By.xpath("//label[text() = 'Confirm Selenium is checked']/following-sibling::input");
    private By deSelectOnlyInputLocator = By.xpath("//label[text() = 'DeSelect only checked']/following-sibling::input[2]");

    private By selectAllCheckboxesInputLocators = By.xpath("//label[text() = 'Select all below checkboxes ']/following-sibling::input");

    private WebDriver driver;
    private WebElement languagesJavaInput;
    private WebElement confirmSeleniumInput;
    private WebElement deSelectOnlyInput;
    private List<WebElement> selectAllCheckboxesInputsList;

    @BeforeMethod
    public void setUp() {
        System.setProperty("selenium.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(baseUrl);
        languagesJavaInput = driver.findElement(languagesJavaInputLocator);
        confirmSeleniumInput = driver.findElement(confirmSeleniumInputLocator);
        deSelectOnlyInput = driver.findElement(deSelectOnlyInputLocator);
        selectAllCheckboxesInputsList = driver.findElements(selectAllCheckboxesInputLocators);
    }

    @Test
    public void languagesJavaInputTest() {
        languagesJavaInput.click();
        Assert.assertTrue(languagesJavaInput.isSelected(), "\"Java\" checkbox isn't selected");
    }

    @Test
    public void confirmSeleniumInputTest() {
        Assert.assertTrue(confirmSeleniumInput.isSelected(), "\"Selenium\" checkbox isn't checked by default");
    }

    @Test
    public void deSelectOnlyInputTest() {
        deSelectOnlyInput.click();
        Assert.assertFalse(deSelectOnlyInput.isSelected(), "Checkbox isn't unchecked");
    }

    @Test
    public void selectAllCheckboxesInputTest() {
        for (WebElement input : selectAllCheckboxesInputsList) {
            input.click();
            Assert.assertTrue(input.isSelected(),"Some of the checkbox isn't checked");
        }
    }

    @AfterMethod
    public void tearmDown() {
        driver.close();
    }
}
