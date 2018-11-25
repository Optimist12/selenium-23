import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class CountStickerTest {

    @Test
    public void checkStickerOnDuck() {
        open("http://localhost/litecart/public_html/en/");
        ElementsCollection ducks = $$(".product");
        ducks.forEach(duck -> Assert.assertTrue(duck.findElements(By.cssSelector(".sticker")).size() == 1));

    }
}
