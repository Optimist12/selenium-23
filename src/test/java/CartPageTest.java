import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CartPageTest {

    @Test
    public void cartPageCheck() {
        open("http://localhost/litecart/public_html/");

        SelenideElement catalog = $("#box-campaigns .price-wrapper");
        String mainPage = $("#box-campaigns .product .name").getAttribute("textContent");
        String price = catalog.findElement(By.cssSelector(".regular-price")).getAttribute("textContent");
        String campaignPrice = catalog.findElement(By.cssSelector(".campaign-price")).getAttribute("textContent");
        String priceColor = catalog.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        String priceDec = catalog.findElement(By.cssSelector(".regular-price")).getTagName();
        String priceFontSize = catalog.findElement(By.cssSelector(".regular-price")).getCssValue("font-size");
        String campaignPriceColor = catalog.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        String campaignPriceDec = catalog.findElement(By.cssSelector(".campaign-price")).getTagName();
        String campaignPriceFontSize = catalog.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size");

        $("#box-campaigns .link").click();

        String titleproductPage = $("#box-product .title").getAttribute("textContent");
        SelenideElement productPage = $("#box-product .price-wrapper");
        String priceOnProductPage = productPage.findElement(By.cssSelector(".regular-price")).getAttribute("textContent");
        String campaignPriceOnProductPage = productPage.findElement(By.cssSelector(".campaign-price")).getAttribute("textContent");
        String priceColorOnProductPage = productPage.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        String priceDecOnProductPage = productPage.findElement(By.cssSelector(".regular-price")).getTagName();
        String priceFontSizeOnProductPage = productPage.findElement(By.cssSelector(".regular-price")).getCssValue("font-size");
        String campaignPriceColorOnProductPage = productPage.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        String campaignPriceDecOnProductPage = productPage.findElement(By.cssSelector(".campaign-price")).getTagName();
        String campaignpricefontsizeP = productPage.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size");


        Assert.assertEquals(mainPage, titleproductPage);
        Assert.assertEquals(price, priceOnProductPage);
        Assert.assertEquals(campaignPrice,campaignPriceOnProductPage);
//        Assert.assertEquals(priceColor,"rgba(119, 119, 119, 1)");
        String colorspriceColor[];
        colorspriceColor = priceColor.replace("rgba(", "").split(",");
        Assert.assertEquals(colorspriceColor[0].trim(), colorspriceColor[1].trim());
        Assert.assertEquals(colorspriceColor[1].trim(), colorspriceColor[2].trim());
        Assert.assertEquals(priceDec,"s");
//        Assert.assertEquals(campaignPriceColor,"rgba(204, 0, 0, 1)");
        String colorscampaignPrice[];
        colorscampaignPrice = campaignPriceColor.replace("rgba(", "").split(",");
        Assert.assertEquals(colorscampaignPrice[1].trim(), "0");
        Assert.assertEquals(colorscampaignPrice[2].trim(), "0");
        Assert.assertEquals(campaignPriceDec,"strong");

//        Assert.assertEquals(priceColorOnProductPage,"rgba(102, 102, 102, 1)");
        String colorsOnProductPage[];
        colorsOnProductPage = priceColorOnProductPage.replace("rgba(", "").split(",");
        Assert.assertEquals(colorsOnProductPage[0].trim(), colorsOnProductPage[1].trim());
        Assert.assertEquals(colorsOnProductPage[1].trim(), colorsOnProductPage[2].trim());
        Assert.assertEquals(priceDecOnProductPage,"s");
        String colors[];
        colors = campaignPriceColorOnProductPage.replace("rgba(", "").split(",");
        Assert.assertEquals(colors[1].trim(), "0");
        Assert.assertEquals(colors[2].trim(), "0");
        //Assert.assertEquals(campaignPriceColorOnProductPage,"rgba(204, 0, 0, 1)");
        Assert.assertEquals(campaignPriceDecOnProductPage,"strong");

        Assert.assertTrue(Double.parseDouble(priceFontSize.replace("px","")) < Double.parseDouble(campaignPriceFontSize.replace("px","")));
        Assert.assertTrue(Double.parseDouble(priceFontSizeOnProductPage.replace("px","")) < Double.parseDouble(campaignpricefontsizeP.replace("px","")));

    }
}
