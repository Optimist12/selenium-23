import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LeftPanelTest extends TestBase {
    AdminPage adminPage = new AdminPage();

    @Test
    public void clickOnAllMenuItemsTest() {

        $(new By.ByXPath("//input[@name='username']")).setValue("admin");
        $(new By.ByXPath("//input[@name='password']")).setValue("admin");
        $(new By.ByXPath("//button[@value='Login']")).click();
        $(new By.ByXPath("//i[@class='fa fa-sign-out fa-lg']")).shouldBe(visible);
        adminPage.clickElementsonLeftPanel();
    }
}
