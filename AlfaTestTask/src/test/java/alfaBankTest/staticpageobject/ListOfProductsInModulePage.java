package alfaBankTest.staticpageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListOfProductsInModulePage {

    private static final By buttonBasketRedirectLocator = By.xpath("//*[@class='btn btn-primary btn-sm ml-auto']");
    private static final By moduleWindowLocator = By.cssSelector(".dropdown-menu.dropdown-menu-right.show");
    private static final By distinctProductLocator = By.cssSelector(".actionDeleteProduct.fa.fa-trash.fa-lg.mr-4");


    public static void buttonRedirect (WebDriver driver) {
        driver.findElement(buttonBasketRedirectLocator).click();
    }
    public static boolean moduleWindowElementIsVisible(WebDriver driver) {
        return driver.findElement(moduleWindowLocator).isDisplayed();//для 1 assert преобразуем в переменную boolean
    }
    public static void deletePartProduct (WebDriver driver) {
        driver.findElement(distinctProductLocator).click();
    }

}
