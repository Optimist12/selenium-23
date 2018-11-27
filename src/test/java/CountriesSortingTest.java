import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class CountriesSortingTest {
    @Test
    public void checkCountresCheck() {
        open("http://localhost/litecart/public_html/admin/?app=countries&doc=countries");
        $(new By.ByXPath("//input[@name='username']")).setValue("admin");
        $(new By.ByXPath("//input[@name='password']")).setValue("admin");
        $(new By.ByXPath("//button[@value='Login']")).click();
        $(new By.ByXPath("//i[@class='fa fa-sign-out fa-lg']")).shouldBe(visible);

        ElementsCollection elements = $$(By.cssSelector(".dataTable .row"));
        List<String> countries = new ArrayList<>();
        List<String> countriesWithZones = new ArrayList<>();
        for (SelenideElement element : elements)
        {
            countries.add(element.findElement(By.cssSelector("td:nth-child(5) a")).getAttribute("text"));
            if (!(element.findElement(By.cssSelector("td:nth-child(6)")).getAttribute("textContent")).equals("0"))
            {
                countriesWithZones.add(element.findElement(By.cssSelector("a")).getAttribute("innerText"));
            }
        }
        List<String> copy = countries;
        Collections.sort(countries);
        Assert.assertEquals(copy,countries);

        for (String countrieWithZones : countriesWithZones)
        {
            $(By.linkText(countrieWithZones)).click();
            elements = $$(By.cssSelector(".dataTable tr td:nth-child(3)"));
            List<String> zones = new ArrayList<>();
            for (SelenideElement z: elements)
            {
                zones.add(z.getAttribute("textContent"));
            }
            copy = zones;
            Collections.sort(zones);
            Assert.assertEquals(copy,zones);
            open("http://localhost/litecart/public_html/admin/?app=countries&doc=countries");
        }
    }
}
