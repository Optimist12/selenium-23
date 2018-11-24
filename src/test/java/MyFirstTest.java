import org.junit.Test;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class MyFirstTest extends TestBase {

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://ya.ru");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".input__control.input__input")).sendKeys("webdriver");
        driver.findElement(new By.ByXPath("//button[@type='submit']")).click();
        wait.until(titleContains("webdriver"));
    }

}
