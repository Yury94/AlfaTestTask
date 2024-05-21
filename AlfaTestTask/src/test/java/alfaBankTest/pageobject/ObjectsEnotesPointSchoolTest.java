package alfaBankTest.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class ObjectsEnotesPointSchoolTest extends TestBase {
    @Test//Task1
    public void EmptyBasket() {
        HomePage homePage = new HomePage(driver);//инициализируем обьект класса HomePage
        ListOfProductsInModuleWindow listOfProductsInModuleWindow = new ListOfProductsInModuleWindow(driver);//инициализируем обьект класса ListOfProductsPage
        BasketPage basketPage = new BasketPage(driver);//инициализируем обьект класса HomePage
        homePage.changePageFrom1To2();//проверка пагинации - переход на 2-ю страницу
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));//задаем Явное ожидание
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='120']")));//чтобы страница 2 стала доступна
        wait.until(ExpectedConditions.textToBe(homePage.ValueOfBasketText, "0"));//проверка наличия в корзине 0 товаров
        homePage.buttonBasket();//нажатие на иконку пустой корзины

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(listOfProductsInModuleWindow.moduleWindowElementIsVisible(), "Element is not visible");//проверка открытия окна корзины

        listOfProductsInModuleWindow.buttonRedirect();//нажатие на кнопку "Перейти в корзину" в модалке
        softAssert.assertEquals(basketPage.getColorErrorMessage(), LIGHT_PINK);//проверка перехода на страницу корзины
        softAssert.assertEquals(basketPage.getServerErrorMessageText(), expectedServerErrorMessageText);//проверка перехода на страницу корзины
        softAssert.assertAll();
        System.out.println("Page of basket is opened correctly!");
    }

    @Test//Task2
    public void AddOneAnyProductWithoutDiscountToBasket() {
        HomePage homePage = new HomePage(driver);//инициализируем обьект класса HomePage
        ListOfProductsInModuleWindow listOfProductsPage = new ListOfProductsInModuleWindow(driver);//инициализируем обьект класса ListOfProductsPage
        BasketPage basketPage = new BasketPage(driver);//инициализируем обьект класса HomePage
        homePage.changePageFrom1To2();//проверка пагинации - переход на 2-ю страницу
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));//задаем Явное ожидание
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='120']")));//чтобы страница 2 стала доступна
        homePage.selectAnyProductWithoutDiscount();//выбор любого Не акционного товара
        wait.until(ExpectedConditions.textToBe(homePage.ValueOfBasketText, "1"));//проверка наличия в корзине 1 товара
        homePage.buttonBasket();//нажатие на иконку корзины

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(listOfProductsPage.moduleWindowElementIsVisible(), "Element is not visible");//проверка открытия окна корзины

        listOfProductsPage.buttonRedirect();//нажатие на кнопку "Перейти в корзину" в модалке
        softAssert.assertEquals(basketPage.getColorErrorMessage(), LIGHT_PINK);//проверка перехода на страницу корзины
        softAssert.assertEquals(basketPage.getServerErrorMessageText(), expectedServerErrorMessageText);//проверка перехода на страницу корзины
        softAssert.assertAll();
        System.out.println("Product without discount added correctly!");
    }

    @Test//Task3
    public void AddOneAnyProductWithDiscountToBasket() {
        HomePage homePage = new HomePage(driver);//инициализируем обьект класса HomePage
        ListOfProductsInModuleWindow listOfProductsPage = new ListOfProductsInModuleWindow(driver);//инициализируем обьект класса ListOfProductsPage
        BasketPage basketPage = new BasketPage(driver);//инициализируем обьект класса HomePage
        homePage.selectAnyProductWithDiscount();//выбор любого акционного товара
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));//задаем Явное ожидание для проверки
        wait.until(ExpectedConditions.textToBe(homePage.ValueOfBasketText, "1"));//проверка наличия в корзине 1 товара
        homePage.buttonBasket();//нажатие на иконку корзины с 1 продуктом

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(listOfProductsPage.moduleWindowElementIsVisible(), "Element is not visible");//проверка открытия окна корзины
        listOfProductsPage.buttonRedirect();//нажатие на кнопку "Перейти в корзину" в модальном окне корзины

        softAssert.assertEquals(basketPage.getColorErrorMessage(), LIGHT_PINK);//проверка перехода на страницу корзины
        softAssert.assertEquals(basketPage.getServerErrorMessageText(), expectedServerErrorMessageText);//проверка перехода на страницу корзины
        softAssert.assertAll();
        System.out.println("Product with discount added correctly!");
    }

    @Test//Task4
    public void AddNineAnyProductsToBasket() {
        HomePage homePage = new HomePage(driver);//инициализируем обьект класса HomePage
        ListOfProductsInModuleWindow listOfProductsPage = new ListOfProductsInModuleWindow(driver);//инициализируем обьект класса ListOfProductsPage
        BasketPage basketPage = new BasketPage(driver);//инициализируем обьект класса HomePage
        homePage.selectAnyProductWithDiscount();//выбор любого акционного товара
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));//задаем Явное ожидание для проверки
        wait.until(ExpectedConditions.textToBe(homePage.ValueOfBasketText, "1"));//проверка наличия в корзине 1 товара из предыдущей задачи
        homePage.selectNineAnyProducts("3", "4", "1");//параметризировал метод для ввода разного количества трех видов книг до 9 ВКЛЮЧАЯ
        wait.until(ExpectedConditions.textToBe(homePage.ValueOfBasketText, "9"));//проверка наличия в корзине 9 товаров
        homePage.buttonBasket();//нажатие на иконку корзины

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(listOfProductsPage.moduleWindowElementIsVisible(), "Element is not visible");//проверка открытия окна корзины

        listOfProductsPage.buttonRedirect();//нажатие на кнопку "Перейти в корзину" в модалке
        softAssert.assertEquals(basketPage.getColorErrorMessage(), LIGHT_PINK);//проверка перехода на страницу корзины
        softAssert.assertEquals(basketPage.getServerErrorMessageText(), expectedServerErrorMessageText);//проверка перехода на страницу корзины
        softAssert.assertAll();
        System.out.println("Any 9 products added correctly!");
    }

    @Test//Task4.1
    public void AddNineAnyDiscountAndNotDiscountProductsToBasket() {//
        HomePage homePage = new HomePage(driver);//инициализируем обьект класса HomePage
        ListOfProductsInModuleWindow listOfProductsPage = new ListOfProductsInModuleWindow(driver);//инициализируем обьект класса ListOfProductsPage
        BasketPage basketPage = new BasketPage(driver);//инициализируем обьект класса HomePage
        homePage.selectAnyProductWithDiscount();//выбор любого акционного товара
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));//задаем Явное ожидание для проверки
        wait.until(ExpectedConditions.textToBe(homePage.ValueOfBasketText, "1"));//проверка наличия в корзине 1 товара из предыдущей задачи
        homePage.selectNineAnyDiscountAndNotDiscountProducts("4", "4");
        wait.until(ExpectedConditions.textToBe(homePage.ValueOfBasketText, "9"));//проверка наличия в корзине 9 товаров
        homePage.buttonBasket();//нажатие на иконку корзины

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(listOfProductsPage.moduleWindowElementIsVisible(), "Element is not visible");//проверка открытия окна корзины

        listOfProductsPage.buttonRedirect();//нажатие на кнопку "Перейти в корзину" в модалке
        softAssert.assertEquals(basketPage.getColorErrorMessage(), LIGHT_PINK);//проверка перехода на страницу корзины
        softAssert.assertEquals(basketPage.getServerErrorMessageText(), expectedServerErrorMessageText);//проверка перехода на страницу корзины
        softAssert.assertAll();
        System.out.println("Any 9 products added correctly! Product with discount and without discount!");
    }

    @Test//Task5
    public void AddNineAnyProductsWithDiscountToBasket() {
        HomePage homePage = new HomePage(driver);//инициализируем обьект класса HomePage
        ListOfProductsInModuleWindow listOfProductsPage = new ListOfProductsInModuleWindow(driver);//инициализируем обьект класса ListOfProductsPage
        BasketPage basketPage = new BasketPage(driver);//инициализируем обьект класса HomePage
        homePage.selectNineAnyProductWithDiscount("9");//параметризировал метод для ввода разного количества трех видов книг до 9 ВКЛЮЧАЯ
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));//КОСТЫЛЬ - задаем Явное ожидание
        wait.until(ExpectedConditions.textToBe(homePage.ValueOfBasketText, "9"));//проверка наличия в корзине 9 товаров
        homePage.buttonBasket();//нажатие на иконку корзины с 9 товарами

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(listOfProductsPage.moduleWindowElementIsVisible(), "Element is not visible");//проверка открытия окна корзины

        listOfProductsPage.buttonRedirect();//нажатие на кнопку "Перейти в корзину" в модальном окне корзины
        softAssert.assertEquals(basketPage.getColorErrorMessage(), LIGHT_PINK);//проверка перехода на страницу корзины
        softAssert.assertEquals(basketPage.getServerErrorMessageText(), expectedServerErrorMessageText);//проверка перехода на страницу корзины
        softAssert.assertAll();
        System.out.println("Any 9 products with discount added correctly!");
    }
}
