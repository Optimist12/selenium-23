
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by Rutol on 20.12.2016.
 */
public class NewWindowTest {

    private WebDriver driver;
    private WebDriverWait wait;
    int countryQuantity, linksQuantity;
    int randomIndex;
    WebElement countryRow;
    List<WebElement> countryRows, listLinks;
    String originalWindow, newWindow;
    Set<String> existingWindows;

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles=driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size()>0 ? handles.iterator().next():null;
            }
        };
    }

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }


    @Test
    public void NewWindowTest() {

        driver.get("http://localhost/litecart/public_html/admin/"); //открыть страницу
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
        driver.get("http://localhost/litecart/public_html/admin/?app=countries&doc=countries");
        wait.until(titleContains("Countries"));
        countryRows = driver.findElements(By.cssSelector("[name=countries_form] .row"));
        countryQuantity=countryRows.size();
        final Random random = new Random();
        randomIndex = random.nextInt(countryQuantity-1);
        countryRow=countryRows.get(randomIndex);
        countryRow.findElement(By.cssSelector("a")).click();
        wait.until(titleContains("Edit Country"));
        listLinks = driver.findElements(By.cssSelector("form .fa-external-link"));
        linksQuantity = listLinks.size();
        for (int i=0; i<linksQuantity; i++) {
            originalWindow=driver.getWindowHandle();
            existingWindows=driver.getWindowHandles();
            listLinks.get(i).click();
            newWindow=wait.until(anyWindowOtherThan(existingWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(originalWindow);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}