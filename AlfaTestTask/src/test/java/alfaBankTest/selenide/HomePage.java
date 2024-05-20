package alfaBankTest.selenide;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

@Epic("Selenium test task")
@Feature("Stationery")
public class HomePage {
    public static By ValueOfBasketText = By.cssSelector(".basket-count-items.badge.badge-primary");

    public static void clickButtonBasket() {
        $(By.xpath("//*[@id='dropdownBasket']")).click();
    }

    public static void deleteAllDataFromBasket() {
        $(By.xpath("//*[@id='dropdownBasket']")).click();
        $(By.cssSelector(".btn.btn-danger")).click();
    }

    public static void selectAnyProductWithoutDiscount() {//задача 2
        $(By.xpath("//*[@class='wrap-ribbon d-none']/parent::div[@class='note-poster']/following::div[1]/button")).click();
    }

    public static void selectAnyProductWithDiscount() {//задача 3
        $(By.cssSelector(".hasDiscount .actionBuyProduct.btn.btn-primary.mt-3")).click();
    }

    public static void changePageFrom1To2() {
        $(By.xpath("//*/li[@class='page-item ']")).click();//переход на страницу 2
    }//или By.xpath(//*[@class='page-link'][@data-page-number='2']);

    public static void selectNineAnyProducts(WebDriver driver, String value1, String value2, String value3) {//задача 4
        $(By.cssSelector(".note-list.row>:nth-child(1) input")).clear();//очистка поля ввода
        $(By.cssSelector(".note-list.row>:nth-child(1) input")).sendKeys(value1);//ввод данных в поле ввода
        $(By.cssSelector(".note-list.row>:nth-child(1) .actionBuyProduct")).click();
        $(By.cssSelector(".note-list.row>:nth-child(4) input")).clear();//очистка поля ввода
        $(By.cssSelector(".note-list.row>:nth-child(4) input")).sendKeys(value2);//ввод данных в поле ввода
        $(By.cssSelector(".note-list.row>:nth-child(4) .actionBuyProduct")).click();
        $(By.cssSelector(".note-list.row>:nth-child(7) input")).clear();//очистка поля ввода
        $(By.cssSelector(".note-list.row>:nth-child(7) input")).sendKeys(value3);//ввод данных в поле ввода
        $(By.cssSelector(".note-list.row>:nth-child(7) .actionBuyProduct")).click();
    }

    public static void selectNineAnyProductWithDiscount(String value) {//задача 5
        $(By.xpath("//*[@class='note-list row']/div[4]//input")).clear();
        $(By.xpath("//*[@class='note-list row']/div[4]//input")).sendKeys(value);//ввод данных в товар №4
        $(By.cssSelector(".note-list.row>:nth-child(4) .actionBuyProduct")).click();
    }
}
