import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AddDuckTest {

    @Test
    public void addNewDuckCheck() {
        open("http://localhost/litecart/public_html/admin/");
        $(new By.ByXPath("//input[@name='username']")).setValue("admin");
        $(new By.ByXPath("//input[@name='password']")).setValue("admin");
        $(new By.ByXPath("//button[@value='Login']")).click();
        open("http://localhost/litecart/public_html/admin/?app=catalog&doc=catalog");
        $("#content .button:nth-child(2)").click();
        $("input[name='status'][value='1']").click();
        String name = "Koala3";
        $("[name='name[en]']").setValue(name);
        String code = "koala3";
        $("[name=code]").setValue(code);
        $("[name='categories[]'][value='0']").click();
        $("[name='categories[]'][value='1']").click();
        $("[name='product_groups[]'][value='1-3']").click();
        String quantity = "2";
        $("[name = 'quantity']").clear();
        $("[name = 'quantity']").setValue(quantity);
        File pic = new File("src/test/java/Koala.jpg");
        String filepath = pic.getAbsolutePath();
        $("[name='new_images[]']").setValue(filepath);
        $("#add-new-image").click();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "dd/MM/yyyy" );
        LocalDate dateFrom = LocalDate.now();
        LocalDate dateTo = LocalDate.now().plusYears ( 1 );
        String sdatefrom = dateFrom.format(formatter);
        String sdateto = dateTo.format(formatter);
        $("[name='date_valid_from']").sendKeys(sdatefrom);
        $("[name='date_valid_to']").sendKeys(sdateto);
        $(".index li:nth-child(2)").click();
        new Select($("[name='manufacturer_id']")).selectByVisibleText("ACME Corp.");
        String keyword = "diver";
        $("[name='keywords']").setValue(keyword);
        String shortdescription = "test";
        $("[name='short_description[en]'").sendKeys(shortdescription);
        String description = "A diver duck - for test";
        $(".trumbowyg-editor").sendKeys(description);
        String headTitle = "Not Duck";
        $("[name='head_title[en]'").setValue(headTitle);
        String meta_desc = "Meta_description text";
        $("[name='meta_description[en]'").setValue(meta_desc);
        $(".index li:nth-child(4)").click();
        String price = "20.5";
        $("[name = 'purchase_price']").clear();
        $("[name = 'purchase_price']").setValue(price);
        new Select($("[name='purchase_price_currency_code']")).selectByValue("EUR");
        price = "66";
        $("[name = 'prices[USD]']").clear();
        $("[name = 'prices[USD]']").setValue(price);
        $("[name = save]").click();
        open("http://localhost/litecart/public_html/admin/?app=catalog&doc=catalog&category_id=1");
        $(By.linkText(name)).shouldBe(Condition.visible);

    }

}
