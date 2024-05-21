package alfaBankTest.selenide;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Epic("Selenium test task")
@Feature("Stationery")
public class ListOfProductsInModuleWindow {
    public BasketPage buttonRedirect() {
        $(By.xpath("//*[@class='btn btn-primary btn-sm ml-auto']")).click();
        return Selenide.page(BasketPage.class);//переход на страницу BasketPage
    }

    public ListOfProductsInModuleWindow validateModuleWindow() {//проверка, что модальное окно открыто
        $(".dropdown-menu.dropdown-menu-right.show").shouldBe(visible);
        return this;
    }
    public ListOfProductsInModuleWindow deleteAllDataFromBasket() {
        $(By.xpath("//*[@id='dropdownBasket']")).click();
        $(By.cssSelector(".btn.btn-danger")).click();
        return this;
    }
}
