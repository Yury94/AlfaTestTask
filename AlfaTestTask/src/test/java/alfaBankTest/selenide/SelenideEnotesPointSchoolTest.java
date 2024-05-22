package alfaBankTest.selenide;

import com.codeborne.selenide.testng.SoftAsserts;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

@Epic("Selenium test task")
@Feature("Stationery")
@Listeners({SoftAsserts.class, TestBase.class})
public class SelenideEnotesPointSchoolTest extends TestBase {
    @Description("Page of basket is opened after tap on basket icon, displayed module window and tap on 'Go to busket' button.")
    @Story("Opening page of basket if amount of product in basket is zero(0) by default")
    @Test//Task1
    public void emptyBasketTest() {
        new HomePage()
                .selectNextPage()//проверка пагинации
                .waitThatProductWithDiscout120Visible()//проверка загрузки страницы
                .clickButtonBasket()
                .validateModuleWindow()
                .buttonRedirect()
                .validateErrorMessage(expectedServerErrorMessageText, LIGHT_PINK);
    }

    @Description("Page of basket is opened after added 1 product without discount, tap on basket icon, displayed module window and tap on 'Go to basket' button.")
    @Story("Opening page of basket if amount of product in basket is zero(0) by default")
    @Test//Task2
    public void AddOneAnyProductWithoutDiscountToBasket() {
        HomePage homePage = new HomePage()
                .selectNextPage()//проверка пагинации
                .waitThatProductWithDiscout120Visible()//проверка загрузки страницы
                .selectAnyProductWithoutDiscount();
        homePage.getProductCount().shouldHave(text("1"));//сохраняем состояние homePage
        homePage.clickButtonBasket()
                .validateModuleWindow()
                .buttonRedirect()
                .validateErrorMessage(expectedServerErrorMessageText, LIGHT_PINK);
    }

    @Description("Page of basket is opened after added 1 product with discount, tap on basket icon, displayed module window and tap on 'Go to basket' button.")
    @Story("Opening page of basket if amount of product in basket is zero(0) by default")
    @Test//Task3
    public void AddOneAnyProductWithDiscountToBasket() {
        HomePage homePage = new HomePage();
        homePage.getProductCount().shouldHave(text("0"));
        homePage.selectAnyProductWithDiscount();
        homePage.getProductCount().shouldHave(text("1"));
        homePage.clickButtonBasket()
                .validateModuleWindow()
                .buttonRedirect()
                .validateErrorMessage(expectedServerErrorMessageText, LIGHT_PINK);
    }

    @Description("Page of basket is opened after added 8 different products with/without discount, tap on basket icon, displayed module window and tap on 'Go to basket' button.")
    @Story("Opening page of basket if amount of product in basket is one(1) by default")
    @Test//Task4
    public void AddNineAnyProductsToBasket() {
        HomePage homePage = new HomePage();
        homePage.getProductCount().shouldHave(text("0"));
        homePage.selectAnyProductWithDiscount();
        homePage.getProductCount().shouldHave(text("1"));
        homePage.selectNineAnyProducts("3", "4", "1");
        homePage.getProductCount().shouldHave(text("9"));
        homePage.clickButtonBasket()
                .validateModuleWindow()
                .buttonRedirect()
                .validateErrorMessage(expectedServerErrorMessageText, LIGHT_PINK);
    }

    @Description("Page of basket is opened after added 9 same products with/without discount, tap on basket icon, displayed module window and tap on 'Go to basket' button.")
    @Story("Opening page of basket if amount of product in basket is zero(0) by default")
    @Test//Task5
    public void AddNineAnyProductsWithDiscountToBasket() {
        HomePage homePage = new HomePage();
        homePage.getProductCount().shouldHave(text("0"));
        homePage.selectNineAnyProductWithDiscount("9");
        homePage.clickButtonBasket()
                .validateModuleWindow()
                .buttonRedirect()
                .validateErrorMessage(expectedServerErrorMessageText, LIGHT_PINK);
    }
}
