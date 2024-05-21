package alfaBankTest.staticpageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private static final By buttonBasketLocator = By.xpath("//*[@id='dropdownBasket']");
    private static final By anyProductWithoutDiscount = By.xpath("//*[@class='wrap-ribbon d-none']/parent::div[@class='note-poster']/following::div[1]/button");//кнопка Купить для любого НЕакционного товара
    private static final By anyProductWithDiscount = By.cssSelector(".hasDiscount .actionBuyProduct.btn.btn-primary.mt-3");//кнопка Купить для любого акционного товара
    protected static final By ValueOfBasketText = By.cssSelector(".basket-count-items.badge.badge-primary");

    //Task 4
    private static final By buttonBuy = By.cssSelector(".note-list.row>:nth-child(1) .actionBuyProduct");//кнопка Купить по индексу 1
    private static final By buttonBuy2 = By.cssSelector(".note-list.row>:nth-child(4) .actionBuyProduct");//кнопка Купить по индексу 4
    private static final By buttonBuy3 = By.cssSelector(".note-list.row>:nth-child(7) .actionBuyProduct");//кнопка Купить по индексу 7
    private static final By anyFirstProducts = By.cssSelector(".note-list.row>:nth-child(1) input");//поле ввода по индексу 1
    private static final By anySecondProducts = By.cssSelector(".note-list.row>:nth-child(4) input");//поле ввода по индексу 4
    private static final By anyThreadProducts = By.cssSelector(".note-list.row>:nth-child(7) input");//поле ввода по индексу 7
    private static final By paginationSecondPageLocator = By.xpath("//*/li[@class='page-item ']");//переключение с 1 на 2 страницу
    //или By.xpath(//*[@class='page-link'][@data-page-number='2']);

    //Task 4.1
    private static final By anyDiscountProduct = By.cssSelector(".hasDiscount div.input-group.mt-3>input");//первое поле ввода акционного товара
    private static final By anyNotDiscountProduct = By.xpath("//*[@class='wrap-ribbon d-none']/parent::div[@class='note-poster']/following::div[1]//input");//первое поле ввода акционного товара

    //Task 5
    private static final By ProductWithDiscount = By.xpath("//*[@class='note-list row']/div[4]//input");

    public static void buttonBasket(WebDriver driver) {
        driver.findElement(buttonBasketLocator).click();
    }

    public static void selectAnyProductWithoutDiscount(WebDriver driver) {//задача 2
        driver.findElement(anyProductWithoutDiscount).click();
    }

    public static void selectAnyProductWithDiscount(WebDriver driver) {//задача 3
        driver.findElement(anyProductWithDiscount).click();
    }

    public static void changePageFrom1To2(WebDriver driver) {
        driver.findElement(paginationSecondPageLocator).click();//переход на страницу 2
    }

    public static void selectNineAnyProducts(WebDriver driver, String value1, String value2, String value3) {//задача 4
        driver.findElement(anyFirstProducts).clear();//очистка поля ввода
        driver.findElement(anyFirstProducts).sendKeys(value1);//ввод данных в поле ввода
        driver.findElement(buttonBuy).click();
        driver.findElement(anySecondProducts).clear();//очистка поля ввода
        driver.findElement(anySecondProducts).sendKeys(value2);//ввод данных в поле ввода
        driver.findElement(buttonBuy2).click();
        driver.findElement(anyThreadProducts).clear();//очистка поля ввода
        driver.findElement(anyThreadProducts).sendKeys(value3);//ввод данных в поле ввода
        driver.findElement(buttonBuy3).click();
    }

    public static void selectNineAnyDiscountAndNotDiscountProducts(WebDriver driver, String discountProduct, String notDiscountProduct) {//задача 4.1
        driver.findElement(anyDiscountProduct).clear();//очистка поля ввода акционного товара
        driver.findElement(anyDiscountProduct).sendKeys(discountProduct);//ввод данных в поле ввода акционного товара
        driver.findElement(anyProductWithDiscount).click();

        driver.findElement(anyNotDiscountProduct).clear();//очистка поля ввода НЕакционного товара
        driver.findElement(anyNotDiscountProduct).sendKeys(notDiscountProduct);//ввод данных в поле ввода НЕакционного товара
        driver.findElement(anyProductWithoutDiscount).click();
    }

    public static void selectNineAnyProductWithDiscount(WebDriver driver, String value) {//задача 5
        driver.findElement(ProductWithDiscount).clear();
        driver.findElement(ProductWithDiscount).sendKeys(value);
        driver.findElement(buttonBuy2).click();
    }
}
