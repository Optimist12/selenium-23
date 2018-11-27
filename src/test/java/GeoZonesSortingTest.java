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

public class GeoZonesSortingTest {

    @Test
    public void CheckGeozonesSorting() {
        open("http://localhost/litecart/public_html/admin/?app=geo_zones&doc=geo_zones");
        $(new By.ByXPath("//input[@name='username']")).setValue("admin");
        $(new By.ByXPath("//input[@name='password']")).setValue("admin");
        $(new By.ByXPath("//button[@value='Login']")).click();
        $(new By.ByXPath("//i[@class='fa fa-sign-out fa-lg']")).shouldBe(visible);
        ElementsCollection elements = $$(".dataTable .row td:nth-child(3)");
        ArrayList<String> countries = new ArrayList<>();
        for (SelenideElement element : elements){
            countries.add(element.getAttribute("innerText")); }
        for (String countrie : countries)
        {
            $(By.linkText(countrie)).click();
            elements = $$(".dataTable tr td:nth-child(3) select option:checked");
            List<String> geoZones = new ArrayList<>();
            for (SelenideElement element : elements)
            {
                geoZones.add(element.getAttribute("textContent"));
            }
            List<String> geoZonesSorting = geoZones;
            Collections.sort(geoZones);
            Assert.assertEquals(geoZonesSorting,geoZones);
            open("http://localhost/litecart/public_html/admin/?app=geo_zones&doc=geo_zones");
        }
    }
}
