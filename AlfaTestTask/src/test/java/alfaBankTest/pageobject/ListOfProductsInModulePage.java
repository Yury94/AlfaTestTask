package alfaBankTest.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListOfProductsInModulePage {

    private final By buttonBasketRedirectLocator = By.xpath("//*[@class='btn btn-primary btn-sm ml-auto']");
    private final By moduleWindowLocator = By.cssSelector(".dropdown-menu.dropdown-menu-right.show");
    private final By distinctProductLocator = By.cssSelector(".actionDeleteProduct.fa.fa-trash.fa-lg.mr-4");

    private WebDriver driver;

    public ListOfProductsInModulePage(WebDriver driver){
        this.driver = driver;
    }
    public void buttonRedirect () {
        driver.findElement(buttonBasketRedirectLocator).click();
    }
    public boolean moduleWindowElementIsVisible() {
        return driver.findElement(moduleWindowLocator).isDisplayed();//для 1 assert преобразуем в переменную boolean
    }
    public void deletePartProduct () {
        driver.findElement(distinctProductLocator).click();
    }

}
