package UI;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import practice.Drivers;
import practice.MyAnnotation;

import static practice.Drivers.CHROME_DRIVER;
import static practice.Drivers.FIREFOX_DRIVER;

@MyAnnotation(myField = "qwerty")
public class Practice {

    private WebDriver driver;

    private void createDriver(Drivers driver) {
        if(driver.equals(CHROME_DRIVER)) {
            //create chrome driver
        } else if (driver.equals(FIREFOX_DRIVER)) {
            //create firefox driver
        }
    }

    @Test
    public void enumTest() {
        System.out.println(CHROME_DRIVER.getDriverName());
    }
}
