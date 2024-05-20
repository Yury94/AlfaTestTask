package alfaBankTest.selenide;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@Epic("Selenium test task")
@Feature("Stationery")
public class BasketPage {

    public static void validateErrorMessage (String expectedErrorText, String bgColor) {
        Configuration.assertionMode = AssertionMode.SOFT;
        $("div.alert.alert-danger").shouldHave(text(expectedErrorText));
        $("div.alert.alert-danger").shouldHave(cssValue("background-color", bgColor));
    }
}
