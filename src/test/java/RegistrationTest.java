import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class RegistrationTest {

    @Test
    public void RegistrationAndLoginCheck()    {

        open("http://localhost/litecart/public_html/");

        SelenideElement newuserlink = $("#box-account-login td a");
        newuserlink.click();

        Random randomNum = new Random();
        int rnd = randomNum.nextInt(100);

        String firstName = "Ivan";
        String lastName = "Ivanov";
        String address = "5th Avenue 6";
        String postcode = "12345";
        String city = "New York";
        String country = "United States";
        String email = "Ivan" + rnd + "@fake.com";
        String phone = "12345678";
        String password = "bestpass";

        $(By.xpath("//input[@name='firstname']")).setValue(firstName);
        $(By.xpath("//input[@name='lastname']")).setValue(lastName);
        $(By.xpath("//input[@name='address1']")).setValue(address);
        $(By.xpath("//input[@name='postcode']")).setValue(postcode);
        $(By.xpath("//input[@name='city']")).setValue(city);
        $(By.className("select2-selection__rendered")).click();
        $(By.xpath("//input[@type='search']")).setValue(country).pressEnter();
        $(By.xpath("//input[@name='email']")).setValue(email);
        $(By.xpath("//input[@name='phone']")).setValue(phone);
        $(By.xpath("//input[@name='password']")).setValue(password);
        $(By.xpath("//input[@name='confirmed_password']")).setValue(password);
        $("button").click();
        $(".account li:nth-child(5) a").click();
        $("#box-account-login td input:nth-child(3)").setValue(email);
        $("#box-account-login td input:nth-child(2)").setValue(password);
        $("#box-account-login td .button-set [name='login']").click();
        $(".account li:nth-child(5) a").click();

    }
}
