package UI.testleaf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGWorkWithEditFielda {
    // input
    // isEnaible
    // Disaiblep
    private String baseUrl = "http://www.leafground.com/pages/Edit.html";
    private By emailInputLocator = By.xpath("//input[@id='email']");
    private By appendInputLocator = By.xpath("//label[text()='Append a text and press keyboard tab']/following-sibling::input");
    private By getTextInputLocaor = By.xpath("//label[text()='Get default text entered']/following-sibling::input");
    private By clearTextLocator = By.xpath("//label[text()='Clear the text']/following-sibling::input");
    private By disabledInputLocator = By.xpath("//label[text()='Confirm that edit field is disabled']/following-sibling::input");
    private WebDriver driver;
    private WebElement emailInput;
    private WebElement appendInput;
    private WebElement getTextInput;
    private WebElement clearTextInput;
    private WebElement disabledInput;

    @BeforeMethod
    public void setUp() {
        System.setProperty("selenium.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(baseUrl);
        emailInput = driver.findElement(emailInputLocator);
        appendInput = driver.findElement(appendInputLocator);
        getTextInput = driver.findElement(getTextInputLocaor);
        clearTextInput = driver.findElement(clearTextLocator);
        disabledInput = driver.findElement(disabledInputLocator);
    }

    @Test
    public void emailInputTests() {
        String email = "email_exampl@te.st";
        emailInput.sendKeys(email);
        Assert.assertEquals(emailInput.getAttribute("value"), email, "Text is not equal");
        // emaiInput.getText() не працює то слід спробувати emailInput.getAttribute("value")
    }

    @Test
    public void clearInputTest() {
        clearTextInput.clear();
        Assert.assertTrue(ExpectedConditions.attributeToBe(clearTextInput, "value", "").apply(driver));
    }

    @Test
    public void disableTest() {
        Assert.assertFalse(disabledInput.isEnabled());
    }

    @AfterMethod
    public void tearmDown() {
        driver.close();
    }
}

//        By inputs = By.xpath("//input");
//        List<WebElement> inputs = driver.findElement(inputs)
//        By secondInputLocator = By.xpath("//input[2]");
//        WebElement secondInput = driver.findElement(secondInputLocator);
//        secondInput.sendKeys("It is second input");
//        //label[text()='Enter your'] - усесь текст
//        //label[contains(text(), 'Enter your')] - елемент у якому є текст
//        //label[contains(@for, 'ema')]
//        //label[text()='Enter your email address']/following-sibling::input - перший сестринській елемент input
//        //label[text()='Enter your email address']/following-sibling::input[@type='text']

