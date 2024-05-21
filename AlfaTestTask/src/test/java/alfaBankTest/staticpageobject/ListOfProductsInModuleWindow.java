package alfaBankTest.staticpageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListOfProductsInModuleWindow {

    private static final By buttonBasketRedirectLocator = By.xpath("//*[@class='btn btn-primary btn-sm ml-auto']");
    private static final By moduleWindowLocator = By.cssSelector(".dropdown-menu.dropdown-menu-right.show");
    private static final By clearBasketLocator = By.cssSelector(".btn.btn-danger");//локатор кнопки очистить таблицу
    private static final By buttonBasketLocator = By.xpath("//*[@id='dropdownBasket']");

    public static void buttonRedirect (WebDriver driver) {
        driver.findElement(buttonBasketRedirectLocator).click();
    }
    public static boolean moduleWindowElementIsVisible(WebDriver driver) {
        return driver.findElement(moduleWindowLocator).isDisplayed();//для 1 assert преобразуем в переменную boolean
    }
    public  static void deleteAllDataFromBasket(WebDriver driver) {
        driver.findElement(buttonBasketLocator).click();
        driver.findElement(clearBasketLocator).click();
    }
}
