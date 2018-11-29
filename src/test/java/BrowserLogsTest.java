import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogType;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BrowserLogsTest {

    @Test
    public void checkBrowserLogs ()    {
        open("http://localhost/litecart/public_html/admin");


        $(By.xpath("//input[@name='username']")).setValue("admin");
        $(By.xpath("//input[@name='password']")).setValue("admin");
        $(By.xpath("//button[@value='Login']")).click();
        $(By.xpath("//i[@class='fa fa-sign-out fa-lg']")).shouldBe(visible);

        open("http://localhost/litecart/public_html/admin/?app=catalog&doc=catalog&category_id=1");

        ElementsCollection goodsw = $$(By.xpath(".//tr/td[3]/a")); //xpath
        List<String> goods = new ArrayList<>();
        for (SelenideElement g : goodsw)
        {
            if (!g.getText().equals("Subcategory"))
                goods.add(g.getAttribute("textContent"));
        }

        for (String g : goods)
        {
            $(By.linkText(g)).click();
            Assert.assertTrue(getWebDriverLogs(LogType.BROWSER, Level.ALL).size() == 0);
            open("http://localhost/litecart/public_html/admin/?app=catalog&doc=catalog&category_id=1");
        }

    }
}
