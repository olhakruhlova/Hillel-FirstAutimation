import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class TestTitles {
    private By titlesLocatpr = By.cssSelector("wp-categories-title");
    private WebDriver driver = new ChromeDriver();
    @Test
    public void titleTest() {
        driver.get("http://www.leafground.com/home.html");
        List<WebElement> titles = driver.findElements(titlesLocatpr);
        List<String> titleTest;

        // titleTest = titles.stream().map(element -> element.getText()).collect(Collectors.toList());
        // більш простіше
        titleTest = titles.stream().map(WebElement::getText).collect(Collectors.toList());

        // преобразовуємо елементи
        titleTest = titles.stream()
                .map(WebElement::getText)
                .map(title -> title = title + "qwerty")
                .collect(Collectors.toList());

        // фільтер елементів, відфільтровуємо усі що починаються з A
        titleTest = titles.stream()
                .filter(element -> element.getText().toCharArray()[0] == 'A')
                .map(WebElement::getText)
                .collect(Collectors.toList());
        System.out.println();
    }
    @Test
    public void stringFormat() {
        String text = "My name is: %s, and my age is %d";
        // %s - очікую стрінгу
        // %d - очікую цифру
        String newText = String.format(text, "Leo", 24);
        System.out.println(newText);
    }
}
