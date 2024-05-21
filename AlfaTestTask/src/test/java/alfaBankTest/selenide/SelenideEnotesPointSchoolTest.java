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


//    @Description("Page of basket is opened after tap on basket icon, displayed module window and tap on 'Go to busket' button.")
//    @Story("Opening page of basket if amount of product in basket is zero(0) by default")
//    @Test//Task1
//    public void aEmptyBasket() throws InterruptedException {
//        String expectedServerErrorMessageText = "Internal server error";//задаем ожидаемую переменную для сравнения
//
//        LoginPage.attemptLogin("test", "test");//Логинимся
//        HomePage.changePageFrom1To2();//проверка пагинации - переход на 2-ю страницу
//        $(By.xpath("//span[text()='120']")).shouldBe(visible);//проверяем доступность страницы
//
//        HomePage.selectAnyProductWithoutDiscount();//КОСТЫЛЬ - дабавление 1 Не акционного товара, что бы очистить корзину
//        $(HomePage.ValueOfBasketText).shouldHave(text("10"));//Костыль-проверка наличия в корзине 9 товаров
//        HomePage.deleteAllDataFromBasket();//очистка корзины для выполнения условия задачи
//
//        Thread.sleep(1000);
//
//        $(HomePage.ValueOfBasketText).shouldHave(text("0"));//проверка наличия в корзине 0 товаров для выполнения условия
//        HomePage.clickButtonBasket();//нажатие на иконку пустой корзины
//
//        ListOfProductsInModuleWindow.validateModuleWindow();//проверка открытия окна корзины
//        ListOfProductsInModuleWindow.buttonRedirect();//нажатие на кнопку "Перейти в корзину" в модалке
//        BasketPage.validateErrorMessage(expectedServerErrorMessageText, LIGHT_PINK);
//        System.out.println("Page of basket is opened correctly!");
//    }
//
//    @Description("Page of basket is opened after added 1 product without discount, tap on basket icon, displayed module window and tap on 'Go to basket' button.")
//    @Story("Opening page of basket if amount of product in basket is zero(0) by default")
//    @Test//Task2
//    public void bAddOneAnyProductWithoutDiscountToBasket() {
//        String expectedServerErrorMessageText = "Internal server error";//задаем ожидаемую переменную для сравнения
//
//        LoginPage.attemptLogin("test", "test");//Логинимся
//        HomePage.changePageFrom1To2();//проверка пагинации - переход на 2-ю страницу
//        $(By.xpath("//span[text()='120']")).shouldBe(visible);
//
//        HomePage.selectAnyProductWithoutDiscount();//выбор любого Не акционного товара
//        $(HomePage.ValueOfBasketText).shouldHave(text("1"));//Проверка наличия в корзине 1 товара
//
//        HomePage.clickButtonBasket();//нажатие на иконку корзины
//
//        ListOfProductsInModuleWindow.validateModuleWindow();//проверка открытия окна корзины
//        ListOfProductsInModuleWindow.buttonRedirect();//нажатие на кнопку "Перейти в корзину" в модалке
//        BasketPage.validateErrorMessage(expectedServerErrorMessageText, LIGHT_PINK);
//        System.out.println("Product without discount added correctly!");
//    }
//
//    @Description("Page of basket is opened after added 1 product with discount, tap on basket icon, displayed module window and tap on 'Go to basket' button.")
//    @Story("Opening page of basket if amount of product in basket is zero(0) by default")
//    @Test//Task3
//    public void cAddOneAnyProductWithDiscountToBasket() {
//        String expectedServerErrorMessageText = "Internal server error";//задаем ожидаемую переменную для сравнения
//
//        LoginPage.attemptLogin("test", "test");//Логинимся
//        HomePage.clickButtonBasket();//нажатие на иконку корзины
//        ListOfProductsInModuleWindow.deletePartProduct();//удаление части товара добавленного из предыдущего теста!
//
//        $(HomePage.ValueOfBasketText).shouldHave(text("0"));//проверяем число товаров корзины = 0
//        HomePage.selectAnyProductWithDiscount();//выбор любого акционного товара
//        $(HomePage.ValueOfBasketText).shouldHave(text("1"));//проверяем число товаров корзины = 0
//        HomePage.clickButtonBasket();//нажатие на иконку корзины
//
//        ListOfProductsInModuleWindow.validateModuleWindow();//проверка открытия окна корзины
//        ListOfProductsInModuleWindow.buttonRedirect();//нажатие на кнопку "Перейти в корзину" в модалке
//        BasketPage.validateErrorMessage(expectedServerErrorMessageText, LIGHT_PINK);
//        System.out.println("Product with discount added correctly!");
//    }
//
//    @Description("Page of basket is opened after added 8 different products with/without discount, tap on basket icon, displayed module window and tap on 'Go to basket' button.")
//    @Story("Opening page of basket if amount of product in basket is one(1) by default")
//    @Test//Task4
//    public void dAddNineAnyProductsToBasket() {
//        String expectedServerErrorMessageText = "Internal server error";//задаем ожидаемую переменную для сравнения
//
//        LoginPage.attemptLogin("test", "test");//Логинимся
//        $(HomePage.ValueOfBasketText).shouldHave(text("1"));//проверка наличие в корзине 1 товара
//        HomePage.selectNineAnyProducts(driver, "3", "4", "1");//параметризировал метод для ввода разного количества трех видов книг до 9 ВКЛЮЧАЯ
//        $(HomePage.ValueOfBasketText).shouldHave(text("9"));//проверка наличия в корзине 9 товаров
//        HomePage.clickButtonBasket();//нажатие на иконку корзины
//
//        ListOfProductsInModuleWindow.validateModuleWindow();//проверка открытия окна корзины
//        ListOfProductsInModuleWindow.buttonRedirect();//нажатие на кнопку "Перейти в корзину" в модалке
//        BasketPage.validateErrorMessage(expectedServerErrorMessageText, LIGHT_PINK);
//        System.out.println("Any 9 products added correctly!");
//    }


//    @Description("Page of basket is opened after added 9 same products with/without discount, tap on basket icon, displayed module window and tap on 'Go to basket' button.")
//    @Story("Opening page of basket if amount of product in basket is zero(0) by default")
//    @Test//Task5
//    public void eAddNineAnyProductsWithDiscountToBasket() {
//        String expectedServerErrorMessageText = "Internal server error";//задаем ожидаемую переменную для сравнения
//
//        LoginPage.attemptLogin("test", "test");//Логинимся
//        HomePage.selectAnyProductWithoutDiscount();//КОСТЫЛЬ - дабавление 1 Не акционного товара, что бы очистить корзину
//        $(HomePage.ValueOfBasketText).shouldHave(text("10"));//Костыль-проверка наличия в корзине 10 товаров
//        HomePage.deleteAllDataFromBasket();//очистка корзины для выполения условия задачи
//
//        HomePage.selectNineAnyProductWithDiscount("9");//параметризировал метод для ввода разного количества трех видов книг до 9 ВКЛЮЧАЯ
//        $(HomePage.ValueOfBasketText).shouldHave(text("9"));//Костыль-проверка наличия в корзине 9 товаров
//        HomePage.clickButtonBasket();//нажатие на иконку корзины с 9 товарами
//
//        ListOfProductsInModuleWindow.validateModuleWindow();//проверка открытия окна корзины
//        ListOfProductsInModuleWindow.buttonRedirect();//нажатие на кнопку "Перейти в корзину" в модалке
//        BasketPage.validateErrorMessage(expectedServerErrorMessageText, LIGHT_PINK);
//        System.out.println("Any 9 products with discount added correctly!");
//    }
}
