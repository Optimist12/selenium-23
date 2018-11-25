import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.visible;

public class AdminPage {

    ElementsCollection leftPanel = $$("ul#box-apps-menu li#app-");

    public void clickElementsonLeftPanel() {

        for (int i = 1; i < leftPanel.size(); i++) {
            leftPanel.get(i).click();

            if (($$("ul.docs li").size() > 0)) {
                ElementsCollection subMenuList = $$("ul#box-apps-menu li li");
                for (int j = 1; j < subMenuList.size(); j++) {
                    subMenuList = $$(By.cssSelector("ul#box-apps-menu li li"));
                    subMenuList.get(j).click();
                    $(By.xpath("//td[@id='content']//h1")).shouldBe(visible);
                }
            }
        }
    }


}
