package alfaBankTest.selenide;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Epic("Selenium test task")
@Feature("Stationery")
public class ListOfProductsInModulePage {

    public static void buttonRedirect() {
        $(By.xpath("//*[@class='btn btn-primary btn-sm ml-auto']")).click();
    }

//    public static boolean moduleWindowElementIsVisible() {
//        return $(By.cssSelector(".dropdown-menu.dropdown-menu-right.show")).isDisplayed();//для 1 assert преобразуем в переменную boolean
//    }

    public static void validateModuleWindow(){//assert
        $(".dropdown-menu.dropdown-menu-right.show").shouldBe(visible);
    }
    public static void deletePartProduct() {
        $(By.cssSelector(".actionDeleteProduct.fa.fa-trash.fa-lg.mr-4")).click();
    }

}
