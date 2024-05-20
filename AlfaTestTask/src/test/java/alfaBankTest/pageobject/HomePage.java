package alfaBankTest.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final By buttonBasketLocator = By.xpath("//*[@id='dropdownBasket']");
    private final By anyProductWithoutDiscount = By.xpath("//*[@class='wrap-ribbon d-none']/parent::div[@class='note-poster']/following::div[1]/button");//кнопка Купить для любого НЕакционного товара
    private final By anyProductWithDiscount = By.cssSelector(".hasDiscount .actionBuyProduct.btn.btn-primary.mt-3");//кнопка Купить для любого акционного товара
    protected final By ValueOfBasketText = By.cssSelector(".basket-count-items.badge.badge-primary");
    private By clearBasketLocator = By.cssSelector(".btn.btn-danger");//локатор кнопки очистить таблицу

    //Task 4
    private By buttonBuy = By.cssSelector(".note-list.row>:nth-child(1) .actionBuyProduct");//кнопка Купить по индексу 1
    private By buttonBuy2 = By.cssSelector(".note-list.row>:nth-child(4) .actionBuyProduct");//кнопка Купить по индексу 4
    private By buttonBuy3 = By.cssSelector(".note-list.row>:nth-child(7) .actionBuyProduct");//кнопка Купить по индексу 7
    private By anyFirstProducts = By.cssSelector(".note-list.row>:nth-child(1) input");//поле ввода по индексу 1
    private By anySecondProducts = By.cssSelector(".note-list.row>:nth-child(4) input");//поле ввода по индексу 4
    private By anyThreadProducts = By.cssSelector(".note-list.row>:nth-child(7) input");//поле ввода по индексу 7
    private By paginationSecondPageLocator = By.xpath("//*/li[@class='page-item ']");//переключение с 1 на 2 страницу
    //или By.xpath(//*[@class='page-link'][@data-page-number='2']);
    //Task 4.1
    private By anyDiscountProduct = By.cssSelector(".hasDiscount div.input-group.mt-3>input");//первое поле ввода акционного товара
    private By anyNotDiscountProduct = By.xpath("//*[@class='wrap-ribbon d-none']/parent::div[@class='note-poster']/following::div[1]//input");//первое поле ввода акционного товара

    //Task 5
    private By ProductWithDiscount = By.xpath("//*[@class='note-list row']/div[4]//input");

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void buttonBasket() {
        driver.findElement(buttonBasketLocator).click();
    }

    public void deleteAllDataFromBasket() {
        driver.findElement(buttonBasketLocator).click();
        driver.findElement(clearBasketLocator).click();
    }

    public void selectAnyProductWithoutDiscount() {//задача 2
        driver.findElement(anyProductWithoutDiscount).click();
    }

    public void selectAnyProductWithDiscount() {//задача 3
        driver.findElement(anyProductWithDiscount).click();
    }

    public void changePageFrom1To2() {
        driver.findElement(paginationSecondPageLocator).click();//переход на страницу 2
    }

    public void selectNineAnyProducts(String value1, String value2, String value3) {//задача 4
        driver.findElement(anyFirstProducts).clear();
        driver.findElement(anyFirstProducts).sendKeys(value1);
        driver.findElement(buttonBuy).click();
        driver.findElement(anySecondProducts).clear();
        driver.findElement(anySecondProducts).sendKeys(value2);
        driver.findElement(buttonBuy2).click();
        driver.findElement(anyThreadProducts).clear();
        driver.findElement(anyThreadProducts).sendKeys(value3);
        driver.findElement(buttonBuy3).click();
    }

    public void selectNineAnyDiscountAndNotDiscountProducts(String discountProduct, String notDiscountProduct) {//задача 4.1
        driver.findElement(anyDiscountProduct).clear();//очистка поля ввода акционного товара
        driver.findElement(anyDiscountProduct).sendKeys(discountProduct);//ввод данных в поле ввода акционного товара
        driver.findElement(anyProductWithDiscount).click();

        driver.findElement(anyNotDiscountProduct).clear();//очистка поля ввода НЕакционного товара
        driver.findElement(anyNotDiscountProduct).sendKeys(notDiscountProduct);//ввод данных в поле ввода НЕакционного товара
        driver.findElement(anyProductWithoutDiscount).click();
    }

    public void selectNineAnyProductWithDiscount(String value) {//задача 5
        driver.findElement(ProductWithDiscount).clear();
        driver.findElement(ProductWithDiscount).sendKeys(value);
        driver.findElement(buttonBuy2).click();
    }
}
