package alfaBankTest.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListOfProductsInModuleWindow {

    private final By buttonBasketRedirectLocator = By.xpath("//*[@class='btn btn-primary btn-sm ml-auto']");
    private final By moduleWindowLocator = By.cssSelector(".dropdown-menu.dropdown-menu-right.show");
    private final By clearBasketLocator = By.cssSelector(".btn.btn-danger");//локатор кнопки очистить таблицу
    private final By buttonBasketLocator = By.xpath("//*[@id='dropdownBasket']");

    private WebDriver driver;

    public ListOfProductsInModuleWindow(WebDriver driver){
        this.driver = driver;
    }
    public ListOfProductsInModuleWindow buttonRedirect() {
        driver.findElement(buttonBasketRedirectLocator).click();
        return new ListOfProductsInModuleWindow(driver);
    }
    public boolean moduleWindowElementIsVisible() {
        return driver.findElement(moduleWindowLocator).isDisplayed();//для 1 assert преобразуем в переменную boolean
    }
    public ListOfProductsInModuleWindow deleteAllDataFromBasket() {
        driver.findElement(buttonBasketLocator).click();
        driver.findElement(clearBasketLocator).click();
        return new ListOfProductsInModuleWindow (driver);
    }
}
