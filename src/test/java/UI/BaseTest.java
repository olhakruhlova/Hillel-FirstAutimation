package UI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;


    @BeforeMethod
    public void setUp() {
        System.setProperty("selenium.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        // driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        // по суті те саме
        // implicitlyWait
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // -> появу кожного елементу буде очікуватись протягом 10 секунт (пінгує частіше)
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }
}
