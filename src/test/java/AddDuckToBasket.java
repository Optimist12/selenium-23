import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.*;

public class AddDuckToBasket {

    @Test
    public void addToBasketAndDeleteTest() throws Exception {
        // open mainpage of shop and add 3 ducks to cart in loop
        for (int i = 1; i <= 3; i++) {
            String num = String.valueOf(i);
            open("http://localhost/litecart/public_html/");
            $("#box-most-popular li:nth-child(1)").click();
            if ($(By.tagName("h1")).getAttribute("textContent").equals("Yellow Duck"))
                new Select($("[name='options[Size]']")).selectByValue("Large");
            $(".quantity button").click();
            $("#cart .quantity").waitUntil((Condition.text(num)), 5000);

        }
        $("#cart .link").click();
        ElementsCollection row = $$(By.cssSelector("#order_confirmation-wrapper tr td.item"));
        for (int i = 0; i <= row.size()  ; i++) {
            ElementsCollection shortcuts = $$(By.cssSelector("#box-checkout-cart .shortcuts li"));
            if (shortcuts.size() > 0) {
                SelenideElement shortcut = $("#box-checkout-cart .shortcuts li:nth-child(1)");
                shortcut.click();
                $(By.xpath("//button[@value='Remove']")).click();

            }
            else
            {
                $(By.xpath("//button[@value='Remove']")).click();
                row.get(0).waitUntil(Condition.not(Condition.visible), 5000);

            }

        }
        row.get(0).waitUntil(Condition.not(Condition.visible), 5000);

        Assert.assertEquals($("#checkout-cart-wrapper p").getText(),
                "There are no items in your cart.");


    }
}
