package alfaBankTest.staticpageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class StaticEnotesPointSchoolTest extends TestBase {

    @Test//Task1
    public void EmptyBasket() {
        HomePage.changePageFrom1To2(driver);//проверка пагинации - переход на 2-ю страницу
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));//задаем Явное ожидание
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='120']")));//чтобы страница 2 стала дрступна
        wait.until(ExpectedConditions.textToBe(HomePage.ValueOfBasketText, "0"));//проверка наличия в корзине 0 товаров
        HomePage.buttonBasket(driver);//нажатие на иконку пустой корзины

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(ListOfProductsInModuleWindow.moduleWindowElementIsVisible(driver), "Element is not visible");//проверка открытия окна корзины
        ListOfProductsInModuleWindow.buttonRedirect(driver);//нажатие на кнопку "Перейти в корзину" в модалке

        softAssert.assertEquals(BasketPage.getColorErrorMessage(driver), LIGHT_PINK);
        softAssert.assertEquals(BasketPage.getServerErrorMessageText(driver), expectedServerErrorMessageText);
        softAssert.assertAll();
        System.out.println("Page of basket is opened correctly!");
    }

    @Test//Task2
    public void AddOneAnyProductWithoutDiscountToBasket() {
        HomePage.changePageFrom1To2(driver);//проверка пагинации - переход на 2-ю страницу
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));//задаем Явное ожидание
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='120']")));//чтобы страница 2 стала доступна
        HomePage.selectAnyProductWithoutDiscount(driver);//выбор любого Не акционного товара
        wait.until(ExpectedConditions.textToBe(HomePage.ValueOfBasketText, "1"));
        HomePage.buttonBasket(driver);//нажатие на иконку корзины

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(ListOfProductsInModuleWindow.moduleWindowElementIsVisible(driver), "Element is not visible");//проверка открытия окна корзины

        ListOfProductsInModuleWindow.buttonRedirect(driver);//нажатие на кнопку "Перейти в корзину" в модалке
        softAssert.assertEquals(BasketPage.getColorErrorMessage(driver), LIGHT_PINK);
        softAssert.assertEquals(BasketPage.getServerErrorMessageText(driver), expectedServerErrorMessageText);
        softAssert.assertAll();
        System.out.println("Product without discount added correctly!");
    }

    @Test//Task3
    public void AddOneAnyProductWithDiscountToBasket() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));//задаем Явное ожидание
        wait.until(ExpectedConditions.textToBe(HomePage.ValueOfBasketText, "0"));
        HomePage.selectAnyProductWithDiscount(driver);//выбор любого акционного товара
        wait.until(ExpectedConditions.textToBe(HomePage.ValueOfBasketText, "1"));
        HomePage.buttonBasket(driver);//нажатие на иконку корзины

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(ListOfProductsInModuleWindow.moduleWindowElementIsVisible(driver), "Element is not visible");//проверка открытия окна корзины

        ListOfProductsInModuleWindow.buttonRedirect(driver);//нажатие на кнопку "Перейти в корзину" в модалке
        softAssert.assertEquals(BasketPage.getColorErrorMessage(driver), LIGHT_PINK);
        softAssert.assertEquals(BasketPage.getServerErrorMessageText(driver), expectedServerErrorMessageText);
        softAssert.assertAll();
        System.out.println("Product with discount added correctly!");
    }

    @Test//Task4
    public void AddNineAnyProductsToBasket() {
        HomePage.selectAnyProductWithDiscount(driver);//выбор любого акционного товара
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));//задаем Явное ожидание
        wait.until(ExpectedConditions.textToBe(HomePage.ValueOfBasketText, "1"));//проверка наличия в корзине 1 товар
        HomePage.selectNineAnyProducts(driver, "3", "4", "1");//параметризировал метод для ввода разного количества трех видов книг до 9 ВКЛЮЧАЯ
        wait.until(ExpectedConditions.textToBe(HomePage.ValueOfBasketText, "9"));//проверка наличия в корзине 9 товаров
        HomePage.buttonBasket(driver);//нажатие на иконку корзины

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(ListOfProductsInModuleWindow.moduleWindowElementIsVisible(driver), "Element is not visible");//проверка открытия окна корзины

        ListOfProductsInModuleWindow.buttonRedirect(driver);//нажатие на кнопку "Перейти в корзину" в модалке
        softAssert.assertEquals(BasketPage.getColorErrorMessage(driver), LIGHT_PINK);
        softAssert.assertEquals(BasketPage.getServerErrorMessageText(driver), expectedServerErrorMessageText);
        softAssert.assertAll();
        System.out.println("Any 9 products added correctly!");
    }

    @Test//Task4.1
    public void AddNineAnyDiscountAndNotDiscountProductsToBasket() {
        HomePage.selectAnyProductWithDiscount(driver);//выбор любого акционного товара
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));//задаем Явное ожидание
        wait.until(ExpectedConditions.textToBe(HomePage.ValueOfBasketText, "1"));//проверка наличия в корзине 1 товар
        HomePage.selectNineAnyDiscountAndNotDiscountProducts(driver, "3", "5");
        wait.until(ExpectedConditions.textToBe(HomePage.ValueOfBasketText, "9"));//проверка наличия в корзине 9 товаров
        HomePage.buttonBasket(driver);//нажатие на иконку корзины

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(ListOfProductsInModuleWindow.moduleWindowElementIsVisible(driver), "Element is not visible");

        ListOfProductsInModuleWindow.buttonRedirect(driver);//нажатие на кнопку "Перейти в корзину" в модалке
        softAssert.assertEquals(BasketPage.getColorErrorMessage(driver), LIGHT_PINK);
        softAssert.assertEquals(BasketPage.getServerErrorMessageText(driver), expectedServerErrorMessageText);
        softAssert.assertAll();
        System.out.println("Any 9 products added correctly! Product with discount and without discount!");
    }

    @Test//Task5
    public void AddNineAnyProductsWithDiscountToBasket() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));//задаем Явное ожидание
        HomePage.selectNineAnyProductWithDiscount(driver, "9");//параметризировал метод для ввода разного количества трех видов книг до 9 ВКЛЮЧАЯ
        wait.until(ExpectedConditions.textToBe(HomePage.ValueOfBasketText, "9"));//проверка наличия в корзине 9 товаров
        HomePage.buttonBasket(driver);//нажатие на иконку корзины с 9 товарами

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(ListOfProductsInModuleWindow.moduleWindowElementIsVisible(driver), "Element is not visible");

        ListOfProductsInModuleWindow.buttonRedirect(driver);//нажатие на кнопку "Перейти в корзину" в модалке
        softAssert.assertEquals(BasketPage.getColorErrorMessage(driver), LIGHT_PINK);
        softAssert.assertEquals(BasketPage.getServerErrorMessageText(driver), expectedServerErrorMessageText);
        softAssert.assertAll();
        System.out.println("Any 9 products with discount added correctly!");
    }
}
